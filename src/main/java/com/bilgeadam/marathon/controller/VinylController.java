package com.bilgeadam.marathon.controller;

import com.bilgeadam.marathon.entity.VinylEntity;
import com.bilgeadam.marathon.enums.EGenre;
import com.bilgeadam.marathon.enums.EVinlyDiameter;
import com.bilgeadam.marathon.enums.EVinylRPM;
import com.bilgeadam.marathon.util.HibernateUtil;
import com.bilgeadam.marathon.util.McUtils;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class VinylController implements Controllable<VinylEntity>{
    @Override
    public void delete(VinylEntity entity) {
        try {
            VinylEntity findEntity = find(McUtils.readLong("Güncellemek istediğiniz ID'yi giriniz"));
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
    public void update(VinylEntity entity) {
        try {
            VinylEntity findEntity = find(McUtils.readLong("Güncellemek istediğiniz ID'yi giriniz"));
            Session session = databaseConnectionHibernate();
            if (findEntity != null) {
                session.getTransaction().begin();

                findEntity.setName(McUtils.readString("İsmi giriniz"));
                findEntity.setPrice(McUtils.readDouble("Fiyatı giriniz"));
                findEntity.setDiscountRate(McUtils.readDouble("İndirim oranını giriniz (0 - 1 --> Ex. 0,3)"));
                chooseGenre(findEntity);
                setDiscountedPrice(findEntity);
                chooseDiameter(findEntity);
                chooseRPM(findEntity);

                session.merge(findEntity);
                session.getTransaction().commit();
            }
            System.out.println(("Güncelleme Başarılı " + VinylEntity.class));
        } catch (Exception e) {
            System.out.println(("güncelleme anında hata meydana geldi !!!!! " + VinylEntity.class));
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<VinylEntity> list() {
        Session session = HibernateUtil.getSessionfactory().openSession();
        String hql = "select vE from VinylEntity as vE ";

        TypedQuery<VinylEntity> typedQuery = session.createQuery(hql, VinylEntity.class);
        ArrayList<VinylEntity> entities = (ArrayList<VinylEntity>) typedQuery.getResultList();

        for (VinylEntity temp : entities) {
            System.out.println(temp);
        }
        return null;
    }

    @Override
    public VinylEntity find(long id) {
        Session session = databaseConnectionHibernate();
        VinylEntity vinylEntity;
        try {
            vinylEntity = session.find(VinylEntity.class, id);

            if (vinylEntity != null) {
                System.out.println("Bulundu... ");
                return vinylEntity;
            } else {
                System.out.println("Aradığınız kriterde sonuçlar bulunamadı ...");
                return null;
            }
        } catch (Exception e) {
            System.out.println(("Bulma anında hata meydana geldi... "));
            e.printStackTrace();
        }
        return null;
    }

    public void setDiscountedPrice(VinylEntity entity) {
        entity.setPrice(entity.getPrice() - entity.getPrice() * entity.getDiscountRate());
    }

    private void chooseGenre(VinylEntity findEntity) {
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

    private void chooseDiameter(VinylEntity findEntity) {
        int diaChoice = (McUtils.readInt("Plağın boyutu nedir?\n " +
                " 1) 7\" \n" +
                " 2) 10\"\n" +
                " 3) 12\"\n"));
        switch (diaChoice) {
            case 1 -> findEntity.setDiameter(EVinlyDiameter.SEVEN);
            case 2 -> findEntity.setDiameter(EVinlyDiameter.TEN);
            case 3 -> findEntity.setDiameter(EVinlyDiameter.TWELVE);
        }
    }

    private void chooseRPM(VinylEntity findEntity) {
        int diaChoice = (McUtils.readInt("Plağın hızını seçiniz?\n " +
                " 1) LOW \n" +
                " 2) MEDIUM\n" +
                " 3) HIGH\n"));
        switch (diaChoice) {
            case 1 -> findEntity.setSpeed(EVinylRPM.LOW);
            case 2 -> findEntity.setSpeed(EVinylRPM.MEDIUM);
            case 3 -> findEntity.setSpeed(EVinylRPM.HIGH);
        }
    }
}
