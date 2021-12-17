package com.bilgeadam.marathon.controller;

import com.bilgeadam.marathon.entity.ArtistEntity;
import com.bilgeadam.marathon.entity.CDEntity;
import com.bilgeadam.marathon.enums.EGenre;
import com.bilgeadam.marathon.util.McUtils;
import org.hibernate.Session;

import java.util.ArrayList;

public class CDController implements Controllable<CDEntity>{

    @Override
    public void delete(CDEntity entity) {
        try {
            CDEntity findEntity = find(McUtils.readLong("Güncellemek istediğiniz ID'yi giriniz"));
            if (findEntity != null) {
                Session session = databaseConnectionHibernate();
                session.getTransaction().begin();
                session.remove(findEntity);
                session.getTransaction().commit();
                System.out.println("Silme Başarılı ");
            }
        } catch (Exception e) {
            System.out.println("silme anında hata meydana geldi !!!!! ");
            e.printStackTrace();
        }
    }

    @Override
    public void update(CDEntity entity) {
        try {
            CDController cdController = new CDController();
            CDEntity findEntity = find(McUtils.readLong("Güncellemek istediğiniz ID'yi giriniz"));
            Session session = databaseConnectionHibernate();
            if (findEntity != null) {
                session.getTransaction().begin();

                findEntity.setName(McUtils.readString("İsmi giriniz"));
                findEntity.setPrice(McUtils.readDouble("Fiyatı giriniz"));
                findEntity.setDiscountRate(McUtils.readDouble("İndirim oranını giriniz (0 - 1 --> Ex. 0,3)"));
                chooseGenre(findEntity);
                setDiscountedPrice(findEntity);
                session.merge(findEntity);
                session.getTransaction().commit();
            }
                System.out.println(("Güncelleme Başarılı " + CDEntity.class));
        } catch (Exception e) {
            System.out.println(("güncelleme anında hata meydana geldi !!!!! " + CDEntity.class));
            e.printStackTrace();
        }
    }

    private void chooseGenre(CDEntity findEntity) {
        int genreChoice = (McUtils.readInt("Albümün tarzı nedir?\n " +
                "1) Blues \n" +
                " 2) Jazz\n" +
                " 3) Rock\n" +
                " 4) Country\n" +
                " 5) Dance\n" +
                " 6) Hip Hop\n" +
                "7) Soul\n"));
        switch (genreChoice) {
            case 1:
                findEntity.setGenre(EGenre.BLUES);
                break;
            case 2:
                findEntity.setGenre(EGenre.JAZZ);
                break;
            case 3:
                findEntity.setGenre(EGenre.ROCK);
                break;
            case 4:
                findEntity.setGenre(EGenre.COUNTRY);
                break;
            case 5:
                findEntity.setGenre(EGenre.DANCE);
                break;
            case 6:
                findEntity.setGenre(EGenre.HIPHOP);
                break;
            case 7:
                findEntity.setGenre(EGenre.SOUL);
                break;
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

    public void setDiscountedPrice(CDEntity entity) {
        entity.setPrice(entity.getPrice() - entity.getPrice() * entity.getDiscountRate());
    }
}
