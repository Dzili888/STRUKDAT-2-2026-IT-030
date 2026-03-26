public class Sistemnyiram implements Alat {
    @Override
    public void prosesnyiram(int ml, int hari) {
        System.out.println("mesin nyala... nyiram " + ml + " ml tiap " + hari + " hari sekali");
    }
}