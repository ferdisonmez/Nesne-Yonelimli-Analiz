public class BasicUserFactory implements IUsersFactory {

    @Override
    public IUsers factoryMethod(String name, String password) {
        BasicUser basicUser=new BasicUser(name,password,"user");
        return basicUser;
    }
}
