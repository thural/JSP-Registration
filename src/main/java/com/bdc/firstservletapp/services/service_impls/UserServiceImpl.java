package com.bdc.firstservletapp.services.service_impls;

import com.bdc.firstservletapp.models.User;
import com.bdc.firstservletapp.services.UserService;
import com.bdc.firstservletapp.utils.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserServiceImpl implements UserService {

    SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();

    @Override
    public List<User> getAll() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM User";
            return session.createQuery(hql, User.class).getResultList();
        } catch (Exception e) {
            System.err.println("failed to query users: " + e.getMessage());
            return List.of();
        }
    }

    public boolean addOne(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("failed to add user: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("failed to update user: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteById(long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) session.remove(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.err.println("failed to delete user: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean authenticate(String email, String password) {
        try (Session session = sessionFactory.openSession()) {

            String hql = "FROM User u WHERE u.email = :email AND u.password = :password";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("password", password);
            query.setParameter("email", email);
            User result = query.uniqueResult();

            return result != null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public User getOne(String email, String password) {
        try (Session session = sessionFactory.openSession()) {

            String hql = "FROM User u WHERE u.email = :email AND u.password = :password";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("email", email);
            query.setParameter("password", password);

            return query.uniqueResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}