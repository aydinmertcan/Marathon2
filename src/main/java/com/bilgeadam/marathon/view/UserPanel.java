package com.bilgeadam.marathon.view;

import com.bilgeadam.marathon.entity.CDEntity;
import com.bilgeadam.marathon.entity.VinylEntity;
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
            choice = McUtils.readInt("Lütfen ne yapmak istediğinizi seçiniz:\n" + "\t1) Son Eklenen 10 Albüm\n"
                    + "\t2) İndirimdeki 15 Albüm\n" +
                    "\t3) Türlere Göre Albümler\n" +
                    "\t4) Sanatçıya Göre Albümler\n" +
                    "\t5) En Çok Sipariş Edilenler\n" +
                    "\t6) En Popüler Sanatçı\n" +
                    "\t99) Exit\n");
            switch (choice) {
                case 1:
                    showLastestAlbums();
                    break;

                case 2:
                    showDiscountedAlbums();
                    break;
                case 3:
                    showAlbumsWRTGenre();
                    System.out.println("Yapım aşamasında...");
                    break;
                case 4:
                    showAlbumsWRTArtist();
                    System.out.println("Yapım aşamasında...");
                    break;
                case 6:
                    showAlbumsWRTArtist();
                    System.out.println("Yapım aşamasında...");
                    break;
                case 99:
                    System.exit(-1);
                    break;
            }
        } while (true);
    }

    private void showLastestAlbums() {
        Session session = HibernateUtil.getSessionfactory().openSession();
        String hql = "select ce from CDEntity as ce order by date(createdDate)";

        TypedQuery<CDEntity> typedQuery = session.createQuery(hql, CDEntity.class);
        ArrayList<CDEntity> entities = (ArrayList<CDEntity>) typedQuery.getResultList();

        for (CDEntity temp : entities) {
            System.out.println(temp);
        }
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

    private void showAlbumsWRTGenre() {
        Session session = HibernateUtil.getSessionfactory().openSession();
        String hql = "select ce from CDEntity as ce order by date(createdDate)";

        TypedQuery<CDEntity> typedQuery = session.createQuery(hql, CDEntity.class);
        ArrayList<CDEntity> entities = (ArrayList<CDEntity>) typedQuery.getResultList();

        for (CDEntity temp : entities) {
            System.out.println(temp);
        }
    }

    private void showDiscountedAlbums() {
        Session session = HibernateUtil.getSessionfactory().openSession();
        String hql = "select vE from VinylEntity as vE where artist";

        TypedQuery<VinylEntity> typedQuery = session.createQuery(hql, VinylEntity.class);
        ArrayList<VinylEntity> entities = (ArrayList<VinylEntity>) typedQuery.getResultList();

        for (VinylEntity temp : entities) {
            System.out.println(temp);
        }
    }
}
