
public class MerkeziBirim {

        private int sicaklik;
        Eyleyici eyleyici = new Eyleyici();
        SicaklikAlgilayici algilayici=new SicaklikAlgilayici();

        public MerkeziBirim() {

        }
        public int SicaklikGetir() {
            sicaklik = algilayici.sicaklikoku();
            return sicaklik;
        }
        public void SogutucuAc() {
            System.out.println("Soğutucu açılıyor.");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Soğutucu açıldı.");
            this.sicaklik = eyleyici.sogutucuAc(sicaklik);
            algilayici.sicaklikguncelle(sicaklik);
        }

        public void SogutucuKapa() {
            System.out.println("Soğutucu kapatılıyor.");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Soğutucu kapatıldı.");
            eyleyici.sogutucuKapa(sicaklik);
        }

}
