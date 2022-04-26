import java.util.Random;

public class SicaklikAlgilayici {

    private int sicaklik;
    public SicaklikAlgilayici() {
        Random random = new Random();
        this.sicaklik = random.nextInt(50);

    }
    public int sicaklikoku() {
        return sicaklik;
    }
    public void sicaklikguncelle(int sicaklik) {
        this.sicaklik = sicaklik;
    }

}
