package com.humanbooster;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        Metadata metadata = new MetadataSources(registry).buildMetadata();
        SessionFactory sessionFactory = metadata.buildSessionFactory();

        TaskDAO taskDAO = new TaskDAO(sessionFactory);

        Task task = new Task();
        task.setTitle("Ma nouvelle tâche");
        task.setDescription("");
        task.setCreatedAt(LocalDate.now());
        task.setUpdatedAt(LocalDate.now());
        taskDAO.create(task);

        Task taskRead = taskDAO.read(task.getId());
        System.out.println(taskRead.getTitle());

    }
}
