JDK, JRE, dan JVM
- perbedaan
- instalasi
Hello World
- print hello world
	- `System.out.print("Hello, world!");`
	- harus ada method `main`
	- harus ada class
	- method `main` harus berparameter `String[]`
	- method `main` harus `public static` (`public` = access modifier)
	- class yang dieksekusi harus di paling atas
Compile dan run
- `javac Main.java`
- `java Main`
- `java Main.java` (Java 11+)
Tipe data
- `char[]` vs String
- `int` vs `Integer`
Array
- membuat: `new Person[10]`
- inisialisasi: `{1, 2, 3}`
OOP
- class dan objek
	- data/field/property
	- method (function dalam class)
	- constuctor
		- nama method sama dengan nama class
		- method tidak bertipe data
- 4 pilar OOP
	- encapsulation -> tiap-tiap data & method yang ada hubungannya dibungkus dalam class-class dan dibatasi aksesnya dengan access modifier
		- `private`
		- `default`
		- `protected`
		- `public`
	- inheritance (pewarisan class) -> `extends`
		- yang di-*extends* = superclass
		- yang meng-*extends* = subclass
	- polymorphism: (namanya sama, implementasinya beda)
		- method overriding (nama sama, parameter berbeda, implementasi berbeda)
		- method overloading (nama sama, implementasi di subclass berbeda)
	- abstraction
		- abstract class: sebagian methodnya abstract
		- interface: semua methodnya abstract
		abstract class dan interface tidak dapat di-instansiasi.
- keyword umum
	- `this`: merujuk ke objek saat ini.
	- `super`: merujuk ke objek superclass (induk).
	- `final`: konstanta, prevent method overriding, and inheritance.
	- `static`: terikat ke class, bukan instance dari class
Scanner
Package
Membuat CRUD