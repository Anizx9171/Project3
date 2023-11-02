package project.view.admin;

import project.config.Config;
import project.service.user.UserImpl;

public class UserManagerMenu {
    public static void showMenu() {
        UserImpl impl = new UserImpl();
        int choice;
        while (true) {
            System.out.println("""
                    ======================================================================
                    ||     (6_6)                  User Manager                (6_6)     ||
                    ======================================================================
                    ||         1. Lock/Unlock                                           ||
                    ||         2. Show all                                              ||
                    ||         3. Back                                                  ||
                    ======================================================================
                    """);
            choice = Config.getInt();
            switch (choice) {
                case 1:
                    impl.delete();
                    break;
                case 2:
                    impl.show();
                    break;
                case 3:
                    return;
                default:
                    break;
            }
        }
    }
}
