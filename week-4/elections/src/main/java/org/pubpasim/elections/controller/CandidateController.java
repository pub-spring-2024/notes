package org.pubpasim.elections.controller;

import java.util.Arrays;

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

    private Candidate[] candidates = new Candidate[] {
            new Candidate(2L, "Ganjar Pranowo", 4, "Boolean"),
            new Candidate(3L, "Basuki Tjahaja Purnama", 5, "String")
    };

    @RequestMapping()
    public Candidate[] getAll() {
        return candidates;
    }

    @RequestMapping("{id}")
    public Candidate getById(@PathVariable Long id) {
        for (Candidate candidate : candidates) {
            if (candidate.id == id) {
                return candidate;
            }
        }
        return null;
    }

    @PostMapping
    public String create(@RequestBody Candidate candidate) {
        candidates = Arrays.copyOf(candidates, candidates.length + 1);
        candidates[candidates.length - 1] = candidate;
        return "Kandidat berhasil disimpan.";
    }

    @PutMapping("{index}")
    public String updateByIndex(@PathVariable Integer index, @RequestBody Candidate candidate) {
        candidates[index] = candidate;
        return "Kandidat berhasil diperbarui.";
    }

    @DeleteMapping
    public String deleteLast() {
        candidates[candidates.length - 1] = null;
        candidates = Arrays.copyOf(candidates, candidates.length - 1);
        return "Kandidat terakhir berhasil dihapus.";
    }

    @RequestMapping("1")
    public Candidate candidate() {
        Candidate candidate = new Candidate();
        candidate.id = 1L;
        candidate.name = "Ridwan Kamil";
        candidate.number = 3;
        candidate.generation = "Include";
        return candidate;
    }
}
