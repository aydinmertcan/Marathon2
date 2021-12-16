package com.bilgeadam.marathon.controller;

import com.bilgeadam.marathon.entity.VinylEntity;
import com.bilgeadam.marathon.util.HibernateUtil;
import com.bilgeadam.marathon.util.McUtils;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class VinylController implements Controllable<VinylEntity>{
    @Override
    public void delete(VinylEntity entity) {

    }

    @Override
    public void update(VinylEntity entity) {

        int choice = McUtils.readInt("Lütfen değiştirmek istediğiniz ID'yi girin");
        VinylEntity vinylEntity = new VinylEntity();
        VinylController vinController = new VinylController();

        Session session = HibernateUtil.getSessionfactory().openSession();
        TypedQuery<VinylEntity> typedQuery = session.createQuery("select ve from VinylEntity as ve where id =:key",
                VinylEntity.class);
        typedQuery.setParameter("key", choice);
        vinylEntity = typedQuery.getSingleResult();

        vinylEntity.setName(McUtils.readString("İsmi giriniz"));
        vinylEntity.setPrice(McUtils.readDouble("Fiyatı giriniz"));
        vinylEntity.setPrice(McUtils.readDouble("İndirim oranını giriniz"));
        vinController.update(vinylEntity);

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
        return null;
    }
}
