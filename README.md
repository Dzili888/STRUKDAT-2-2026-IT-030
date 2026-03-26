# Tugas OOP: Smart Watering System (Sistem Penyiraman Otomatis)

## 1. Deskripsi Kasus
Proyek ini mensimulasikan sistem alat penyiram tanaman otomatis yang bisa diatur jadwalnya. Masalah utama yang diselesaikan adalah perbedaan kebutuhan air dan frekuensi penyiraman antar jenis tanaman. Misalnya, kaktus akan busuk jika terlalu sering disiram, sementara tulip akan layu jika kekurangan air atau jarak penyiramannya terlalu lama.


## 2. Class Diagram
```mermaid
classDiagram
    direction BT
    class Alat {
        <<interface>>
        +prosesnyiram(int ml, int hari) void
    }
    class Sistemnyiram {
        +prosesnyiram(int ml, int hari) void
    }
    class Tanaman {
        <<abstract>>
        #String nama
        #int batasMl
        #int batasHari
        #Alat alat
        +jadwal(int ml, int hari) void
        +cek(int ml, int hari) boolean*
    }
    class Kaktus {
        +cek(int ml, int hari) boolean
    }
    class Tulip {
        +cek(int ml, int hari) boolean
    }

    Sistemnyiram ..|> Alat : implementasi
    Kaktus --|> Tanaman : warisan
    Tulip --|> Tanaman : warisan
    Tanaman --> Alat : menggunakan