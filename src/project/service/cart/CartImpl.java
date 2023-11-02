package project.service.cart;

import project.config.Config;
import project.config.FileTask;
import project.config.GetArrays;
import project.constant.Constant;
import project.model.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CartImpl {
    static FileTask<Cart> cTask = new FileTask<>();
    static FileTask<List<Cart>> lCTask = new FileTask<>();
    static FileTask<User> uTask = new FileTask<>();
    static FileTask<List<Order>> lOTask = new FileTask<>();
    static FileTask<List<Product>> lPTask = new FileTask<>();
    static GetArrays<Cart> gCart = new GetArrays<>();
    static GetArrays<Order> gOrder = new GetArrays<>();
    static GetArrays<Product> gProduct = new GetArrays<>();
    static Cart cartUser = cTask.getData(Constant.CARTLOGIN);

    public static void addToCart(){
        List<Cart> carts = gCart.getArr(Constant.CART);
        List<Product> products = gProduct.getArr(Constant.PRODUCT);
        try {
            System.out.println("Nhập id sản phẩm: ");
            Long idPro = Long.valueOf(Config.scanner().nextLine());
            boolean check = false;
            for (Product product:products) {
                if (product.getProductId().equals(idPro)) {
                    check = true;
                    break;
                }
            }
            if (check){
                for (Cart cart :carts) {
                    if(cart.getUserId().equals(cartUser.getUserId())){
                        Map<Long, Integer> mapCart = cart.getCart();
                        for (Map.Entry<Long, Integer> entry : mapCart.entrySet()) {
                            if (entry.getKey().equals(idPro)){
                                entry.setValue(entry.getValue() + 1);
                                lCTask.save(carts, Constant.CART);
                                System.out.println("Đã có trong giỏ hàng, +1 số lượng");
                                return;
                            }
                        }
                        mapCart.put(idPro, 1);
                        lCTask.save(carts, Constant.CART);
                        System.out.println("Thêm vào giỏ hàng thành công");
                        return;
                    }
                }
            }else {
                System.out.println("Sản phẩm bị xóa hoặc không tồn tại");
            }
        }catch (NumberFormatException e){
            System.out.println("Sai định dạng id");
        }
    }

    public  static void showCart(){
        while (true){
       List<Cart> carts = gCart.getArr(Constant.CART);
        System.out.println("Giỏ hàng của bạn:");
        boolean check = false;
        for (Cart cart :carts) {
            if(cart.getUserId().equals(cartUser.getUserId())){
                Map<Long, Integer> mapCart = cart.getCart();
                for (Map.Entry<Long, Integer> entry : mapCart.entrySet()) {
                    Product pro = Config.getProById(entry.getKey());
                    if (pro == null){
                        continue;
                    }else{
                        check = true;
                        System.out.printf("Mã sản phẩm: %s, Tên sản phẩm: %s, Số lượng: %d, Giá: %s đ\n",pro.getProductId(),pro.getProductName(), entry.getValue(),Config.formatVnd().format(pro.getUnitPrice()*entry.getValue()));
                    }
                }
            }
        }
        if (!check){
            System.out.println("Giỏ hàng rỗng (T^T)");
            return;
        }else {
            int choice;
                System.out.println("""
                    Bạn muốn làm gì?
                    1. Check out
                    2. Thay đổi số lượng
                    3. Xóa sản phẩm
                    4. Quay lại
                    """);
                choice = Config.getInt();
                switch (choice){
                    case 1:
                        checkOut();
                        return;
                    case 2:
                        changeQuantity();
                        break;
                    case 3:
                        delProInCart();
                        break;
                    case 4:
                        return;
                    default:
                        break;
                }
            }
        }
    }

    private static void checkOut() {
        List<Cart> carts = gCart.getArr(Constant.CART);
        List<Product> products = gProduct.getArr(Constant.PRODUCT);
        List<Order> orders = gOrder.getArr(Constant.ORDER);
        User uLogin = uTask.getData(Constant.LOGIN);
        System.out.println("Nhập số điện thoại (+84)");
        String phoneNumber = Config.validatePhoneNumber();
        System.out.println("Nhập địa chỉ");
        String address = Config.validateEmpty();
        for (Cart cart :carts) {
            if(cart.getUserId().equals(cartUser.getUserId())){
                Map<Long, Integer> mapCart = cart.getCart();
                if (mapCart == null){
                    continue;
                }
                for (Map.Entry<Long, Integer> entry : mapCart.entrySet()) {
                    Long key = entry.getKey();
                    Integer value = entry.getValue();
                    for (Product pro:products) {
                        if (pro.getProductId().equals(key)){
                            if (pro.getStock() < value){
                                System.out.printf("Sản phẩm %s vượt quá số lượng trong kho %d\n", pro.getProductName(), pro.getStock());
                            }else if(!pro.isStatus()){
                                System.out.printf("Sản phẩm %s đã ngưng bán\n", pro.getProductName());
                            }else {
                                pro.setStock(pro.getStock() - value);
                                if (pro.getStock() <= 0){
                                    pro.setStatus(false);
                                }
                                Order order = new Order(uLogin.getUserId(), pro.getProductId(), uLogin.getUsername(), pro.getProductName(), phoneNumber, address, (pro.getUnitPrice()*value),value);
                                orders.add(order);
                                mapCart.remove(entry.getKey());

                                System.out.printf("Mua sản phẩm %s thành công\n", pro.getProductName());
                            }
                        }
                    }
                }
            }
        }
        lCTask.save(carts, Constant.CART);
        lPTask.save(products, Constant.PRODUCT);
        lOTask.save(orders, Constant.ORDER);
    }

    private static void delProInCart() {
        List<Cart> carts = gCart.getArr(Constant.CART);
        System.out.println("Nhập id product muốn xóa");
        Long idPro = Long.valueOf(Config.scanner().nextLine());
        for (Cart cart :carts) {
            if(cart.getUserId().equals(cartUser.getUserId())){
                Map<Long, Integer> mapCart = cart.getCart();
                for (Map.Entry<Long, Integer> entry : mapCart.entrySet()) {
                   if (entry.getKey().equals(idPro)){
                       mapCart.remove(entry.getKey());
                       lCTask.save(carts, Constant.CART);
                       System.out.println("Xóa thành công");
                       return;
                   }
                }
            }
        }
        System.out.println("Không có sản phẩm id này trong giỏ hàng");
    }

    private static void changeQuantity() {
        List<Cart> carts = gCart.getArr(Constant.CART);
        System.out.println("Nhập id product muốn thay đổi số lượng");
        Long idPro;
        try {
            idPro = Long.valueOf(Config.scanner().nextLine());
        }catch (NumberFormatException e){
            System.out.println("Id không tồn tại");
            return;
        }
        for (Cart cart :carts) {
            if(cart.getUserId().equals(cartUser.getUserId())){
                Map<Long, Integer> mapCart = cart.getCart();
                for (Map.Entry<Long, Integer> entry : mapCart.entrySet()) {
                    if (entry.getKey().equals(idPro)){
                        Product pro = Config.getProById(idPro);
                        if (pro != null){
                            System.out.println("Nhập số lượng mới:");
                            while (true){
                                int stock = Config.getInt();
                                if (stock>0 && stock < pro.getStock()){
                                    entry.setValue(stock);
                                    lCTask.save(carts, Constant.CART);
                                    return;
                                }
                                if (stock >= pro.getStock()){
                                    System.out.println("Vượt quá số lượng trong kho, trong kho còn " + pro.getStock());
                                }
                                if (stock < 1){
                                    System.out.println("Không được nhỏ hơn 1");
                                }
                            }
                        }else {
                            System.out.println("Sản phẩm đã ngưng bán, vui lòng xóa");
                            return;
                        }
                    }
                }
            }
        }
        System.out.println("Không có sản phẩm id này trong giỏ hàng");
    }

}