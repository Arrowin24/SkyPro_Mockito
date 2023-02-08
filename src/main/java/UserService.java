import exception.UserNotUniqueException;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> getAllLogins() {
        return userRepository.getAllUsers().stream().map(User::getLogin).toList();
    }

    public void createUser(String login, String password) {
        User user = new User(login, password);
        if (isCorrectLoginAndPassword(login, password) && isUnique(user)) {
            userRepository.addUser(user);
        }
    }

    public boolean isExist(String login, String password) {
        return userRepository.getUser(login, password).isPresent();
    }

    private boolean isCorrectLoginAndPassword(String login, String password) {
        if (login.isBlank() || password.isBlank()) {
            throw new IllegalArgumentException("Некорректный логин или пароль");
        }
        return true;
    }

    private boolean isUnique(User user) {
        if (userRepository.getAllUsers().contains(user)) {
            throw new UserNotUniqueException();
        }
        return true;
    }

}
