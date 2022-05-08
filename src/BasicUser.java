public class BasicUser implements IUsers,IObserver{

    private String name;
    private String password;
    private String type;

    @Override
    public void print_log() {

        System.out.println("Basic user giriş yaptı.");
        System.out.println("İsmim: "+this.name);
    }

    public BasicUser(){

    }

    public BasicUser(String name,String pass,String type){
        this.name=name;
        this.password=pass;
        this.type=type;
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
        System.out.println("Basic user gelen mesaj alındı:"+message);
    }
}
