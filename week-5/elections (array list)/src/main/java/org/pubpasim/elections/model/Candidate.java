package org.pubpasim.elections.model;

public class Candidate {

    private Long id;
    private String name;
    private Integer number;
    private String generation;

    public Candidate() {
    }

    public Candidate(Long id, String name, Integer number, String generation) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.generation = generation;
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

    public String getGeneration() {
        return generation;
    }

    public String getNumberAndName() {
        return "0" + number.toString() + " " + name;
    }
}
