public class Main {

    // Method reusable tanpa input user
    public static void setupTanaman(Tanaman tanaman, int ml, int hari) {
        System.out.println("setting buat " + tanaman.nama + "...");

        if (tanaman.cek(ml, hari)) {
            tanaman.jadwal(ml, hari);
        } else {
            System.out.println("--> Gagal diset! Parameter tidak sesuai.\n");
        }
    }

    public static void main(String[] args) {

        Alat mesin = new Sistemnyiram();

        Tanaman kaktus = new Kaktus("Kaktus Gurun", mesin);
        Tanaman tulip = new Tulip("Tulip Belanda", mesin);

        System.out.println("=== SETUP ALAT SIRAM OTOMATIS ===\n");

        // HARD CODED VALUE
        setupTanaman(kaktus, 40, 7);   // contoh: salah (biar keliatan validasi)
        System.out.println("\n-----------------------------\n");

        setupTanaman(tulip, 300, 8);  // contoh: benar

        System.out.println("\n=== SELESAI ===");
    }
}
