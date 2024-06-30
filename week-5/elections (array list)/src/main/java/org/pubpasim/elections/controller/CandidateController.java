package org.pubpasim.elections.controller;

import java.util.ArrayList;
import java.util.List;

import org.pubpasim.elections.model.Candidate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/candidates")
@RestController
public class CandidateController {

    private List<Candidate> candidates = new ArrayList<Candidate>();

    // constructor
    public CandidateController() {
        candidates.add(new Candidate(1L, "Romi", 1, "Jarvis"));
        candidates.add(new Candidate(2L, "Ades", 2, "Getch"));
        candidates.add(new Candidate(3L, "Arya", 3, "Getch"));
    }

    // get semua kandidat
    @RequestMapping()
    public List<Candidate> getAll() {
        return candidates;
    }

    // get kandidat berdasarkan ID
    @RequestMapping("{id}")
    public Candidate getById(@PathVariable Long id) {
        for (Candidate candidate : candidates) {
            if (candidate.getId() == id) {
                return candidate;
            }
        }
        return null;
    }

    // buat kandidat
    @PostMapping
    public String create(@RequestBody Candidate candidate) {
        candidates.add(candidate);
        return "Kandidat berhasil disimpan.";
    }

    // edit kandidat
    @PutMapping("{index}")
    public String editByIndex(@PathVariable Integer index, @RequestBody Candidate candidate) {
        candidates.set(index, candidate);
        return "Kandidat berhasil diperbarui.";
    }

    // hapus kandidat berdasarkan ID
    @DeleteMapping("{id}")
    public String deleteById(@PathVariable Long id) {
        candidates.removeIf(candidate -> candidate.getId() == id);
        return "Kandidat terakhir berhasil dihapus.";
    }
}
