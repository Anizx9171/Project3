package project.config;
import java.io.*;

public class FileTask<T> {

    public T getData(String url){
       try {
           FileInputStream fos = new FileInputStream(url);
           ObjectInputStream oos = new ObjectInputStream(fos);
           return (T) oos.readObject();
       }catch (Exception e) {
           return null;
       }
    }

    public void save(T t, String url){
        try {
            File f = new File(url);
            f.getParentFile().mkdir();
            f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(t);
            oos.close();
        }catch (IOException e) {
            System.out.println("Lỗi ghi file");
        }
    }
}