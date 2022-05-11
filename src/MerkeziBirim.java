
public class MerkeziBirim {

        private int sicaklik;
        private boolean sogutucuAcik;
        Eyleyici eyleyici = new Eyleyici();
        SicaklikAlgilayici algilayici=new SicaklikAlgilayici();
        public MerkeziBirim(boolean sogutucuAcik) {
            this.sogutucuAcik=sogutucuAcik;
        }

        public int SicaklikGetir() {
            sicaklik = algilayici.sicaklikoku();
            return sicaklik;
        }
        public void SogutucuAc() {

            if(this.sogutucuAcik==true){
                System.out.println("Sogutucu zaten acik!");
                return;
            }
            else {this.sogutucuAcik=true;}

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

             if(this.sogutucuAcik==false){
                 System.out.println("Sogutucu zaten kapali!");
                 return;
             }
             else {this.sogutucuAcik=false;}

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
