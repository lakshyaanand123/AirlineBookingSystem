package interfaces;

import model.User;
import java.util.List;

public interface UserOperations {

    boolean registerUser(User user);

    User loginUser(String email, String password);

    List<User> getAllUsers();

    boolean updateUser(User user);

    boolean deleteUser(int userId);
}