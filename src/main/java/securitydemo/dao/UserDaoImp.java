package securitydemo.dao;

import org.springframework.stereotype.Repository;
import securitydemo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> listUsers() {
        return entityManager.createQuery("SELECT U FROM User U", User.class).getResultList();
    }

    @Override
    public Optional<User> read(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(User user) {
        User userForDelete = entityManager.find(User.class, user.getID());
        entityManager.remove(userForDelete);
    }


    @Override
    public User findByName(String userName){
        Optional<User> optional = entityManager.createQuery("SELECT user FROM User user WHERE user.name= ?1")
                .setParameter(1, userName)
                .getResultList().stream().findFirst();
        return optional.orElse(null);
//        return entityManager.find(User.class, userName);
    }
}
