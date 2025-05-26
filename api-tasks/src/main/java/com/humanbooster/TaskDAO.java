package com.humanbooster;

import jakarta.persistence.RollbackException;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TaskDAO {
    SessionFactory sessionFactory;

    public TaskDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(Task entity){
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        } catch (JDBCException | IllegalStateException | RollbackException e) {
            System.err.println("CRUD ERROR : Impossible to CREATE entity");
        }
    }

    public Task read(int id){
        Task entity = null;

        try (Session session = sessionFactory.openSession()) {
            entity = session.get(Task.class, id);
        } catch (JDBCException | IllegalStateException | RollbackException e) {
            System.err.println("CRUD ERROR : Impossible to READ entity of id " + id);
        }
        return entity;
    }

    public void update(Task entity){

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
        } catch (JDBCException | IllegalStateException | RollbackException e) {
            System.err.println("CRUD ERROR : Impossible to UPDATE entity");
        }
    }

    public void delete(int id){
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(session.get(Task.class, id));
            session.getTransaction().commit();
        } catch (JDBCException | IllegalStateException | RollbackException e) {
            System.err.println("CRUD ERROR : Impossible to DELETE entity of id " + id);
        }
    }
}
