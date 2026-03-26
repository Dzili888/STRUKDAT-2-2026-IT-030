public class Tulip extends Tanaman {
    public Tulip(String nama, Alat alat) {
        super(nama, 300, 8, alat);
    }

    @Override
    public boolean cek(int ml, int hari) { 
        if (ml < batasMl || hari < batasHari) {
            System.out.println("tulip: kurang air, atau kelamaan jarak nyiramnya. Nanti layu.");
            return false; 
        } else {
            System.out.println("tulip: mantap, udah pas.");
            return true;  
        }
    }
}