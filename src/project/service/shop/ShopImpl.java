package project.service.shop;

import project.config.Config;
import project.config.FileTask;
import project.config.GetArrays;
import project.constant.Constant;
import project.model.Cart;
import project.model.Catalog;
import project.model.Product;
import project.service.cart.CartImpl;

import java.util.List;

public class ShopImpl {
   static GetArrays<Product> gArrP = new GetArrays<>();
   static GetArrays<Catalog> gArrC = new GetArrays<>();
    public static void showShop(){
        int choice;
        while (true){
            System.out.println("""
                    ==================================================================
                    ||     /\\_/\\                                                    ||
                    ||    ( o.o )                   Shop                            ||
                    ||     >^_^<                                                    ||
                    ||==============================================================||
                    ||                      Danh sách lựa chọn                      ||
                    ||~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~||
                    ||              1. Xem sản phẩm                                 ||
                    ||              2. Thêm sản phẩm vào giỏ hàng                   ||
                    ||              3. Tìm kiếm sản phẩm theo danh danh mục         ||
                    ||              4. Tìm kiếm sản phẩm theo tên                   ||
                    ||              5. xem giỏ hàng                                 ||
                    ||              6. thoát                                        ||
                    ==================================================================""");
            choice = Config.getInt();
            switch (choice) {
                case 1:
                    handleShowAll();
                    break;
                case 2:
                    CartImpl.addToCart();
                    break;
                case 3:
                    searchByCatalog();
                    break;
                case 4:
                    handleSearch();
                    break;
                case 5:
                    CartImpl.showCart();
                    break;
                case 6:
                    return;
                default:
                    break;
            }
        }
    }

    private static void handleShowAll() {
        List<Product> products = gArrP.getArr(Constant.PRODUCT);
        List<Catalog> catalogs = gArrC.getArr(Constant.CATALOG);
        System.out.println("--------------Các sản phẩm trong shop--------------");
        for (int i = 0; i < products.size(); i++) {
            for (Catalog ca:  catalogs){
                if (ca.getCatalogId().equals(products.get(i).getCategoryId())){
                    if (products.get(i).isStatus() && ca.isStatus()){
                        System.out.printf("%d.Mã sản phẩm: %s,\n Tên sản phẩm : %s,\n Phân Loại: %s,\n Kho còn: %d,\n Giá bán: %sđ.\n",(i+1),products.get(i).getProductId(),products.get(i).getProductName(), ca.getCatalogName(), products.get(i).getStock(), Config.formatVnd().format(products.get(i).getUnitPrice()));
                        System.out.println();
                    }
                }
            }
        }
        System.out.println("---------------------------------------------------");
    }

    private static void searchByCatalog() {
        List<Catalog> catalogs = gArrC.getArr(Constant.CATALOG);
        List<Product> products = gArrP.getArr(Constant.PRODUCT);
        for (int i = 0; i < catalogs.size(); i++) {
            if (catalogs.get(i).isStatus()){
                System.out.printf("%d. %s\n", (i+1),catalogs.get(i).getCatalogName());
            }
        }
        System.out.println("Nhập lựa chọn của bạn");
       int choice = Config.getInt();
       if (choice >= 1 && choice <= catalogs.size()){
           System.out.printf("--------------%s--------------\n", catalogs.get(choice - 1).getCatalogName());
           for (int i = 0; i < products.size(); i++){
               if (products.get(i).getCategoryId().equals(catalogs.get(choice - 1).getCatalogId())){
                   System.out.printf("%d.Mã sản phẩm: %s,\n Tên sản phẩm : %s,\n Phân Loại: %s,\n Kho còn: %d,\n Giá bán: %sđ.\n",(i+1),products.get(i).getProductId(),products.get(i).getProductName(), catalogs.get(choice - 1).getCatalogName(), products.get(i).getStock(), Config.formatVnd().format(products.get(i).getUnitPrice()));
                   System.out.println();
               }
           }
           System.out.println("-----------------------------------------");
       }
    }

    private static void handleSearch() {
        List<Product> products = gArrP.getArr(Constant.PRODUCT);
        List<Catalog> catalogs = gArrC.getArr(Constant.CATALOG);
        System.out.println("Nhập tên sản phẩm bạn muốn tìm kiếm.");
        String searchText = Config.validateEmpty();
        boolean check  = false;
        System.out.println("--------------kết quả tìm kiếm--------------");
        for (int i = 0; i < products.size(); i++) {
            for (Catalog ca:  catalogs){
                if (ca.getCatalogId().equals(products.get(i).getCategoryId())){
                    if (products.get(i).isStatus() && ca.isStatus()){
                        if (products.get(i).getProductName().contains(searchText)){
                            System.out.printf("%d.Mã sản phẩm: %s,\n Tên sản phẩm : %s,\n Phân Loại: %s,\n Kho còn: %d,\n Giá bán: %sđ.\n",(i+1),products.get(i).getProductId(),products.get(i).getProductName(), ca.getCatalogName(), products.get(i).getStock(), Config.formatVnd().format(products.get(i).getUnitPrice()));
                            System.out.println();
                            check = true;
                        }
                    }
                }
            }
        }
        if (!check){
            System.out.println("Không tìm thấy sản phẩm này (T^T)");
        }
        System.out.println("---------------------------------------------------");
    }
}
