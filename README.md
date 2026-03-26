# Sistem Penyiraman Tanaman Rumah

## 1. Deskripsi Kasus
Merawat tanaman di rumah memiliki tantangan tersendiri karena setiap jenis tanaman memiliki kebutuhan takaran air dan frekuensi penyiraman yang jauh berbeda. Kaktus (tanaman gurun) akan membusuk jika terlalu banyak atau terlalu sering disiram, sedangkan Tulip akan layu jika kekurangan air atau jarak penyiramannya terlalu lama. 

Sistem ini memodelkan alat penyiram tanaman otomatis berbasis OOP. Program tidak akan langsung menyiram, melainkan memvalidasi jumlah mililiter air dan jeda harinya terlebih dahulu. Gunanya untuk menjaga kesehatan dan keberlangsungan hidup tumbuhan tersebut. hehe..

## 2. Class Diagram

```mermaid
classDiagram
  Tanaman <|-- Kaktus
  Tanaman <|-- Tulip
  Alat <|.. Sistemnyiram
  Tanaman --> Alat : menggunakan

  class Alat {
    <<Interface>>
    +prosesnyiram(ml: int, hari: int) void
  }
  
  class Sistemnyiram {
    +prosesnyiram(ml: int, hari: int) void
  }
  
  class Tanaman {
    <<Abstract>>
    #nama: String
    #batasMl: int
    #batasHari: int
    #alat: Alat
    +Tanaman(nama: String, batasMl: int, batasHari: int, alat: Alat)
    +jadwal(ml: int, hari: int) void
    +cek(ml: int, hari: int) boolean*
  }
  
  class Kaktus {
    +Kaktus(nama: String, alat: Alat)
    +cek(ml: int, hari: int) boolean
  }
  
  class Tulip {
    +Tulip(nama: String, alat: Alat)
    +cek(ml: int, hari: int) boolean
  }
  ```

  ## 3. Kode Program Java

Program ini dibagi menjadi 6 file terpisah (modular) untuk menerapkan prinsip OOP dengan rapi:

**1. `Alat.java`**
```java
public interface Alat {
    void prosesnyiram(int ml, int hari);
}
```

**2. `Sistemnyiram.java`**
```java
public class Sistemnyiram implements Alat {
    @Override
    public void prosesnyiram(int ml, int hari) {
        System.out.println("mesin nyala... nyiram " + ml + " ml tiap " + hari + " hari sekali");
    }
}
```

**3. `Tanaman.java`**
```java
public abstract class Tanaman {
    protected String nama;
    protected int batasMl;
    protected int batasHari;
    protected Alat alat;

    public Tanaman(String nama, int batasMl, int batasHari, Alat alat) {
        this.nama = nama;
        this.batasMl = batasMl;
        this.batasHari = batasHari;
        this.alat = alat;
    }

    public void jadwal(int ml, int hari) {
        System.out.println("setting jadwal otomatis buat " + nama + "...");
        if (alat != null) {
            alat.prosesnyiram(ml, hari); 
        }
    }

    public abstract boolean cek(int ml, int hari);
}
```
**4. `Kaktus.java`**
```java
public class Kaktus extends Tanaman {
    public Kaktus(String nama, Alat alat) {
        super(nama, 50, 6, alat); 
    }

    @Override
    public boolean cek(int ml, int hari) {
        if (ml > batasMl || hari < batasHari) {
            System.out.println("kaktus: airnya kebanyakan atau keseringan nyiram.");
            return false; 
        } else {
            System.out.println("kaktus: oke udah pas");
            return true; 
        }
    }
}
```

**5. `Tulip.java`**
```java
public class Tulip extends Tanaman {
    public Tulip(String nama, Alat alat) {
        super(nama, 300, 8, alat); 
    }

    @Override
    public boolean cek(int ml, int hari) { 
        if (ml < batasMl || hari > batasHari) {
            System.out.println("tulip: kurang air, atau kelamaan jarak nyiramnya. Nanti layu.");
            return false; 
        } else {
            System.out.println("tulip: mantap, udah pas.");
            return true;  
        }
    }
}
```

**6. `Main.java`**
```java
public class Main {

    public static void setupTanaman(Tanaman tanaman, int ml, int hari) {
        System.out.println("setting untuk " + tanaman.nama + "...");

        if (tanaman.cek(ml, hari)) {
            tanaman.jadwal(ml, hari);
        } else {
            System.out.println("Gagal diset! Parameter gak sesuai.\n");
        }
    }

    public static void main(String[] args) {

        Alat mesin = new Sistemnyiram();

        Tanaman kaktus = new Kaktus("Kaktus Gurun", mesin);
        Tanaman tulip = new Tulip("Tulip Belanda", mesin);

        setupTanaman(kaktus, 40, 7);   
        System.out.println("-----------------------------\n");
        setupTanaman(tulip, 300, 8);  
    }
}
```

## 4. Screenshot Output
![alt text](assets/output.png)

## 5. Penjelasan Prinsip OOP
* **Abstraction:** Kita pakai `abstract class Tanaman` karena konsep "Tanaman" itu kan masih umum banget. Nah, di dalamnya ada method `cek()` yang sengaja dibikin abstrak. Kenapa? Karena tiap spesies tanaman pasti punya cara dan takaran yang beda-beda buat ngecek aman atau nggaknya pas disiram.
* **Inheritance:** Ini kelihatan banget di class `Kaktus` dan `Tulip`. Daripada capek nulis ulang variabel dari nol, kedua class ini tinggal mewarisi (*extends*) semua sifat dasar—kayak `nama`, `batasMl`, dan `batasHari` dari class induknya, yaitu `Tanaman`.
* **Polymorphism:** Ini bagian yang paling kerasa logikanya. Kita pakai *method overriding* buat fungsi `cek()`. Nama fungsinya sama-sama `cek()`, tapi pas dieksekusi, Kaktus bakal bereaksi kalau kelebihan air, sedangkan Tulip bakal bereaksi kalau kekurangan air. Responnya beda tergantung jenis tanamannya.
* **Encapsulation:** variabel di dalam class `Tanaman` sengaja dikasih *modifier* `protected`. Jadi, data-data ini cuma bisa diakses dan diolah sama class itu sendiri dan `child` kelasnya.

## 6. Keunikan Program

Keunikannya ada di implementasi **Static Method** `setupTanaman()` di dalam *Main class* memisahkan alur eksekusi dengan sangat rapi. Ini memungkinkan sistem untuk menangani dan memvalidasi berbagai macam objek tanaman secara fleksibel melalui satu pintu blok fungsi, tanpa perlu repot menulis ulang percabangan kode untuk setiap tanaman baru.