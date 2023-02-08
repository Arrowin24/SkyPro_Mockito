import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserRepositoryTest {
    UserRepository userRepository;

    @BeforeEach
    void init() {
        userRepository = new UserRepository();
    }

    @Test
    @DisplayName("When userRepository is created, then UsersList is empty")
    void emptyUserList() {
        List<User> users = userRepository.getAllUsers();
        Assertions.assertTrue(users.isEmpty());
    }

    @Test
    @DisplayName("When Repository contains users, then users are returned")
    void getAllUsers() {
        User user1 = new User("test", "test");
        User user2 = new User("test2", "test2");
        List<User> usersTest = List.of(user1, user2);
        userRepository.addUser(user1);
        userRepository.addUser(user2);
        List<User> users = userRepository.getAllUsers();
        Assertions.assertEquals(usersTest, users);
    }

    @Test
    @DisplayName("When Repository contains user, then user is returned")
    void getUserByLogin() {
        User userTest = new User("test", "test");
        userRepository.addUser(userTest);
        User user = userRepository.getUserByLogin("test").get();
        Assertions.assertEquals(userTest, user);
    }

    @Test
    @DisplayName("When Repository does not contain user, then NULL is returned")
    void getNullUserByLogin() {
        User userTest = new User("test", "test");
        userRepository.addUser(userTest);
        User user = userRepository.getUserByLogin("test2").orElse(null);
        Assertions.assertNull(user);
    }

    @Test
    @DisplayName("When Login is correct and Password is not correct, then NULL is returned")
    void getUserByCorrectPasswordAndNotCorrectLogin() {
        User userTest = new User("test", "test");
        userRepository.addUser(userTest);
        User user = userRepository.getUser("NotTest", "test").orElse(null);
        Assertions.assertNull(user);
    }
    @Test
    @DisplayName("When Password is correct and Login is not correct, then NULL is returned")
    void getUserByCorrectLoginAndNotCorrectPassword() {
        User userTest = new User("test", "test");
        userRepository.addUser(userTest);
        User user = userRepository.getUser("test", "NoTest").orElse(null);
        Assertions.assertNull(user);
    }
}
