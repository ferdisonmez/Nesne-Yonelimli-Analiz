import java.sql.*;
import java.util.ArrayList;

public class UserKontrol{

    ArrayList<String> names=new ArrayList<String>();
    ArrayList<String> passwords=new ArrayList<String>();
    ArrayList<String> types=new ArrayList<String>();

    public void DatabaseUsers(){
        DatabaseConnection instance = DatabaseConnection.getInstance();
        Connection conn = DatabaseConnection.getConnection();

        try {
            Statement statement = conn.createStatement();

            String query="SELECT *" + "FROM public.users";
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                names.add(result.getString("name"));
                passwords.add(result.getString("password"));
                types.add(result.getString("usertype"));
            }
        }catch(Exception e) {
            System.out.println(e);
        }
    }

    public int UsernameControl(String username) {
        int index = 0;
        for(String name: names) {
            if(name.equals(username)) {
                return index;
            }
            index++;
        }
        System.out.println("Kullanıcı Adı Hatalı");
        return -1;
    }

    public boolean PasswordControl(String password,int index) {
        if(passwords.get(index).equals(password)) {
            System.out.println("Giriş Başarılı");
            return true;
        }
        else {
            System.out.println("Parola Yanlış");
            return false;
        }
    }
}