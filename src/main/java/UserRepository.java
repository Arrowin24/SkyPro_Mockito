import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    private final List<User> users = new ArrayList<>();

    public Optional<User> getUserByLogin(String login) {
        return users.stream()
                    .filter(user -> user.getLogin().equals(login))
                    .findAny();
    }

    public Optional<User> getUser(String login, String password) {
        return users.stream()
                    .filter(user -> user.equals(new User(login, password)))
                    .findAny();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getAllUsers(){
        return users;
    }


}
