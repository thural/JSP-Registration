import com.bdc.firstservletapp.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.query.Query;
import utils.SessionFactoryProvider;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction transaction = entityManager.getTransaction();
//
//        try{
//            transaction.begin();
//
//            User guest = new User("RandomDude");
//            guest.setPhoneNumber("+9946754532");
//            entityManager.persist(guest);
//
//            transaction.commit();
//        } finally {
//            if (transaction.isActive()) transaction.rollback();
//            entityManager.close();
//            entityManagerFactory.close();
//        }

//        User user = null;
//        Transaction transaction = null;
//
//        Configuration config = new Configuration();
//        config.configure();
//        // local SessionFactory bean created
//        SessionFactory sessionFactory = config.buildSessionFactory();
//        Session sessionContext = sessionFactory.getCurrentSession();
//        try (Session session = sessionContext.getSessionFactory().openSession()) {
//            // start a transaction
//            transaction = session.beginTransaction();
//
//            String hql = "FROM UserEntity u WHERE u.email = email AND u.password = password";
//            Query query = session.createQuery(hql);
//            List results = query.list();
//
//            System.out.println("logged user: " + results.get(0));
//
////            // get Student entity using get() method
////            user = session.get(User.class, email, password);
////            System.out.println(user.getFirstName());
////            System.out.println(user.getEmail());
//
//            // commit transaction
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }

//        User user = null;
//        Transaction transaction = null;
//
//        Configuration config = new Configuration().configure("hibernate.cfg.xml");
//        // local SessionFactory bean created
//        SessionFactory sessionFactory = config.buildSessionFactory();
//
//        try (Session session = sessionFactory.getCurrentSession().getSessionFactory().openSession()) {
//            // start a transaction
//            transaction = session.beginTransaction();
////
//            // get Student entity using get() method
//            user = session.get(User.class, 1);
//            System.out.println(user.getFirstName());
//            System.out.println(user.getEmail());
//
//            // commit transaction
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }

        try {
            // get session factory
            SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
            Session session = sessionFactory.openSession();

            // start a transaction
            Transaction transaction = session.beginTransaction();

            String hql = "FROM User u WHERE u.email = :email AND u.password = :password";
            Query query = session.createQuery(hql);
            query.setParameter("email", "tural.musaibov@gmail.com");
            query.setParameter("password", "banana1256");
            List results = query.list();
            User user = (User) results.get(0);
            System.out.println(user.getFirstName());
            System.out.println(user.getEmail());


//            UserEntity user = session.get(UserEntity.class, 1);
//            System.out.println(user.getFirstName());
//            System.out.println(user.getEmail());


//            UserEntity guest = new UserEntity("randomGeeky");
//            guest.setPhoneNumber("+9945776752613");
//
//            // get Student entity using get() method
//            user = session.get(User.class, 1);
//            System.out.println(user.getFirstName());
//            System.out.println(user.getEmail());

//            User guest = new User("guestOne");
//            session.persist(guest);

            // commit transaction
//            transaction.commit();
            sessionFactory.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //return user;
    }
}
