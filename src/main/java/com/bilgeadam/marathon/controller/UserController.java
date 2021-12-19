package com.bilgeadam.marathon.controller;


import com.bilgeadam.marathon.entity.UserEntity;
import com.bilgeadam.marathon.util.McUtils;
import com.bilgeadam.marathon.view.UserPanel;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class UserController implements Controllable<UserEntity>{

    @Override
    public void delete(UserEntity entity) {

    }

    @Override
    public void update(UserEntity entity) {

    }

    @Override
    public ArrayList<UserEntity> list() {
        return null;
    }

    @Override
    public UserEntity find(long id) {
        Session session = databaseConnectionHibernate();
        UserEntity userEntity;
        try {
            userEntity = session.find(UserEntity.class, id);

            if (userEntity != null) {
                System.out.println("bulundu... ");
                return userEntity;
            } else {
                System.out.println("Aradığınız kriterde sonuçlar bulunamadı ...");
                return null;
            }
        } catch (Exception e) {
            System.out.println("find anında hata meydana geldi !!!!! ");
            e.printStackTrace();
        }
        return null;
    }

    public static void write2DB(String userName, String password){
        UserEntity entity = new UserEntity(userName,password);
        UserController controller = new UserController();
        controller.create(entity);
    }

    public void validateLoginInfos(String userName, String password) {
        Session session = databaseConnectionHibernate();
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userName);
        userEntity.setPassword(password);

        String hql = "select ue from UserEntity as ue where username=:key1 and password=:key2";
        TypedQuery<UserEntity> typedQuery = session.createQuery(hql, UserEntity.class);
        typedQuery.setParameter("key1", userEntity.getUsername());
        typedQuery.setParameter("key2", userEntity.getPassword());
        try {
            System.out.println(userEntity.getUsername());
            userEntity = typedQuery.getSingleResult();
            System.out.println("\n\tHoşgeldin " + userEntity.getUsername() + "!");
            UserPanel userPanel = new UserPanel();
            userPanel.showMenuItems();
        } catch (Exception e) {
            System.out.println("Kullanıcı adı veya şifre yanlış, tekrar deneyin");
        }


    }

}
