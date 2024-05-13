Materi: React TypeScript
Hari, tanggal: Minggu, 12 Mei 2024
# Persiapan

Yang perlu diinstal:
- Node.js
- `pnpm` (opsional, boleh menggunakan `npm`)
- TypeScript (`pnpm add -g typescript`)
## Menginstal `pnpm`
Buka command prompt (Terminal di Windows 11, CMD di Windows 10 ke bawah).
```shell
iwr https://get.pnpm.io/install.ps1 -useb | iex
```
## Menginstal TypeScript
Buka command prompt (Terminal di Windows 11, CMD di Windows 10 ke bawah).
```shell
pnpm add -g typescript
```
# Hello World
Buat file bernama `index.ts`.
```ts
let nama: string;
nama = "Getch";
console.log(`Halo, ${nama}!`);
```
Jalankan:
```shell
tsc index.ts
node index.js
```
# Sintaks dasar
## Tipe data pada variabel
Contoh:
```ts
let nama: string;
let umur: number;
```
## Inferention (penyimpulan)
Variabel yang diinisialisasi nilainya tidak perlu disebutkan tipe datanya.
```ts
let angkatan = 21;
// `bilangan1` otomatis bertipe `number`
```
Variabel `bilangan1` tidak dapat diisi dengan data yang tidak bertipe `number`.
```ts
angkatan = "include"
// tidak valid, karena `angkatan` harus bertipe `number`
```
## Sintaks `any`
Sintaks `any` digunakan untuk menonaktifkan *typechecking* (sebaiknya dihindari).
```ts
let data: any;
data = 21;
data = "duapuluhsatu";
data = false;
```
## Union
Union adalah gabungan dari beberapa tipe data (agar dapat menerima berbagai tipe data).
```ts
let angkatan: number | string;
angkatan = 21;
angkatan = "getch";
```
## Sintaks `interface`
`interface` adalah tipe data untuk objek.
```ts
// membuat interface
interface Orang {
  id: number;
  nama: string;
}

// menggunakan interface
let orang1: Orang = {
  id: 1,
  nama: "Gibran",
};
```
## Sintaks `extends`
`extends` adalah sintaks untuk menambahkan property/method pada `interface`.
```ts
interface Mahasiswa extends Orang {
  fakultas: string;
}

interface Dosen extends Orang {
  mataKuliah: string;
}

let mahasiswa1: Mahasiswa = {
  id: 1,
  nama: "Gibran",
  fakultas: "Ekonomi",
};

const dosen1: Dosen = {
  id: 1,
  nama: "Anies",
  mataKuliah: "Ekonomi",
};
```
## Tipe data pada parameter
```ts
function cetakNama(orang: Orang) {
  console.log(orang.nama);
}
```
## Tipe data pada function (hasil return)
Tipe data hasil return function dituliskan di antara kurung tutup parameter dan kurung kurawal buka *function*.
```ts
// function yang mengembalikan string
function getNama(orang: Orang): string {
  return orang.nama;
}

// function yang tidak mengembalikan data
function cetakNama(orang: Orang): void {
  console.log(orang.nama);
}
```
## Generic
Generic adalah parameter untuk interface agar dinamis.
```ts
interface Komunitas<Type> {
  id: number;
  nama: string;
  getAnggota: () => Type;
}

const komunitas1: Komunitas<Mahasiswa> = {
  id: 1,
  nama: "Getch",
  getAnggota: () => {
    const anggota = mahasiswa1;
    return anggota;
  },
};

const komunitas2: Komunitas<Dosen> = {
  id: 1,
  nama: "Dosen Bandung",
  getAnggota: () => {
    const anggota = dosen1;
    return anggota;
  },
};
```
## Sistem tipe struktural
Dalam sistem tipe struktural, jika dua benda mempunyai bentuk yang sama, maka keduanya dianggap bertipe sama. Contoh:
```ts
const objek = {
  id: 2,
  nama: "Prabowo",
};

let orang: Orang;
orang = objekTidakDiketahui;
// ini valid, padahal kita tidak menyebutkan bahwa `objek` bertipe `Orang`
```
## Array
```ts
let numbers: number[];
let students: Mahasiswa[];

numbers = [1, 2, 3];
students = [
  {
    id: 1,
    nama: "Romi",
    fakultas: "Ilmu Komputer",
  },
  {
    id: 2,
    nama: "Peni",
    fakultas: "Ekonomi",
  },
  {
    id: 3,
    nama: "Putri",
    fakultas: "Ekonomi",
  },
  {
    id: 3,
    nama: "Amel",
    fakultas: "Ekonomi",
  },
  {
    id: 3,
    nama: "Kharisma",
    fakultas: "Ekonomi",
  },
];
```
## Tuple
Tuple adalah objek dengan properti tanpa *key*, hanya *value*, seperti array tapi ditentukan setiap itemnya.
```ts
let item: [string, number];
item = ["meja", 50_000];
```