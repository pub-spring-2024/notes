package org.pubpasim.elections.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Candidate {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer number;

    private String batch;

    // constructor tanpa argumen
    public Candidate() {
    }

    // constructor semua argumen
    public Candidate(Long id, String name, Integer number, String batch) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.batch = batch;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }

    public String getBatch() {
        return batch;
    }

    public String getNumberAndName() {
        return "0" + number.toString() + " " + name;
    }
}
