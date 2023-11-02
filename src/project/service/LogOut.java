package project.service;

import project.config.Config;
import project.constant.Constant;
import java.io.FileOutputStream;
import java.io.IOException;

public class LogOut {
    public static boolean logOut(){
        int choice;
       while (true){
           System.out.println("""
                Bạn có muốn đăng xuất không?
                1. true
                2. false
                """);
           choice = Config.getInt();
           if (choice == 1){
               kickOut();
               return true;
           }
           if (choice == 2){
               return false;
           }
       }
    }
    public static void kickOut(){
        try {
            FileOutputStream fis = new FileOutputStream(Constant.LOGIN);
            fis.write(0);
            FileOutputStream fic = new FileOutputStream(Constant.CARTLOGIN);
            fic.write(0);
        }catch (IOException e){}
    }
}
