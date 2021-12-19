package com.bilgeadam.marathon.controller;

import com.bilgeadam.marathon.entity.CDEntity;
import com.bilgeadam.marathon.entity.DVDEntity;
import com.bilgeadam.marathon.entity.VinylEntity;
import com.bilgeadam.marathon.enums.EDVDQuality;
import com.bilgeadam.marathon.enums.EGenre;
import com.bilgeadam.marathon.enums.EVinylRPM;
import com.bilgeadam.marathon.util.McUtils;
import org.hibernate.Session;

import java.util.ArrayList;

public class DVDController implements Controllable<DVDEntity>{
    @Override
    public void delete(DVDEntity entity) {
        try {
            DVDEntity findEntity = find(McUtils.readLong("Güncellemek istediğiniz ID'yi giriniz"));
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
    public void update(DVDEntity entity) {
        try {
            DVDEntity findEntity = find(McUtils.readLong("Güncellemek istediğiniz ID'yi giriniz"));
            Session session = databaseConnectionHibernate();
            if (findEntity != null) {
                session.getTransaction().begin();

                findEntity.setName(McUtils.readString("İsmi giriniz"));
                findEntity.setPrice(McUtils.readDouble("Fiyatı giriniz"));
                findEntity.setDiscountRate(McUtils.readDouble("İndirim oranını giriniz (0 - 1 --> Ex. 0,3)"));
                chooseGenre(findEntity);
                chooseQuality(findEntity);
                setDiscountedPrice(findEntity);

                session.merge(findEntity);
                session.getTransaction().commit();
            }
            System.out.println(("Güncelleme Başarılı " + DVDEntity.class));
        } catch (Exception e) {
            System.out.println(("güncelleme anında hata meydana geldi !!!!! " + DVDEntity.class));
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<DVDEntity> list() {
        return null;
    }

    @Override
    public DVDEntity find(long id) {
        Session session = databaseConnectionHibernate();
        DVDEntity dvdEntity;
        try {
            dvdEntity = session.find(DVDEntity.class, id);

            if (dvdEntity != null) {
                System.out.println("bulundu... ");
                return dvdEntity;
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

    public void setDiscountedPrice(DVDEntity entity) {
        entity.setPrice(entity.getPrice() - entity.getPrice() * entity.getDiscountRate());
    }

    private void chooseGenre(DVDEntity findEntity) {
        int genreChoice = (McUtils.readInt("Albümün tarzı nedir?\n " +
                "1) Blues \n" +
                " 2) Jazz\n" +
                " 3) Rock\n" +
                " 4) Country\n" +
                " 5) Dance\n" +
                " 6) Hip Hop\n" +
                "7) Soul\n"));
        switch (genreChoice) {
            case 1 -> findEntity.setGenre(EGenre.BLUES);
            case 2 -> findEntity.setGenre(EGenre.JAZZ);
            case 3 -> findEntity.setGenre(EGenre.ROCK);
            case 4 -> findEntity.setGenre(EGenre.COUNTRY);
            case 5 -> findEntity.setGenre(EGenre.DANCE);
            case 6 -> findEntity.setGenre(EGenre.HIPHOP);
            case 7 -> findEntity.setGenre(EGenre.SOUL);
        }
    }

    private void chooseQuality(DVDEntity findEntity) {
        int diaChoice = (McUtils.readInt("DVD'nin kalitesini seçiniz?\n " +
                " 1) LOW \n" +
                " 2) MEDIUM\n" +
                " 3) HIGH\n"));
        switch (diaChoice) {
            case 1 -> findEntity.setQuality(EDVDQuality.LOW);
            case 2 -> findEntity.setQuality(EDVDQuality.MEDIUM);
            case 3 -> findEntity.setQuality(EDVDQuality.HIGH);
        }
    }
}
