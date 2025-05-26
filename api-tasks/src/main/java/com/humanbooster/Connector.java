package com.humanbooster;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Connector {
    private static Connector instance;
    private final SessionFactory sessionFactory;

    private Connector() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        Metadata metadata = new MetadataSources(registry).buildMetadata();
        sessionFactory = metadata.buildSessionFactory();
    }

    public static Connector getInstance() {
        if (instance == null){
            instance = new Connector();
        }
        return instance;
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
