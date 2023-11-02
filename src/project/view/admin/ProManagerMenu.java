package project.view.admin;

import project.config.Config;
import project.service.product.ProductImpl;


public class ProManagerMenu {
    public static void showMenu(){
        ProductImpl impl = new ProductImpl();
        int choice;
        while (true){
            System.out.println("""
                    ======================================================================
                    ||     (=^=)                Product Manager               (=^=)     ||
                    ======================================================================
                    ||         1. AddNew                                                ||
                    ||         2. ShowAll                                               ||
                    ||         3. Find                                                  ||
                    ||         4. Edit                                                  ||
                    ||         5. Delete                                                ||
                    ||         6. Back                                                  ||
                    ======================================================================
                    """);
            choice = Config.getInt();
            switch (choice) {
                case 1:
                    impl.add();
                    break;
                case 2:
                    impl.show();
                    break;
                case 3:
                    impl.find();
                    break;
                case 4:
                    impl.update();
                    break;
                case 5:
                    impl.delete();
                    break;
                case 6:
                    return;
                default:
                    break;
            }
        }
    }
}
