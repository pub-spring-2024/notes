package org.pubpasim.elections;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        String[] names = new String[10];
        names[0] = "Ganjar";
        names[1] = "Mahfud";

        System.out.println("Hello, world!");
        System.out.println("Hello, " + names[0] + "!");
        System.out.printf("Hi, %s!\n", names[1]);

        // System.out.printf("args: %s dan %s", args[0], args[1]);
        System.out.println();

        Integer[] numbers = { 25, 57, 16 };
        System.out.printf("angka: %d, %d, %d", numbers[0], numbers[1], numbers[2]);
        System.out.println();

        Candidate c1 = new Candidate();
        c1.setName("Mas Nyoman");

        // anak PUB
        c1.vote();

        // pembina
        c1.vote(10);

        // pemilik yayasan
        c1.vote(20);

        System.out.println(c1.getName());
        System.out.printf("Perolehan suara: %d", c1.voteCount);
        System.out.println();

        System.out.println(Candidate.className);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nama! ");
        String name = scanner.nextLine();

        System.out.printf("Selamat datang %s!", name);

        scanner.close();
    }
}

interface PersonInterface {
    String getName();
}

class Person extends Creature {
    // field/data/property
    int id;
    protected String name;

    // implement method
    void move() {
        System.out.println("Makhluk ini telah berpindah.");
    }

    // constructor: pertama kali dipanggil pas diinisialisasi
    Person() {
        System.out.printf("Inisialisasi Person berhasil.\n");
    }

    // getter: mendapatkan/membaca data
    public String getName() {
        return name;
    }

    // setter: mengubah data
    void setName(String name) {
        this.name = name;
    }
}

class Student extends Person {
    String nim;
    int generation;

    public String getName() {
        return name + " - " + nim;
    }
}

class Candidate extends Student {
    // non static
    int number;
    long voteCount = 0;

    // static
    static String className = "Kandidat";

    // method
    void printNameWithNumber() {
        System.out.printf("%s %d", name, number);
    }

    // overriding: nama method, parameter beda
    void vote() {
        voteCount++;
    }

    void vote(int power) {
        voteCount += power;
    }
}

// class React {

// }

// instance (objek / hasil instansiasi)
// Spring objek = new Spring();
// objek.main();

// C/C++
// char nama[] = "Romi";
// char* nama = "Romi";

// Java
// char[] nama = "Romi";
// String nama = "Romi";