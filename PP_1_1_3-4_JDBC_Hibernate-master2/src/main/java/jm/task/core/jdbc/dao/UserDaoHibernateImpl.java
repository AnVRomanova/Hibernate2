package jm.task.core.jdbc.dao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static SessionFactory sessionFactory;
    protected EntityManager manager;

    public UserDaoHibernateImpl() {

    }

    //@Override
    public void createUsersTable() {
    }

   // @Override
    public void dropUsersTable() {
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {

        try (Session session = Util.getSessionFactory().openSession()) {
            session.getTransaction().begin();

            Object persistentInstance = session.get(User.class, id);
            if (persistentInstance != null) {
                session.delete(persistentInstance);
            }

            session.getTransaction().commit();
            System.out.printf("deleted user № " + id );
        }


    }

   @Override
    public List<User> getAllUsers() {
       List<User> data = new ArrayList<>();
       try (Session session = Util.getSessionFactory().openSession()) {
           session.getTransaction().begin();
           CriteriaBuilder builder = session.getCriteriaBuilder();
           CriteriaQuery<User> criteria = builder.createQuery(User.class);
           criteria.from(User.class);
           data = session.createQuery(criteria).getResultList();
       }
       return data;

    }

   // @Override
    public void cleanUsersTable() {
    }
}
