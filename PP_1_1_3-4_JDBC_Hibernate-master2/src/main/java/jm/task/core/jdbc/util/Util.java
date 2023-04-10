package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.io.File;

public class Util {
    private static final SessionFactory session = buildSessionFactory();
    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure(new File("src\\main\\resources\\hibernate.cfg.xml")).buildSessionFactory();
        } catch ( Throwable e) {
            System.err.println("SeccionFactory creation failed " + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return session;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
