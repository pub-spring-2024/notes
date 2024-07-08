import { Candidate } from "../App";

export default function CandidateCard({
  candidate,
  onDelete,
}: {
  candidate: Candidate;
  onDelete: (id: number) => void;
}) {
  return (
    <div className="flex gap-4">
      <p>
        {candidate.numberAndName} - {candidate.batch}
      </p>
      <button
        className="bg-red-500 text-white rounded-lg"
        onClick={() => {
          if (confirm("Apakah Anda yakin ingin menghapus kandidat ini?")) {
            onDelete(candidate.id);
          }
        }}
      >
        Hapus
      </button>
    </div>
  );
}
