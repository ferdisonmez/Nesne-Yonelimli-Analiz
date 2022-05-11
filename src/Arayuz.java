import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Arayuz {

    //Kullanicinin basariyla girip girmedigini döndürür.
    public static boolean kullanicigonder(String user, String pass, UserKontrol control) {
        if(control.UsernameControl(user)>=0 && control.PasswordControl(pass,control.UsernameControl(user))) {
            return true;
        }
        else
            return false;
    }

    //Merkezi birimin görevlerini yaptigi kisim.
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

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserKontrol control = new UserKontrol();
        control.DatabaseUsers();//Veritabanindan kullanicilari okur.
		MerkeziBirim ms = new MerkeziBirim(false);//Merkezi birim sogutucu kapali baslatilir.

		int basarisizGiris = 0;
		boolean girisBasarili = false;

        //Ana menu
        while (true) {
            System.out.println("*******  Menu  ********\n" +
                    "1 - Kullanici Girisi\n" +
                    "2 - Kullanıcı olustur\n" +
                    "3 - Kullanıcılara Mesaj Gönder:\n"+
                    "4 - Cikis\n");
            Scanner scanner = new Scanner(System.in);
            int islem = scanner.nextInt();
            //Islem=1 merkezi birimin menusune gider.
            if (islem == 1) {

                while(basarisizGiris<3) {

                    System.out.println("Kullanici Adi: ");
                    String user = sc.next();
                    System.out.println("Parola: ");
                    String pass = sc.next();

                    if(kullanicigonder(user,pass,control)) {
                        girisBasarili = true;
                        break;
                    }
                    basarisizGiris++;
                }
                if(girisBasarili){Islem(ms);}
                else {System.out.println("3 Kere Basarisiz Kullanıcı Giris.");}
                //Islem=2 kullanici ekler.
            } else if (islem == 2) {
                while (true) {
                    if (KullaniciOlustur() == 3) {
                        break;
                    }
                }
                //Islem=3 Veritabanindaki tum kullanicilara mesaj gonderir.
            } else if (islem == 3) {
                sendStateMachineMessage();
            } else {
                System.out.println("Sistem sonlandiriliyor.");
                break;
            }
        }
	}

    public static int KullaniciOlustur(){


        String query="INSERT INTO public.users(name, password, usertype)"+ "VALUES (?, ?, ?)";
        DatabaseConnection instance = DatabaseConnection.getInstance();
        Connection conn = DatabaseConnection.getConnection();

        UserCreating userCreating1=new UserCreating(new AdminUserFactory());
        UserCreating userCreating2=new UserCreating(new BasicUserFactory());

        IUsers iUser;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Kullanıcı türünü giriniz: 1-Admin 2-Basic User 3- Ana Menü");
        int type=scanner.nextInt();
        if(type==1){
            scanner.nextLine();
            System.out.println("Kullanici adinizi giriniz:");
            String name=scanner.nextLine();
            System.out.println("Parolanizi giriniz:");
            String password=scanner.nextLine();
            String usertype="admin";

            //Factory deseni ile yeni bir kullanici olusturulur.
            iUser=userCreating1.initUser(name,password);
            name=iUser.getName();
            password=iUser.getPassword();

            try{
                PreparedStatement pstmt = conn.prepareStatement(query);

                pstmt.setString(1,name);
                pstmt.setString(2,password);
                pstmt.setString(3, "admin");
                pstmt.executeUpdate();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                conn.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
            return type;

        }
        else if(type==2){
            scanner.nextLine();
            System.out.println("Kullanici adinizi giriniz:");
            String name=scanner.nextLine();
            System.out.println("Parolanizi giriniz:");
            String password=scanner.nextLine();
            String usertype="user";

            //Factory deseni ile yeni bir kullanici olusturulur.
            iUser=userCreating2.initUser(name,password);
            name=iUser.getName();
            password=iUser.getPassword();

            try{
                PreparedStatement pstmt = conn.prepareStatement(query);

                pstmt.setString(1,name);
                pstmt.setString(2,password);
                pstmt.setString(3, "user");
                pstmt.executeUpdate();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                conn.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
            return type;
        }
        else {
            try {
                conn.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
            return 3;
        }
    }
    public static void sendStateMachineMessage(){

        ArrayList<IObserver> iUsers=new ArrayList<>();
        DatabaseConnection instance = DatabaseConnection.getInstance();
        Connection conn = DatabaseConnection.getConnection();

        //Tum kullanicilar veritabanından okunur.
        String query="SELECT *" + "FROM public.users";
        try {

            Statement qr=conn.createStatement();
            ResultSet rs=qr.executeQuery(query);
            while (rs.next()){
                if(rs.getString("usertype").equals("admin")){

                    iUsers.add(new AdminUser(rs.getString("name"), rs.getString("password"),"admin"));
                }
                else {
                    iUsers.add(new BasicUser(rs.getString("name"), rs.getString("password"),"user"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Observer deseni ile tum kullanicilara mesaj gönderme
        Publisher publisher=new Publisher(iUsers);
        publisher.notifying("Akıllı Cihaz Arızalandı.");

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

