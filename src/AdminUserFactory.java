public class AdminUserFactory implements IUsersFactory {

    public IUsers factoryMethod(String name, String password) {
        AdminUser adminUser=new AdminUser(name,password,"admin");
        return adminUser;
    }
}
