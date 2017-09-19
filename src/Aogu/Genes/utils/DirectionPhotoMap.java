package Aogu.Genes.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 25065 on 2016/7/19.
 */
public class DirectionPhotoMap {

    private static Map<String,String> map=new HashMap<String,String>();

    public static void putMap(String hello,String world){
        map.put(hello,world);
    }

    public static Map<String,String> returnMap(){
        return map;
    }

    public static void clearMap(){
        map.clear();
    }
}
