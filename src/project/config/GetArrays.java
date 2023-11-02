package project.config;

import java.util.ArrayList;
import java.util.List;

public class GetArrays<T> {
    public List<T> getArr(String url){
        FileTask<List<T>> task = new FileTask<>();
        if (task.getData(url) == null){
            return new ArrayList<>();
        }else {
            return task.getData(url);
        }
    }
}
