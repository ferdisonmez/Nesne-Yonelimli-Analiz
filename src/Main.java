import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        while (true) {
            System.out.println("*******  Menu  ********\n" +
                    "1 - Kullanici Girisi\n" +
                    "2 - Kullanıcı olustur\n" +
                    "3 - Kullanıcılara Mesaj Gönder:\n");
            Scanner scanner = new Scanner(System.in);
            int islem = scanner.nextInt();
            if (islem == 1) {
                Arayuz arayuz = new Arayuz();
                arayuz.arayuzBaslat();
            } else if (islem == 2) {
                while (true) {
                    if (KullaniciOlustur() == 3) {
                        break;
                    }
                }
            } else if (islem == 3) {
                sendStateMachineMessage();
            } else {
                System.out.println("Devam edilemiyor.");
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
        System.out.println("Kullanıcı türünü giriniz: 1-Admin 2-Basic User");
        int type=scanner.nextInt();
        if(type==1){
            scanner.nextLine();
            System.out.println("Adinizi giriniz:");
            String name=scanner.nextLine();
            System.out.println("Parolanizi giriniz:");
            String password=scanner.nextLine();
            String usertype="admin";
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
            System.out.println("Adinizi giriniz:");
            String name=scanner.nextLine();
            System.out.println("Parolanizi giriniz:");
            String password=scanner.nextLine();
            String usertype="user";
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

        Publisher publisher=new Publisher(iUsers);
        publisher.notifying("Akıllı Cihaz Arızalandı.");

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
