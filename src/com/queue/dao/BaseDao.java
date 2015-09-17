package com.queue.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by JUSTIN on 2015/7/20.
 */
public class BaseDao {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
//        return this.sessionFactory.openSession();
    }


}
