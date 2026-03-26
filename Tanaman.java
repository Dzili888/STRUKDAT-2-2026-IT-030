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