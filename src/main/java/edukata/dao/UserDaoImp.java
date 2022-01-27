package edukata.dao;

import edukata.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao{
    @PersistenceContext
    @Autowired
    EntityManager em;

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public User getUser(Long id) {
        User user = em.createQuery("select u from User as u where u.id=:id", User.class)
                .setParameter("id", id).getSingleResult();
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUser(id);
        if(user!=null){
            em.remove(user);
        }
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public List<User> getUsers() {
        List <User> userList = new ArrayList<>();
        userList = em.createQuery("select u from User as u").getResultList();
        return userList;
    }

}
