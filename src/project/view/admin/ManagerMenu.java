package project.view.admin;

import project.config.Config;
import project.service.LogOut;

public class ManagerMenu {
    public static void showMenu(){
       int choice;
        while (true){
            System.out.println("""
                    ======================================================================
                    ||     (0w0)                    Manager                   (0w0)     ||
                    ======================================================================
                    ||         1. Catelog                                               ||
                    ||         2. User                                                  ||
                    ||         3. Product                                               ||
                    ||         4. Oder                                                  ||
                    ||         5. LogOut                                                ||
                    ======================================================================
                    """);
            choice = Config.getInt();
            switch (choice){
                case 1:
                    CatManagerMenu.showMenu();
                    break;
                case 2:
                    UserManagerMenu.showMenu();
                    break;
                case 3:
                    ProManagerMenu.showMenu();
                    break;
                case 4:
                    OrdManagerMenu.showMenu();
                    break;
                case 5:
                    if (LogOut.logOut()){
                        return;
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
