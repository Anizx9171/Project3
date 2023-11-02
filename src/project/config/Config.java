package project.config;

import project.constant.Constant;
import project.model.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Config {

    public static DecimalFormat formatVnd(){
        return new DecimalFormat("###,###,###");
    }

    public static Scanner scanner() {
        return new Scanner(System.in);
    }

    public static Long RandomId() {
        Random random = new Random();
        Long randomId = random.nextLong();
        if (randomId < 0) {
            return randomId * -1;
        }
        return randomId;
    }

    public static int getInt() {
        while (true) {
            try {
                System.out.print("Lựa chọn của bạn: ");
                int a = Integer.parseInt(Config.scanner().nextLine());
                System.out.println();
                return a;
            } catch (NumberFormatException E) {
                System.out.println("Sai định dạng, vui lòng nhập số nguyên");
            }
        }
    }

    public static String validateEmpty() {
        while (true) {
            String a = Config.scanner().nextLine();
            if (a.trim().isEmpty()) {
                System.out.println("Không được để trống");
            } else {
                return a;
            }
        }
    }

    public static String validateEmail() {
        while (true) {
            System.out.print("Nhập gmail: ");
            String a = Config.scanner().nextLine();
            System.out.println();
            if (a.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
                return a;
            } else {
                System.out.println("Gmail không đúng định dạng");
            }
        }
    }

    public static String validatePhoneNumber() {
        while (true) {
            System.out.print("Nhập số điện thoại: ");
            String a = Config.scanner().nextLine();
            if (a.trim().matches("^0+[135789]{1}+[\\d]{8}+$")) {
                return a;
            } else {
                System.out.println("Số điện thoại không hợp lệ");
            }
        }
    }
    public static Product getProById(Long id){
        GetArrays<Product> gPro = new GetArrays<>();
        List<Product> products = gPro.getArr(Constant.PRODUCT);
        for (Product pro:products) {
            if (pro.getProductId().equals(id)){
                return pro;
            }
        }
        return null;
    }
}
