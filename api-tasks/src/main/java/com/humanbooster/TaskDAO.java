package com.humanbooster;

import jakarta.persistence.RollbackException;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class TaskDAO {
    SessionFactory sessionFactory;

    public TaskDAO() {
        this.sessionFactory = Connector.getInstance().getSessionFactory();
    }

    public Task create(Task entity){
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            return entity;
        } catch (JDBCException | IllegalStateException | RollbackException e) {
            System.err.println("CRUD ERROR : Impossible to CREATE entity");
        }
        return null;
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

    public Task update(Task entity){
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
            return entity;
        } catch (JDBCException | IllegalStateException | RollbackException e) {
            System.err.println("CRUD ERROR : Impossible to UPDATE entity");
        }
        return null;
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


    public List<Task> readAll() {
        List<Task> entities = null;

        try (Session session = sessionFactory.openSession()) {
            entities = session.createQuery("FROM Task", Task.class).list();
        } catch (JDBCException | IllegalStateException | RollbackException e) {
            System.err.println("CRUD ERROR : Impossible to READ all entities");
        }
        return entities;
    }
}
