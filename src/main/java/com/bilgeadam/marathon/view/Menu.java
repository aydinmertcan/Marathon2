package com.bilgeadam.marathon.view;

import com.bilgeadam.marathon.controller.UserController;
import com.bilgeadam.marathon.model.User;
import com.bilgeadam.marathon.util.McUtils;

import static com.bilgeadam.marathon.controller.UserController.write2DB;

public class Menu {
    public void  menuItems() {
        int choice = 0;
        McUtils.header("Plak Dükkanına Hoşgeldiniz");
        System.out.println();
        do {
            choice = McUtils.readInt("Lütfen giriş yapın:\n" + "\t1) Admin olarak giriş yap\n" + "\t2) Kullanıcı olarak giriş yap\n" + "\t3) Kayıt ol\n" + "\t99) Exit\n");
            switch (choice) {
                case 1:
                    adminLoginScreen();
                    break;
                case 2:
                    userLoginScreen();
                    break;
                case 3:
                    signupScreen();
                    break;
                case 99:
                    System.exit(-1);
                    break;
            }
        } while (true);
    }

    private void userLoginScreen() {
        String userName = McUtils.readString("Kullanıcı adınızı giriniz: ");
        String password = McUtils.readString("Şifrenizi giriniz: ");
        UserController userController = new UserController();
        userController.validateLoginInfos(userName, password);
        System.out.println("\n\nYönlendiriliyorsunuz...");
        UserPanel userPanel = new UserPanel();
        userPanel.showMenuItems();
    }

    private void signupScreen() {
        String userName = McUtils.readString("Oluşturmak istediğiniz adını giriniz ");
        String password = McUtils.readString("Şifre giriniz: ");
        UserController.write2DB(userName,password);
    }


    private void adminLoginScreen() {
        String userName = McUtils.readString("Kullanıcı adınızı giriniz: ");
        String password = McUtils.readString("Şifrenizi giriniz: ");

        if(userName.equals("admin") && password.equals("qwerty")) {
            AdminPanel adminPanel = new AdminPanel();
            adminPanel.showAdminPanel();
        }
        else {
            System.out.println("Kullanıcı adı veya şifre yanlış. Lütfen tekrar deneyin.");
        }
    }


}
