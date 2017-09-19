package Aogu.Genes.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 25065 on 2016/7/19.
 */
public class NewsPhotoMap {

    private static Map<String,String> newsPhotoMap=new HashMap<String,String>();

    public static void putMap(String hello,String world){
        newsPhotoMap.put(hello,world);
    }

    public static Map<String,String> returnMap(){
        return newsPhotoMap;
    }

    public static void clearMap(){
        newsPhotoMap.clear();
    }
}
