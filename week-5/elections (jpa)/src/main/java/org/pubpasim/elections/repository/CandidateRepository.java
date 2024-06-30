package org.pubpasim.elections.repository;

import org.pubpasim.elections.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

}
