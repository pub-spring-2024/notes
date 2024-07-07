package org.pubpasim.elections.controller;

import java.util.List;

import org.pubpasim.elections.model.Candidate;
import org.pubpasim.elections.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/candidates")
@RestController
public class CandidateController {

    private final CandidateRepository repository;

    // constructor
    @Autowired
    public CandidateController(CandidateRepository repository) {
        this.repository = repository;
    }

    // get semua kandidat
    @RequestMapping()
    public List<Candidate> getAll() {
        return repository.findAll();
    }

    // get kandidat berdasarkan ID
    @RequestMapping("{id}")
    public Object getById(@PathVariable Long id) {
        Candidate candidate = repository.findById(id).orElse(null);
        if (candidate != null) {
            return candidate;
        } else {
            return "Kandidat dengan ID " + id + " tidak ditemukan.";
        }
    }

    // buat kandidat
    @PostMapping
    public Candidate create(@RequestBody Candidate candidate) {
        return repository.save(candidate);
    }

    // edit kandidat berdasarkan ID
    @PutMapping()
    public String editById(@RequestBody Candidate candidate) {
        repository.save(candidate);
        return "Kandidat berhasil diperbarui.";
    }

    // hapus kandidat berdasarkan ID
    @DeleteMapping("{id}")
    public String deleteById(@PathVariable Long id) {
        repository.deleteById(id);
        return "Kandidat berhasil dihapus.";
    }
}
