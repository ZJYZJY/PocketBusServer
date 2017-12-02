package bus.orm;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class MySessionFactory {

    private static SessionFactory sessionFactory;

    private MySessionFactory(){

    }

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            Configuration config = new Configuration().configure();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
            sessionFactory = config.buildSessionFactory(serviceRegistry);
            return sessionFactory;
        } else {
            return sessionFactory;
        }
    }
}
