import java.util.ArrayList;

public class UserCreating{

    IUsersFactory userFactory;

    public UserCreating(IUsersFactory iUsersFactory){
        this.userFactory=iUsersFactory;

    }
    public IUsers initUser(String name, String surname){
        IUsers iUsers = userFactory.factoryMethod(name,surname);
        //userFactory.factoryMethod(name,surname);
        iUsers.print_log();
        return iUsers;
    }
}
