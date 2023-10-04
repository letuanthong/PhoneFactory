package com.devking.utils;


import com.devking.domain.Manufacture;
import com.devking.domain.Phone;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {

    @Getter
    private static final SessionFactory sessionFactory;

    static {
//        try {
//            Configuration cfg = new Configuration().configure();
//            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
//                    .applySettings(cfg.getProperties());
//            sessionFactory = cfg.buildSessionFactory(builder.build());

            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
//
            cfg.addAnnotatedClass(Phone.class);
            cfg.addAnnotatedClass(Manufacture.class);
            ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
            sessionFactory = cfg.buildSessionFactory(registry);
//        } catch (Throwable ex) {
//            // Log the exception.
//            System.err.println("Initial SessionFactory creation failed." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
    }

}