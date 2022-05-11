public class AdminUser implements IUsers,IObserver{

    private String name;
    private String password;
    public String type;

    @Override
    public void print_log() {
        System.out.println("Admin türünde bir kullanıcı kaydoldu.");
        System.out.println("İsmim: "+this.name);
    }

    public AdminUser(String name,String pass,String type){
        this.name=name;
        this.password=pass;
        this.type=type;
    }
    public AdminUser(){

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void sendStateMessage(String message) {
        System.out.println(this.name+" Admin user gelen mesaj alındı:"+message);
    }
}
