- Materi: React TypeScript
- Waktu: 10.00—12.30 WIB
- Hari, tanggal: Minggu, 12 Mei 2024
# Resources 📚
- [Dokumentasi resmi TypeScript](https://www.typescriptlang.org/)
- [TypeScript untuk programmer JavaScript](https://www.typescriptlang.org/docs/handbook/typescript-in-5-minutes.html)
- [TypeScript untuk programmer OOP (C#/Java)](https://www.typescriptlang.org/docs/handbook/typescript-in-5-minutes-oop.html)
- [YouTube](https://www.youtube.com/results?search_query=typescript)
# Persiapan 🛠
Yang perlu diinstal:
- [Node.js](https://nodejs.org/)
- [`pnpm`](https://pnpm.io/) (opsional, bisa juga menggunakan `npm`)
- [TypeScript](https://www.typescriptlang.org/)
- [VS Code](https://code.visualstudio.com/) (opsional, bisa juga menggunakan *text editor*/IDE lain)
## Menginstal `pnpm` (opsional)
[`pnpm`](https://pnpm.io/) adalah *package manager* Node.js yang lebih hemat penyimpanan. Untuk menginstalnya, buka Powershell (di Windows 11 dapat melalui Terminal), lalu jalankan perintah:
```shell
iwr https://get.pnpm.io/install.ps1 -useb | iex
```
## Menginstal TypeScript secara global
[TypeScript](https://www.typescriptlang.org/) sebaiknya diinstal secara global agar kita tidak perlu menginstalnya di setiap project yang akan menggunakan TypeScript. Untuk melakukannya, buka command prompt (Terminal di Windows 11, CMD di Windows 10 ke bawah), lalu jalankan perintah:
```shell
pnpm add -g typescript
```
Periksa apakah TypeScript sudah terinstal dan dapat dijalankan, jalankan perintah:
```shell
tsc -v
```
Perintah di atas mungkin akan menyebabkan error `UnauthorizedAccess`. Jika itu terjadi, maka buka Powershell **sebagai administrator**, lalu jalankan perintah:
```shell
Set-ExecutionPolicy RemoteSigned
```
# Hello, world! 🚀
Buat file bernama `index.ts`:
```ts
let nama: string;
nama = "Getch";
console.log(`Halo, ${nama}!`);
```
Jalankan melalui command prompt:
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

nama = "Arya Segara"; // valid
nama = 58; // tidak valid
umur = 20; // valid
umur = true; // tidak valid
```
## Inferention
Variabel yang diinisialisasi nilainya tidak perlu disebutkan tipe datanya, perilaku ini disebut inferensi (penyimpulan).
```ts
let angkatan = 21;
// `angkatan` otomatis bertipe `number`
```
Variabel `angkatan` tidak dapat diisi dengan data yang tidak bertipe `number`.
```ts
angkatan = "Include"
// tidak valid, karena `angkatan` harus bertipe `number`
```
## Sintaks `any`
Sintaks `any` digunakan untuk menonaktifkan *typechecking* sehingga dapat diisi oleh tipe apapun. Namun cara ini **sebaiknya dihindari**.
```ts
let data: any;
data = 21; // valid
data = "Getch"; // valid
data = true; // valid
```
## Union
Union adalah gabungan dari beberapa tipe data (agar dapat menerima berbagai tipe data).
```ts
let angkatan: number | string;
angkatan = 21; // valid
angkatan = "Getch"; // valid
angkatan = true; // tidak valid
```
## Sintaks `interface`
`interface` adalah tipe data untuk objek.
```ts
// membuat interface
interface Orang {
  id: number;
  nama: string;
}

// deklarasi variabel menggunakan tipe interface `Orang`
let orang1: Orang

// mengisi variabel
orang1 = {
  id: 1,
  nama: "Mohammad Mahfud Mahmodin",
};
```
## Sintaks `extends`
`extends` adalah sintaks untuk menambahkan *property*/*method* pada *interface*.
```ts
interface Mahasiswa extends Orang {
  fakultas: string;
}

interface Dosen extends Orang {
  mataKuliah: string;
}

let mahasiswa1: Mahasiswa;
let dosen1: Dosen;

mahasiswa1 = {
  id: 1,
  nama: "Gibran Rakabuming Raka",
  fakultas: "FISIP",
};

dosen1 = {
  id: 1,
  nama: "Anies Rasyid Baswedan",
  mataKuliah: "Ekonomi",
};
```
## Tipe data pada *parameter* *function*
Penulisannya sama seperti tipe data pada variabel.
```ts
// deklarasi function
function cetakNama(orang: Orang) {
  console.log(orang.nama);
}

// panggil function
cetakNama(orang1);
```
## Tipe data pada hasil *return* *function*
Tipe data hasil *return* *function* dituliskan di antara kurung tutup parameter dan kurung kurawal buka *function*.
```ts
// function yang tidak mengembalikan data
function cetakNama(orang: Orang): void {
  console.log(orang.nama);
}

// function yang mengembalikan string
function getNama(orang: Orang): string {
  return orang.nama;
}

let nama: string;

// simpan hasil return pemanggilan function ke variabel
nama = getNama(orang1);
```
## Generic
Generic adalah parameter untuk *interface* agar dinamis.
```ts
// membuat interface dengan generic
interface Komunitas<Type> {
  id: number;
  nama: string;
  getAnggota: () => Type;
}

// deklarasi variabel dengan tipe interface `Komunitas`
// mem-pass tipe `Mahasiswa` sebagai argumen generic
let komunitasMahasiswa: Komunitas<Mahasiswa>;

// deklarasi variabel dengan tipe interface `Komunitas`
// mem-pass tipe `Dosen` sebagai argumen generic
let komunitasDosen: Komunitas<Dosen>;

komunitasMahasiswa = {
  id: 1,
  nama: "Getch Community",
  getAnggota: () => {
    const anggota = mahasiswa1;
    return anggota;
  },
};

komunitasDosen = {
  id: 1,
  nama: "Asosiasi Dosen Bandung",
  getAnggota: () => {
    const anggota = dosen1;
    return anggota;
  },
};
```
## Sistem tipe struktural
Salah satu prinsip inti TypeScript adalah pemeriksaan tipe berfokus pada "bentuk" nilai. Prinsip ini disebut “sistem tipe struktural”.

Dalam sistem tipe struktural, jika dua objek mempunyai bentuk yang sama, maka keduanya dianggap bertipe sama. Contoh:
```ts
// membuat objek baru tanpa menyebutkan tipenya
const objek = {
  id: 2,
  nama: "Prabowo Subianto Djojohadikusumo",
};

// deklarasi variabel dengan tipe `Orang`
let orang: Orang;

// mengisi variabel `orang` dengan nilai dari `objek`
orang = objek;
// valid, padahal kita tidak menyebutkan bahwa `objek` bertipe `Orang`
```
## Array
Penulisan tipenya adalah tipe data elemen lalu diakhiri dengan sepasang kurung siku.

Contoh deklarasi array untuk kumpulan data primitif:
```ts
let numbers: number[];
numbers = [1, 2, 3];
```
Contoh deklarasi array untuk kumpulan data non-primitif (objek):
```ts
let students: Mahasiswa[];
students = [
  {
    id: 1,
    nama: "Romi Kusuma Bakti",
    fakultas: "Ilmu Komputer",
  },
  {
    id: 2,
    nama: "Abdul Muhaimin Iskandar",
    fakultas: "FISIP",
  },
  {
    id: 3,
    nama: "Ganjar Pranowo",
    fakultas: "Hukum",
  }
];
```
## Tuple
Tuple adalah objek dengan properti tanpa *key*, hanya *value*, seperti array tapi ditentukan setiap itemnya.
```ts
let item: [string, number];
item = ["Meja Belajar", 50_000];
```
# React dengan TypeScript
Buat project React baru menggunakan Vite:
```shell
pnpm create vite
```