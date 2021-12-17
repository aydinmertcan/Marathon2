package com.bilgeadam.marathon.util;

import com.bilgeadam.marathon.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    // dış dünyada bununla bu classa erişim sağlayabileceğim.
    public static SessionFactory getSessionfactory() {
        return sessionFactory;
    }

    // hibernate ile bağlantı kuracağım yer
    private static final SessionFactory sessionFactory = sessionFactoryHibernate();

    // method
    private static SessionFactory sessionFactoryHibernate() {
        try {
            // instance
            Configuration configuration = new Configuration();

            // entity classlarımızı buraya ekleyeceğiz
            configuration.addAnnotatedClass(UserEntity.class);
            configuration.addAnnotatedClass(ArtistEntity.class);
            configuration.addAnnotatedClass(CDEntity.class);
            configuration.addAnnotatedClass(DVDEntity.class);
            configuration.addAnnotatedClass(VinylEntity.class);
           // configuration.addAnnotatedClass(.class);

            SessionFactory factory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
            return factory;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }


}
