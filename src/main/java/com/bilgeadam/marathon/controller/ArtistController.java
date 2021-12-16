package com.bilgeadam.marathon.controller;

import com.bilgeadam.marathon.entity.ArtistEntity;
import org.hibernate.Session;

import java.util.ArrayList;

public class ArtistController implements Controllable<ArtistEntity>{
    @Override
    public void delete(ArtistEntity entity) {
        try {
            ArtistEntity findEntity = find(entity.getId());
            if (findEntity != null) {
                Session session = databaseConnectionHibernate();
                session.getTransaction().begin();
                session.remove(findEntity);
                session.getTransaction().commit();
                System.out.println(("Silme Başarılı " + ArtistEntity.class));
            }
        } catch (Exception e) {
            System.out.println(("silme anında hata meydana geldi !!!!! " + ArtistEntity.class));
            e.printStackTrace();
        }

    }

    @Override
    public void update(ArtistEntity entity) {
        try {
            ArtistEntity findEntity = find(entity.getId());
            if (findEntity != null) {
                findEntity.setFirstName(entity.getFirstName());
                findEntity.setLastName(entity.getLastName());
                findEntity.setDescription(entity.getDescription());
                Session session = databaseConnectionHibernate();
                session.getTransaction().begin();
                session.merge(findEntity);
                session.getTransaction().commit();
                System.out.println(("Güncelleme Başarılı " + ArtistEntity.class));
            }

        } catch (Exception e) {
            System.out.println(("güncelleme anında hata meydana geldi !!!!! " + ArtistEntity.class));
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<ArtistEntity> list() {
        return null;
    }

    @Override
    public ArtistEntity find(long id) {
        return null;
    }
}
