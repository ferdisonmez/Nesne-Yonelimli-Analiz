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
            this.sicaklik = eyleyici.sogutucuAc(sicaklik);
            algilayici.sicaklikguncelle(sicaklik);

        }

        public void SogutucuKapa() {
            eyleyici.sogutucuKapa(sicaklik);
        }

}
