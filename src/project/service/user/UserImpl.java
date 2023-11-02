package project.service.user;

import project.config.Config;
import project.config.FileTask;
import project.config.GetArrays;
import project.constant.Constant;
import project.model.User;
import project.service.IService;
import project.service.LogOut;
import project.view.FistMenu;

import java.util.List;

public class UserImpl implements IService {
    GetArrays<User> getArr = new GetArrays();
    FileTask<List<User>> task = new FileTask();

    @Override
    public void add() {
        List<User> users = getArr.getArr(Constant.USER);
        System.out.println("Username (ít nhất 6 kí tự): ");
        String username = "";
        while (true) {
            boolean check = false;
            username = Config.validateEmpty();
            for (User u : users) {
                if (u.getUsername().equals(username)) {
                    check = true;
                    break;
                }
            }
            if (check) {
                System.out.println("Tên đăng nhập đã được sử dụng");
            } else {
                if (username.length() > 5) {
                    break;
                }
                System.out.println("Tối thiểu 6 kí tự");
            }
        }
        System.out.println("Email: ");
        String email;
        while (true) {
            boolean check = false;
            email = Config.validateEmail();
            for (User u : users) {
                if (((User) u).getEmail().equalsIgnoreCase(email)) {
                    check = true;
                    break;
                }
            }
            if (check) {
                System.out.println("email đã được sử dụng");
            } else {
                break;
            }

        }
        System.out.println("Password (ít nhất 8 kí tự): ");
        String password = "";
        while (password.trim().length() < 8) {
            System.out.println("Nhập password:");
            password = Config.validateEmpty();
        }
        System.out.println("""
                Role
                0. Admin.
                số khác: User.
                """);
        boolean role = Config.getInt() == 0;
        User user = new User(username, email, password, role);
        users.add(user);
        task.save(users, Constant.USER);
    }

    @Override
    public void delete() {
        List<User> users = getArr.getArr(Constant.USER);
        for (int i = 0; i < users.size(); i++) {
            System.out.printf("%d. %s :%s\n", (i + 1), users.get(i).getUsername(),users.get(i).isStatus());
        }
        System.out.println("Tài khoản muốn khóa hoặc mở khóa:");
        int choice = Config.getInt();
        if (choice >= 1 && choice <= users.size()){
            if (users.get(choice - 1).isRole()){
                System.out.println("Không thể sửa admin");
                return;
            }
            users.get(choice - 1).setStatus(!users.get(choice - 1).isStatus());
            System.out.println(!users.get(choice - 1).isStatus()?"Đã khóa tài khoản":"Tài khoản đã được mở khóa");

        }
        task.save(users, Constant.USER);
    }

    @Override
    public void update() {
        List<User> users = getArr.getArr(Constant.USER);
        FileTask<User> ntask = new FileTask();
        User userLoin = ntask.getData(Constant.LOGIN);
        System.out.println("Tài khoản của bạn:");
        System.out.println("Email: " + userLoin.getEmail());
        System.out.println("User Name: " + userLoin.getUsername());
        System.out.println("""
                Bạn muốn làm gì?
                1. Đổi mật khẩu
                2. Thoát
                """);
        int choice = Config.getInt();
        if (choice == 1){
            System.out.println("Nhập mật khẩu cũ");
            String oldPass = Config.validateEmpty();
            if (userLoin.getPassword().equals(oldPass)){
                while (true){
                    System.out.println("Nhập mật khẩu mới");
                    String newPass = Config.validateEmpty();
                    if (newPass.trim().length() > 6){
                        for (User u: users) {
                            if (u.getUserId().equals(userLoin.getUserId())){
                                u.setPassword(newPass);
                                task.save(users, Constant.USER);
                                System.out.println("Đổi mật khẩu thành công, vui lòng đăng nhập lại để tiếp tục");
                                LogOut.kickOut();
                                FistMenu.menuAccount();
                                return;
                            }
                        }
                    }
                    System.out.println("Tối thiểu 6 kí tự");
                }
            }else {
                System.out.println("Sai mật khẩu");
            }
        }
    }

    @Override
    public void find() {}

    @Override
    public void show() {
        List<User> users = getArr.getArr(Constant.USER);
        if (users.isEmpty()){
            System.out.println("Trống");
            return;
        }
        users.forEach(System.out::println);
    }
}
