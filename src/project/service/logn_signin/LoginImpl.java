package project.service.logn_signin;

import project.config.Config;
import project.config.FileTask;
import project.config.GetArrays;
import project.constant.Constant;
import project.model.Cart;
import project.model.User;
import project.view.admin.ManagerMenu;
import project.view.user.Shop;

import java.util.List;

public class LoginImpl {
    public static void Log(){
        FileTask<User> task = new FileTask<>();
        GetArrays<User> arr = new GetArrays<>();
        List<User> users = arr.getArr(Constant.USER);
        System.out.println("Nhập email hoặc tên đăng nhập");
        String uEN = Config.validateEmpty();
        System.out.println("Nhập mật khẩu:");
        String pass = Config.validateEmpty();
        boolean check = false;
        for (User u :users) {
            if ((u.getEmail().equalsIgnoreCase(uEN) && u.getPassword().equals(pass) || u.getUsername().equals(uEN)) && u.getPassword().equals(pass)){
                if (!u.isStatus()){
                    System.out.println("Tài khoản của bạn đã bị khóa!");
                    return;
                }
                task.save(u, Constant.LOGIN);
                System.out.println("Đăng nhập thành công");
                check = true;
                if (u.isRole()){
                    ManagerMenu.showMenu();
                    return;
                }
                getCartUser(u);
                Shop.showShop();
                return;
            }
        }
        if (!check){
            System.out.println("Sai tên đăng nhập hoặc mật khẩu");
        }
    }
    private static void getCartUser(User u){
        FileTask<Cart> cTask = new FileTask<>();
        GetArrays<Cart> gCart = new GetArrays<>();
        FileTask<List<Cart>> carts = new FileTask<>();
        List<Cart> cartList = gCart.getArr(Constant.CART);
        for (Cart c:cartList) {
            if (c.getUserId().equals(u.getUserId())) {
                cTask.save(c, Constant.CARTLOGIN);
                return;
            }
        }
        Cart cart = new Cart(u.getUserId());
        cartList.add(cart);
        carts.save(cartList, Constant.CART);
        cTask.save(cart, Constant.CARTLOGIN);
    }
}