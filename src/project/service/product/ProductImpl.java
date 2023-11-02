package project.service.product;

import project.config.Config;
import project.config.FileTask;
import project.config.GetArrays;
import project.constant.Constant;
import project.model.Catalog;
import project.model.Product;
import project.service.IService;

import java.util.List;

public class ProductImpl implements IService {
    GetArrays<Product> arr = new GetArrays<>();
    FileTask<List<Product>> task = new FileTask<>();

    @Override
    public void add() {
        List<Product> products =  arr.getArr(Constant.PRODUCT);
        System.out.println("Nhập số lượng sản phẩm muốn thêm:");
        int count = Config.getInt();
        for (int i = 0; i < count; i++) {
            System.out.println("Nhập tên sản phẩm: ");
            String name = Config.validateEmpty();
            Long categoryId = getIdCata();
            if (categoryId == null) {
                System.out.println("Không thể tạo sản phẩm đo danh mục không tồn tại");
                return;
            }
            System.out.println("Nhập mô tả sản phẩm:  ");
            String description = Config.validateEmpty();
            double unitPrice;
            while (true){
                System.out.println("Giá bán:");
                try {
                    unitPrice = Double.parseDouble(Config.scanner().nextLine());
                    if (unitPrice < 1){
                        System.out.println("Không được nhỏ hơn 1");
                        continue;
                    }
                    break;
                }catch (NumberFormatException e){
                    System.out.println("sai định dạng, vui lòng nhập lại");
                }
            }

            int stock;
            while (true){
                System.out.println("số lượng:");
                stock = Config.getInt();
                if (stock < 1){
                    System.out.println("Không được nhỏ hơn 1");
                    continue;
                }
                break;
            }
            System.out.println("""
                            Trạng thái sản phẩm?
                            1. true
                            Số khác. false
                            """);
            boolean status = Config.getInt() == 1;
            Product newPro = new Product(name, categoryId, description, unitPrice, stock, status);
            products.add(newPro);
        }
        task.save(products, Constant.PRODUCT);
    }

    @Override
    public void delete() {
        List<Product> products =  arr.getArr(Constant.PRODUCT);
        for (int i = 0; i < products.size(); i++) {
            System.out.printf("%d. %s\n", (i+1), products.get(i).getProductName());
        }
        int indexPro = Config.getInt();
        if (indexPro >=1 && indexPro <= products.size()){
            products.remove(indexPro - 1);
        }else {
            System.out.println("Không tìm thấy product muốn xóa");
        }
        task.save(products, Constant.PRODUCT);
    }

    @Override
    public void update() {
        List<Product> products =  arr.getArr(Constant.PRODUCT);
        for (int i = 0; i < products.size(); i++) {
            System.out.printf("%d. %s\n", (i+1), products.get(i).getProductName());
        }
        int indexPro = Config.getInt();
        if (indexPro >=1 && indexPro <= products.size()){
            while (true){
                System.out.println("""
                    Nhập lựa bạn muốn sửa thông tin nào?
                    1. Tên sản phẩm
                    2. Danh mục
                    3. Mô tả
                    4. Giá bán
                    5. Số lượng
                    6. Trạng thái
                    7. thoát
                    """);
                int choi = Config.getInt();
                switch (choi) {
                    case 1:
                        System.out.println("Nhập tên sản phẩm: ");
                        String name = Config.validateEmpty();
                        products.get(indexPro - 1).setProductName(name);
                        System.out.println("Sửa thành công");
                        break;
                    case 2:
                        Long newCataId =  getIdCata();
                        if (newCataId != null){
                            products.get(indexPro - 1).setCategoryId(newCataId);
                            System.out.println("Sửa thành công");
                            break;
                        }
                        System.out.println("Sửa thất bại");
                        break;
                    case 3:
                        System.out.println("Nhập tên sản phẩm: ");
                        String description = Config.validateEmpty();
                        products.get(indexPro - 1).setDescription(description);
                        System.out.println("Sửa thành công");
                        break;
                    case 4:
                        double unitPrice;
                        while (true){
                            System.out.println("Giá bán:");
                            try {
                                unitPrice = Double.parseDouble(Config.scanner().nextLine());
                                if (unitPrice < 1){
                                    System.out.println("Không được nhỏ hơn 1");
                                    continue;
                                }
                                break;
                            }catch (NumberFormatException e){
                                System.out.println("sai định dạng, vui lòng nhập lại");
                            }
                        }
                        products.get(indexPro-1).setUnitPrice(unitPrice);
                        break;
                    case 5:
                        int stock;
                        while (true){
                            System.out.println("số lượng:");
                            stock = Config.getInt();
                            if (stock < 1){
                                System.out.println("Không được nhỏ hơn 1");
                                continue;
                            }
                            break;
                        }
                        products.get(indexPro-1).setStock(stock);
                        break;
                    case 6:
                        System.out.println("""
                            Status?
                            1. true
                            Số khác. false
                            """);
                        boolean status = Config.getInt() == 1;
                        products.get(indexPro-1).setStatus(status);
                        break;
                    case 7:
                        return;
                    default:
                        break;
                }
                task.save(products, Constant.PRODUCT);
            }
        }else {
            System.out.println("Không tìm thấy product muốn sửa");
        }
    }

    @Override
    public void find() {
        List<Product> products =  arr.getArr(Constant.PRODUCT);
        System.out.println("Nhập tên sản phẩm muốn tìm: ");
        String namePro = Config.validateEmpty();
        boolean check = false;
        System.out.println("Kết quả tìm kiếm: ");
        for (Product product : products) {
            if (product.getProductName().toLowerCase().contains(namePro.toLowerCase())) {
                System.out.println(product);
                System.out.println();
                check = true;
            }
        }
        if (!check){
            System.out.println("Không tìm thấy sản phẩm");
        }
    }

    @Override
    public void show() {
        List<Product> products =  arr.getArr(Constant.PRODUCT);
        System.out.println("Danh sách sản phẩm hiện tại:");
        if (products.isEmpty()){
            System.out.println("Danh sách trống");
            System.out.println();
            return;
        }
        products.forEach(System.out::println);
    }
    private Long getIdCata(){
        GetArrays<Catalog> listCata = new GetArrays<>();
        List<Catalog> catalogList = listCata.getArr(Constant.CATALOG);
        System.out.println("Chọn danh mục");
        for (int i = 0; i < catalogList.size(); i++) {
            System.out.printf("%d. %s\n", (i+1) ,catalogList.get(i).getCatalogName());
        }
        int choice = Config.getInt();
        if (choice >= 1 && choice <= catalogList.size()){
            return catalogList.get(choice - 1).getCatalogId();
        }else{
            System.out.println("""
                        Danh mục không tồn tại. Tạo mới danh mục?
                        1. true
                        Số khác. false
                        """);
            int chs = Config.getInt();
            if (chs == 1){
                System.out.println("Nhập tên Danh mục: ");
                String cataName = Config.validateEmpty();
                System.out.println("Nhập mô tả: ");
                String cataDes = Config.validateEmpty();
                System.out.println("""
                            Status?
                            1. true
                            Số khác. false
                            """);
                boolean CataSta = Config.getInt() == 1;
                Catalog newCata = new Catalog(cataName, cataDes, CataSta);
                catalogList.add(newCata);
                FileTask<List<Catalog>> tks = new FileTask<>();
                tks.save(catalogList, Constant.CATALOG);
                return newCata.getCatalogId();
            }
            else{
                return null;
            }
        }
    }
}
