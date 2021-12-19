package com.bilgeadam.marathon.view;

import com.bilgeadam.marathon.entity.CDEntity;
import com.bilgeadam.marathon.entity.DVDEntity;
import com.bilgeadam.marathon.entity.VinylEntity;
import com.bilgeadam.marathon.enums.EGenre;
import com.bilgeadam.marathon.util.HibernateUtil;
import com.bilgeadam.marathon.util.McUtils;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class UserPanel {
    public void showMenuItems(){
        System.out.println();
        int choice = 0;
        do {
            choice = McUtils.readInt("\nLütfen ne yapmak istediğinizi seçiniz:\n" +
                    "\t1) Son Eklenen 10 Albüm\n" +
                    "\t2) İndirimdeki 15 Albüm\n" +
                    "\t3) Türlere Göre Albümler\n" +
                    "\t4) Sanatçıya Göre Albümler\n" +
                    "\t5) En Çok Sipariş Edilenler\n" +
                    "\t6) En Popüler Sanatçı\n" +
                    "\t99) Exit\n");
            switch (choice) {
                case 1 -> showLastestAlbums();
                case 2 -> showDiscountedAlbums();
                case 3 -> showAlbumsWRTGenre();
                case 4 -> showAlbumsWRTArtist();
                case 5 -> showAlbumsWRTArtist();
                case 99 -> System.exit(-1);
            }
        } while (true);
    }

    private void showLastestAlbums() {
        Session session = HibernateUtil.getSessionfactory().openSession();
        int choice = McUtils.readInt("Hangi tipteki son 10 albümü görüntülemek istiyorsunuz?\n" +
                "\t1) CD" +
                "\t2) DVD" +
                "\t3) Vinyl");
        switch (choice) {
            case 1 -> showLastTenCD(session);
            case 2 -> showLastTenDVD(session);
            case 3 -> showLastTenVinyl(session);
        }
    }

    private void showLastTenVinyl(Session session) {
        TypedQuery<VinylEntity> typedQuery3 = session.createQuery(
                "from VinylEntity as ce order by ce.createdDate desc", VinylEntity.class
        );
        ArrayList<VinylEntity> vinylEntities = (ArrayList<VinylEntity>) typedQuery3.setMaxResults(10).getResultList();
        System.out.println("\n\t\t~~Vinyl~~\n");
        for (VinylEntity temp : vinylEntities) {
            System.out.println(temp);
        }
    }

    private void showLastTenDVD(Session session) {
        TypedQuery<DVDEntity> typedQuery2 = session.createQuery(
                "from DVDEntity as ce order by ce.createdDate desc", DVDEntity.class
        );
        ArrayList<DVDEntity> dvdEntities = (ArrayList<DVDEntity>) typedQuery2.setMaxResults(10).getResultList();
        System.out.println("\n\t\t~~DVD~~\n");
        for (DVDEntity temp : dvdEntities) {
            System.out.println(temp);
        }
    }

    private void showLastTenCD(Session session) {
        TypedQuery<CDEntity> typedQuery = session.createQuery(
                "from CDEntity as ce order by ce.createdDate desc", CDEntity.class
        );
        ArrayList<CDEntity> cdEntities = (ArrayList<CDEntity>) typedQuery.setMaxResults(10).getResultList();
        System.out.println("\n\t\t~~CD~~\n");
        for (CDEntity temp : cdEntities) {
            System.out.println(temp);
        }
    }

    private void showDiscountedAlbums() {
        Session session = HibernateUtil.getSessionfactory().openSession();
        int choice = McUtils.readInt("Hangi tipteki indirimde olan 15 albümü görüntülemek istiyorsunuz?\n" +
                "\t1) CD" +
                "\t2) DVD" +
                "\t3) Vinyl");
        switch (choice) {
            case 1 -> showDiscountedCD(session);
            case 2 -> showDiscountedDVD(session);
            case 3 -> showDiscountedVinyl(session);
        }
    }

    private void showDiscountedCD(Session session) {
        String hql = "from CDEntity";
        TypedQuery<CDEntity> typedQuery = session.createQuery(hql, CDEntity.class);
        ArrayList<CDEntity> entities = (ArrayList<CDEntity>) typedQuery.getResultList();
        System.out.println("\n\t\t~~CD~~\n");
        for (CDEntity temp : entities) {
            if(temp.getDiscountRate() != 0) System.out.println(temp);
        }
    }

    private void showDiscountedDVD(Session session) {
        String hql = "from DVDEntity";
        TypedQuery<DVDEntity> typedQuery = session.createQuery(hql, DVDEntity.class);
        ArrayList<DVDEntity> entities = (ArrayList<DVDEntity>) typedQuery.getResultList();
        System.out.println("\n\t\t~~DVD~~");
        for (DVDEntity temp : entities) {
            if(temp.getDiscountRate() != 0) System.out.println(temp);
        }
    }

    private void showDiscountedVinyl(Session session) {
        String hql = "from VinylEntity";
        TypedQuery<VinylEntity> typedQuery = session.createQuery(hql, VinylEntity.class);
        ArrayList<VinylEntity> entities = (ArrayList<VinylEntity>) typedQuery.getResultList();
        System.out.println("\n\t\t~~Vinyl~~\n");
        for (VinylEntity temp : entities) {
            if(temp.getDiscountRate() != 0) System.out.println(temp);
        }
    }

    private void showAlbumsWRTGenre() {
        Session session = HibernateUtil.getSessionfactory().openSession();

        String hqlCD = "from CDEntity where genre BLUES";
        TypedQuery<CDEntity> typedQuery = session.createQuery(hqlCD, CDEntity.class);
        ArrayList<CDEntity> entities = (ArrayList<CDEntity>) typedQuery.getResultList();
        for (CDEntity temp : entities) {
            if(temp.getGenre() != null)
            System.out.println(temp);
            else {
                System.out.println("hata");
            }
        }
    }

    private String genreChoice() {
       int genreChoice = McUtils.readInt("Albümün tarzı nedir?\n " +
                " 1) Blues \n" +
                " 2) Jazz\n" +
                " 3) Rock\n" +
                " 4) Country\n" +
                " 5) Dance\n" +
                " 6) Hip Hop\n" +
                " 7) Soul\n");

        switch (genreChoice) {
            case 1 -> {
                return "BLUES";
            }
            case 2 -> {
                return "JAZZ";
            }
            case 3 -> {
                return "ROCK";
            }
            case 4 -> {
                return "COUNTRY";
            }
            case 5 -> {
                return "DANCE";
            }
            case 6 -> {
                return "HIPHOP";
            }
            case 7 -> {
                return "SOUL";
            }
        }
        return null;
    }

    private void showAlbumsWRTArtist() {
        Session session = HibernateUtil.getSessionfactory().openSession();
        String hql = "select ce from CDEntity as ce where artist...";

        TypedQuery<CDEntity> typedQuery = session.createQuery(hql, CDEntity.class);
        ArrayList<CDEntity> entities = (ArrayList<CDEntity>) typedQuery.getResultList();

        for (CDEntity temp : entities) {
            System.out.println(temp);
        }
    }



}
