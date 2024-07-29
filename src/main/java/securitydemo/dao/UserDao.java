package securitydemo.dao;

import securitydemo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void add(User user);

    List<User> listUsers();
    Optional<User> read(Long id);

    void update(User user);

    void delete(User user);

    User findByName(String userName);
}
