public class Kaktus extends Tanaman {
    public Kaktus(String nama, Alat alat) {
        super(nama, 50, 6, alat);
    }

    @Override
    public boolean cek(int ml, int hari) {
        if (ml < batasMl || hari > batasHari) {
            System.out.println("kaktus: airnya kebanyakan atau keseringan nyiram.");
            return false; 
        } else {
            System.out.println("kaktus: oke udah pas");
            return true; 
        }
    }
}