package project.view;

import project.config.Config;
import project.service.logn_signin.LoginImpl;
import project.service.logn_signin.SigninImpl;

public class FistMenu {
    public static void menuAccount() {
        int choice;
        while (true) {
            System.out.println("""
                    ======================================================================
                    ||     (0w0)         Chào mừng đến với ani shop           (0w0)     ||
                    ======================================================================
                    ||         1. Đăng nhập                                             ||
                    ||         2. Đăng kí                                               ||
                    ||         3. Thoát                                                 ||
                    ======================================================================
                    """);
            choice = Config.getInt();
            switch (choice) {
                case 1:
                    LoginImpl.Log();
                    break;
                case 2:
                    SigninImpl.newAcc();
                    break;
                case 3:
                    System.exit(0);
                default:
                    break;
            }
        }

    }
}
