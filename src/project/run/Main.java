package project.run;

import project.config.FileTask;
import project.constant.Constant;
import project.model.User;
import project.service.LogOut;
import project.view.FistMenu;
import project.view.admin.ManagerMenu;
import project.view.user.Shop;

public class Main {
    public static void main(String[] args) {
        FileTask<User> task = new FileTask<>();
        while (true){
            if (task.getData(Constant.LOGIN) == null){
                FistMenu.menuAccount();
            }else if (task.getData(Constant.LOGIN).isStatus()){
                if(task.getData(Constant.LOGIN).isRole()){
                    ManagerMenu.showMenu();
                }else{
                    Shop.showShop();
                }
            }else {
                System.out.println("Tài khoản của bạn đã bị khóa!");
                LogOut.logOut();
                FistMenu.menuAccount();
            }
        }
    }
}