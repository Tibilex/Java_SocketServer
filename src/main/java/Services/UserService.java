package Services;

import Models.User;
import Repositories.UserRepo;
import java.util.List;

public class UserService {
    private final UserRepo repository = new UserRepo();

    public UserService() {}

    public User getById(int id) {
        return repository.getById(id);
    }

    public void saveUser(User user) {
        repository.save(user);
    }

    public List<User> getAllUsers() {
        return repository.getAll();
    }
}
