package securitydemo.service;

import securitydemo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void add(User user);
    List<User> listUsers();

    Optional<User> read(Long id);

    void update(User user);

    void delete(User user);

    User findByName(String userName);
}
