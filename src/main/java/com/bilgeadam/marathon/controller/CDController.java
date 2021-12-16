package com.bilgeadam.marathon.controller;

import com.bilgeadam.marathon.entity.ArtistEntity;
import com.bilgeadam.marathon.entity.CDEntity;
import org.hibernate.Session;

import java.util.ArrayList;

public class CDController implements Controllable<CDEntity>{

    @Override
    public void delete(CDEntity entity) {
        try {
            CDEntity findEntity = find(entity.getId());
            if (findEntity != null) {
                Session session = databaseConnectionHibernate();
                session.getTransaction().begin();
                session.remove(findEntity);
                session.getTransaction().commit();
                System.out.println(("Silme Başarılı " + CDEntity.class));
            }
        } catch (Exception e) {
            System.out.println(("silme anında hata meydana geldi !!!!! " + CDEntity.class));
            e.printStackTrace();
        }
    }

    @Override
    public void update(CDEntity entity) {
        try {
            CDEntity findEntity = find(entity.getId());
            if (findEntity != null) {
                findEntity.setName(entity.getName());
                findEntity.setPrice(entity.getPrice());
                findEntity.setGenre(entity.getGenre());
                findEntity.setDiscountRate(entity.getPrice());
                Session session = databaseConnectionHibernate();
                session.getTransaction().begin();
                session.merge(findEntity);
                session.getTransaction().commit();
                System.out.println(("Güncelleme Başarılı " + CDEntity.class));
            }

        } catch (Exception e) {
            System.out.println(("güncelleme anında hata meydana geldi !!!!! " + CDEntity.class));
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<CDEntity> list() {
        return null;
    }

    @Override
    public CDEntity find(long id) {
        Session session = databaseConnectionHibernate();
        CDEntity cdEntity;
        try {
            cdEntity = session.find(CDEntity.class, id);

            if (cdEntity != null) {
                System.out.println("bulundu... ");
                return cdEntity;
            } else {
                System.out.println("Aradığınız kriterde sonuçlar bulunamadı ...");
                return null;
            }
        } catch (Exception e) {
            System.out.println(("find anında hata meydana geldi !!!!! "));
            e.printStackTrace();
        }
        return null;
    }
}
