package project.service.catalog;

import project.config.Config;
import project.config.FileTask;
import project.config.GetArrays;
import project.constant.Constant;
import project.model.Catalog;
import project.model.Product;
import project.service.IService;

import java.util.List;

public class CatalogImpl implements IService {
    GetArrays<Catalog> listCata = new GetArrays<>();
    FileTask<List<Catalog>> task = new FileTask<>();


    @Override
    public void add() {
        List<Catalog> catalogList = listCata.getArr(Constant.CATALOG);
        System.out.println("Nhập số lượng danh mục muốn thêm");
        int count = Config.getInt();
        for (int i = 0; i < count; i++) {
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
        }
        task.save(catalogList, Constant.CATALOG);
    }

    @Override
    public void delete() {
        GetArrays<Product> listPro = new GetArrays<>();
        List<Catalog> catalogList = listCata.getArr(Constant.CATALOG);
        List<Product> products = listPro.getArr(Constant.PRODUCT);
        for (int i = 0; i < catalogList.size(); i++) {
            System.out.printf("%d. %s\n", (i + 1), catalogList.get(i).getCatalogName());
        }
        int choice = Config.getInt();
        if (choice >= 1 && choice <= catalogList.size()) {
            for (Product pro : products) {
                if (pro.getCategoryId().equals(catalogList.get(choice - 1).getCatalogId())) {
                    System.out.println("Có sản phầm đang dùng danh mục, không thể xóa");
                    return;
                }
            }
            catalogList.remove(choice - 1);
        } else {
            System.out.println("Không có sản phẩm này");
        }
        task.save(catalogList, Constant.CATALOG);
    }

    @Override
    public void update() {
        List<Catalog> catalogList = listCata.getArr(Constant.CATALOG);
        for (int i = 0; i < catalogList.size(); i++) {
            System.out.printf("%d. %s\n", (i + 1), catalogList.get(i).getCatalogName());
        }
        int choice = Config.getInt();
        if (choice >= 1 && choice <= catalogList.size()) {
            while (true) {
                System.out.println("""
                        Bạn muốn sửa gì?
                        1. Tên danh mục
                        2. Mô tả danh mục
                        3. Trạng thái
                        4. Thoát
                        """);
                int n = Config.getInt();
                switch (n) {
                    case 1:
                        System.out.println("Nhập tên mới");
                        String name = Config.validateEmpty();
                        catalogList.get(choice - 1).setCatalogName(name);
                        break;
                    case 2:
                        System.out.println("Nhập mô tả mới");
                        String description = Config.validateEmpty();
                        catalogList.get(choice - 1).setDescription(description);
                        break;
                    case 3:
                        catalogList.get(choice - 1).setStatus(!catalogList.get(choice - 1).isStatus());
                        System.out.println("Đã thay đổi trạng thái thành " + catalogList.get(choice - 1).isStatus());
                        break;
                    case 4:
                        return;
                    default:
                        break;
                }
                task.save(catalogList, Constant.CATALOG);
            }
        }
    }

    @Override
    public void find() {
        List<Catalog> catalogList = listCata.getArr(Constant.CATALOG);
        System.out.println("Nhập tên danh mục bạn muốn tìm:");
        String value = Config.validateEmpty();
        boolean check = false;
        for (Catalog cat :catalogList) {
            if (cat.getCatalogName().contains(value)){
                System.out.println(cat);
                check = true;
            }
        }
        if(!check){
            System.out.println("không tìm thấy danh mục");
        }
    }

    @Override
    public void show() {
        List<Catalog> catalogList = listCata.getArr(Constant.CATALOG);
        if (catalogList.isEmpty()){
            System.out.println("Danh sách trống");
            return;
        }
        catalogList.forEach(System.out::println);
    }
}
