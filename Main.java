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

        setupTanaman(kaktus, 40, 7);   // contoh yg salah 
        System.out.println("-----------------------------\n");

        setupTanaman(tulip, 300, 8);  // contoh yg benar
    }
}
