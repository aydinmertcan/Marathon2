package com.bilgeadam.marathon.controller;



import com.bilgeadam.marathon.util.HibernateUtil;
import com.bilgeadam.marathon.util.LogUtil;
import org.hibernate.Session;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.LinkedList;

public interface Controllable <T>{
    default void create(T entity){// ekleme
        try {
            Session session = databaseConnectionHibernate();
            session.getTransaction().begin();
            session.save(entity); // create
            session.getTransaction().commit();
            LogUtil.getInstance().logInfo("Ekleme islemi tamamlandi : >> " + entity);
        } catch (Exception e) {
            LogUtil.getInstance().logError("Ekleme aninda hata meydana geldi : >> " + this.getClass());
            e.printStackTrace();
        }
    }

    default void createMulti(ArrayList<T> entities){
        Session session = databaseConnectionHibernate();
        LinkedList<T> errorList = new LinkedList<>();

        for(T entity : entities){
            try {
                session.getTransaction().begin();
                session.save(entity); // create
            } catch (Exception e) {
                LogUtil.getInstance().logError("Ekleme aninda hata meydana geldi : >> " + this.getClass());
                e.printStackTrace();
                errorList.add(entity);
            } finally {
                session.getTransaction().commit();
            }
        }

        for(T errEntity : errorList) {
            LogUtil.getInstance().logError("HATA >>>>>" + errEntity.toString());
        }

    }

    public void delete(T entity);// silmek

    public void update(T entity);// güncelleme

    public ArrayList<T> list();

    public T find(long id);

    // gövdeli method
    default Session databaseConnectionHibernate() {
        return HibernateUtil.getSessionFactory().openSession();
    }
}
