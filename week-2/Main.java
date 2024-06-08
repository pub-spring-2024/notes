class Main {

    public static void main(String[] args) {
        System.out.println("Hello, world!");

        Person[] people = new Person[10];

        people[0] = new Person(1L, "Romi");
        System.out.println(people[0].getName());
    }
}

class Person {
    private Long id;
    private String name;
    
    Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    String getName() {
        return name;
    }
}