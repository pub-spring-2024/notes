import { useEffect, useState } from "react";
import CandidateCard from "./components/CandidateCard";

export interface Candidate {
  id: number;
  name: string;
  number: number;
  batch: string;
  numberAndName?: string;
}

export default function App() {
  // semua kandidat
  const [candidates, setCandidates] = useState<Candidate[]>([]);

  // kandidat baru
  const [candidate, setCandidate] = useState<Partial<Candidate>>({
    name: "",
    number: 0,
    batch: "",
  });

  useEffect(() => {
    fetch("http://localhost:8080/api/candidates")
      .then((response) => response.json())
      .then((candidates) => setCandidates(candidates));
  }, []);

  function handleDelete(id: number) {
    fetch(`http://localhost:8080/api/candidates/${id}`, {
      method: "DELETE",
    }).then((response) => {
      if (response.ok) {
        setCandidates(candidates.filter((candidate) => candidate.id !== id));
      }
    });
  }

  return (
    <div className="bg-white rounded-lg">
      <h1>Kandidat</h1>
      <div>
        {candidates.map((candidate) => (
          <CandidateCard
            key={candidate.id}
            candidate={candidate}
            onDelete={handleDelete}
          />
        ))}
      </div>
      <div className="grid grid-cols-2 gap-2 border rounded-lg p-4 w-64">
        <label>Nama</label>
        <input
          className="border"
          value={candidate.name}
          onChange={(e) => setCandidate({ ...candidate, name: e.target.value })}
        />
        <label>Nomor urut</label>
        <input
          type="number"
          className="border"
          value={candidate.number}
          onChange={(e) =>
            setCandidate({ ...candidate, number: parseInt(e.target.value) })
          }
        />
        <label>Angkatan</label>
        <input
          className="border"
          value={candidate.batch}
          onChange={(e) =>
            setCandidate({ ...candidate, batch: e.target.value })
          }
        />
        <button
          className="bg-blue-500 text-white rounded-lg"
          onClick={() => {
            fetch("http://localhost:8080/api/candidates", {
              method: "POST",
              headers: {
                "Content-Type": "application/json",
              },
              body: JSON.stringify(candidate),
            })
              .then((response) => response.json())
              .then((candidate) => {
                setCandidates([...candidates, candidate]);
                setCandidate({
                  name: "",
                  number: 0,
                  batch: "",
                });
              });
          }}
        >
          Simpan
        </button>
      </div>
    </div>
  );
}
