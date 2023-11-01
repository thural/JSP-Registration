package com.bdc.javawebapp.service_impls;

import com.bdc.javawebapp.models.User;
import com.bdc.javawebapp.services.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.SessionFactoryProvider;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public List<User> getAll() {
        List<User> userList;
        try {
            // get session factory
            SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
            Session session = sessionFactory.openSession();
            // start a transaction
            Transaction transaction = session.beginTransaction();
            // select and list all rows of the User table
            String hql = "FROM User";
            Query query = session.createQuery(hql);
            // store the list
            userList = query.list();
            // commit transaction
            transaction.commit();
            sessionFactory.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return userList;
    }

    @Override
    public boolean add(User user) {
        boolean result = false;
        Transaction transaction = null;
        try {
            // get session factory
            SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
            Session session = sessionFactory.openSession();
            // start a transaction
            transaction = session.beginTransaction();
            // save the user objects
            session.save(user);
            // store the result
            result = true;
            // commit transaction
            transaction.commit();
            sessionFactory.close();
        } catch (Exception e) {
            // rollback in case transaction failure
            if (transaction != null) transaction.rollback();
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean update(User user) {
        boolean result = false;
        try {
            // get session factory
            SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
            Session session = sessionFactory.openSession();
            // start a transaction
            Transaction transaction = session.beginTransaction();
            // save persisten object to db
            session.saveOrUpdate(user);
            // store the result
            result = true;
            // commit transaction
            transaction.commit();
            sessionFactory.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean deleteById(long id) {
        boolean result = false;
        try {
            // get session factory
            SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
            Session session = sessionFactory.openSession();
            // start a transaction
            Transaction transaction = session.beginTransaction();
            // get the user from db with the specified id
            User user = session.get(User.class, id);
            // if user exists delete the user
            if (user != null) {
                session.remove(user);
                // store the result
                result = true;
            }
            // commit transaction
            transaction.commit();
            sessionFactory.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean authenticate(String email, String password) {
        boolean result = false;
        try {
            // get session factory
            SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
            Session session = sessionFactory.openSession();
            // start a transaction
            Transaction transaction = session.beginTransaction();
            // find the user using HQL matching the credentials
            String hql = "FROM User u WHERE u.email = :email AND u.password = :password";
            Query query = session.createQuery(hql);
            query.setParameter("email", email);
            query.setParameter("password", password);
            List results = query.list();
            // store the execution result
            result = !results.isEmpty();
            // commit transaction
            transaction.commit();
            sessionFactory.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public User getOne(String email, String password) {
        User user = null;
        try {
            // get session factory
            SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
            Session session = sessionFactory.openSession();
            // start a transaction
            Transaction transaction = session.beginTransaction();
            // find the matching user using HQL query
            String hql = "FROM User u WHERE u.email = :email AND u.password = :password";
            Query query = session.createQuery(hql);
            query.setParameter("email", email);
            query.setParameter("password", password);
            // get list of the result
            List results = query.list();
            // store the user from the list
            user = (User) results.get(0);
            // commit transaction
            transaction.commit();
            sessionFactory.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
}