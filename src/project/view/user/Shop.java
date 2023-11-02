package project.view.user;

import project.config.Config;
import project.service.LogOut;
import project.service.shop.ShopImpl;
import project.service.user.UserImpl;

public class Shop {
    public static void showShop(){
        int choice;
        while (true){
            System.out.println("""
                    ==================================================================
                    ||     /\\_/\\                                                    ||
                    ||    ( o.o )       Chào mừng đến với hiệu thuốc Ani            ||
                    ||     >^_^<                                                    ||
                    ||==============================================================||
                    ||                      Danh sách lựa chọn                      ||
                    ||~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~||
                    ||                    1. Vào shop                               ||
                    ||                    2. Kiểm tra giỏ hàng                      ||
                    ||                    3. Xem lịch sử mua hàng                   ||
                    ||                    4. Tài khoản                              ||
                    ||                    5. Đăng xuất                              ||
                    ==================================================================
                    """);
            choice = Config.getInt();
            switch (choice) {
                case 1:
                    ShopImpl.showShop();
                    break;
                case 2:
                    Cart.showCart();
                    break;
                case 3:
                    History.showHistory();
                    break;
                case 4:
                    UserImpl impl = new UserImpl();
                    impl.update();
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
