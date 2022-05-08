import java.util.Scanner;
public class Arayuz {

    public static boolean kullanicigonder(String user, String pass, UserKontrol control) {
        if(control.UsernameControl(user)>=0 && control.PasswordControl(pass,control.UsernameControl(user))) {
            return true;
        }
        else
            return false;
    }

    public static void Islem(MerkeziBirim m) {

        Scanner sc = new Scanner(System.in);
        int secim;
        do {
            System.out.println("\nLütfen yapmak istediginiz islemi seciniz: \n1-Sicaklik Göster "
                    + "\n2-Sogutucu Ac\n3-Sogutucu Kapa\n4-Cikis");

            secim = sc.nextInt();
            sc.nextLine();

            switch(secim) {
                case 1:
                    System.out.println("Ortam sıcaklığı: "+m.SicaklikGetir());
                    break;
                case 2:
                    m.SogutucuAc();
                    break;
                case 3:
                    m.SogutucuKapa();
                    break;

            }

        }while(secim != 4);
    }

    public void arayuzBaslat() {

        Scanner sc = new Scanner(System.in);
        UserKontrol control = new UserKontrol();
        control.DatabaseUsers();

		MerkeziBirim ms = new MerkeziBirim();

		int sayac = 0;
		boolean temp = false;
		while(sayac<3) {

			System.out.println("Username: ");
			String user = sc.next();
			System.out.println("Password: ");
			String pass = sc.next();

			if(kullanicigonder(user,pass,control)) {
				temp = true;

				break;
			}
			sayac++;
		}

		if(temp)
			Islem(ms);
		else
			System.out.println("Your right of entry has expired");

	}

}

