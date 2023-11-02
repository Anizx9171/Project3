package project.service.oder;

import project.config.Config;
import project.config.FileTask;
import project.config.GetArrays;
import project.constant.Constant;
import project.model.Order;
import project.model.StatusOrder;
import project.model.User;

import java.util.List;

public class OderImpl {
    static FileTask<List<Order>> oTask = new FileTask<>();
    static GetArrays<Order> gOrder = new GetArrays<>();
    static FileTask<User> uTask = new FileTask<>();

    public static void menuAdmin(){
        while (true){
        List<Order> orders = gOrder.getArr(Constant.ORDER);
            System.out.println("Nhập id đơn cần duyệt");
            try {
                Long odId = Long.valueOf(Config.scanner().nextLine());
                for (Order od:orders) {
                    if (od.getOrderId().equals(odId) && od.getOrderStatus() != StatusOrder.WAITING){
                        System.out.println("Đơn hàng nãy đã được duyệt");
                        return;
                    }
                    if (od.getOrderId().equals(odId)&& od.getOrderStatus() == StatusOrder.WAITING){
                        while (true){
                            System.out.println("""
                                Bạn muốn làm gì?
                                1.Confirm
                                2.Cancel
                                """);
                            int choice = Config.getInt();
                            if (choice == 1){
                                od.setOrderStatus(StatusOrder.CONFIRM);
                                oTask.save(orders,Constant.ORDER);
                                System.out.println("Đã chấp nhận đơn hàng");
                                return;
                            }
                            if (choice == 2){
                                od.setOrderStatus(StatusOrder.CANCEL);
                                oTask.save(orders,Constant.ORDER);
                                System.out.println("Đã từ chối đơn hàng");
                                return;
                            }
                        }
                    }
                }
                System.out.println("Mã đơn hàng không tồn tại");
            }catch (NumberFormatException e){
                System.out.println("Id không đúng định dạng");
            }
        }
    }
    public static void getOderUser(){
        User userLogin = uTask.getData(Constant.LOGIN);
        List<Order> orders = gOrder.getArr(Constant.ORDER);
        if (orders.isEmpty()){
            System.out.println("Danh sách order rỗng");
            return;
        }
        for (Order od:orders) {
            if (od.getUserId().equals(userLogin.getUserId())){
                System.out.printf("\nId: %s,\nUser name: %s,\nProduct Name: %s,\nQuantity: %d,\nTotal: %sđ,\nstatus: %s.\n", od.getOrderId(), od.getUserName(), od.getProductName(), od.getQuantity(), Config.formatVnd().format(od.getTotal()), od.getOrderStatus());
            }
        }
    }

    public static void getAllOder(){
        List<Order> orders = gOrder.getArr(Constant.ORDER);
        if (orders.isEmpty()){
            System.out.println("Danh sách order rỗng");
            return;
        }
        for (Order od:orders) {
                System.out.println(od);
        }
    }
}
