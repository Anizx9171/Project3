package project.view.admin;

import project.config.Config;
import project.service.oder.OderImpl;

public class OrdManagerMenu {
    public static void showMenu(){
        int choice;
        while (true){
            System.out.println("""
                    ======================================================================
                    ||     (T^T)                  Oder Manager                (T^T)     ||
                    ======================================================================
                    ||         1. ShowAll                                               ||
                    ||         2. Browse                                                ||
                    ||         3. Back                                                  ||
                    ======================================================================
                    """);
            choice = Config.getInt();
            switch (choice) {
                case 1:
                    OderImpl.getAllOder();
                    break;
                case 2:
                    OderImpl.menuAdmin();
                    break;
                case 3:
                    return;
                default:
                    break;
            }
        }
    }
}
