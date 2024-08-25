Deps:

- spring-boot-starter-security
- jwt

**model/User.java**

```java
@Getter
@Setter
@Entity
public class User implements UserDetails {

	@Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String name;

    private String role;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of((GrantedAuthority) () -> role);
    }
}
```

**service/JwtService.java**

```java
@Component
public class JwtService {

    public static final SecretKey key = Jwts.SIG.HS256.key().build();

    public String create(String email) {
        return Jwts.builder().subject(email).signWith(key).compact();
    }

    public String validate(String token) {
        try {
            return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
        } catch (JwtException e) {
            return null;
        }
    }

    public void signOut(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", null);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
```

**config/RequestFilter.java**

```java
@Service
public class RequestFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Autowired
    public RequestFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            Long id = null;

            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    String subject = jwtService.validate(token);
                    if (subject != null) {
                        id = Long.parseLong(subject);
                    } else {
                        jwtService.signOut(response);
                    }
                    break;
                }
            }

            if (id != null) {
                User user = userRepository.findById(id).orElse(null);
                if (user != null) {
                    emailPasswordAuthenticationToken authToken = new emailPasswordAuthenticationToken(user, null, user.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } else {
                    jwtService.signOut(response);
                }
            }
        }

		filterChain.doFilter(request, response);
    }
}
```

**config/SecurityConfig.java**

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, RequestFilter requestFilter) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth) -> {
                    auth.requestMatchers("/api/auth/sign-in").permitAll();

                    auth.requestMatchers("/api/auth/me").authenticated();
                    auth.requestMatchers("/api/auth/sign-out").authenticated();

                    auth.anyRequest().hasAuthority("admin");
                    auth.requestMatchers(HttpMethod.GET).authenticated();
                })
                .addFilterBefore(requestFilter, emailPasswordAuthenticationFilter.class);
        return http.build();
    }
}
```

**AuthController.java**

```java
@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(JwtService jwtService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

	@PostMapping("register")
	public ResponseEntity<User> register(@RequestBody User user) {
	   try {
		   user.setPassword(passwordEncoder.encode(user.getPassword()));
		   return ResponseEntity.ok(userRepository.save(user));
	   } catch (Exception e) {
		   return ResponseEntity.badRequest().build();
	   }
	}

    @PostMapping("sign-in")
    public ResponseEntity<String> signIn(@RequestParam String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
	        if (passwordEncoder.matches(password, user.getPassword())) {
	            String token = jwtService.create(user.getId().toString());

	            Cookie cookie = new Cookie("token", token);
	            cookie.setHttpOnly(true);
	            cookie.setMaxAge(60 * 60 * 24 * 7);
	            cookie.setPath("/");
	            response.addCookie(cookie);

	            return ResponseEntity.ok(token);
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	        }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("me")
    public User me() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping("sign-out")
    public void signOut(HttpServletResponse response) {
        jwtService.signOut(response);
    }
}
```
