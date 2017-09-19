package Aogu.Genes.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 25065 on 2016/7/16.
 */
public class VersionMap {

    private static Map<String,Integer> versionMap=new HashMap<String,Integer>();

    public static Integer getWatchMap(){
        if (versionMap.containsKey("watch")){
            Integer count=versionMap.get("watch");
            versionMap.put("watch",count+1);
            return count+1;
        }else {
            versionMap.put("watch",1);
            return 1;
        }
    }

    public static Integer getDownloadMap(){
        if (versionMap.containsKey("download")){
            Integer count=versionMap.get("download");
            return count;
        }else {
            versionMap.put("download",0);
            return 0;
        }
    }

    public static void downloadMapAdd1(){
        if (versionMap.containsKey("download")){
            Integer count=versionMap.get("download");
            versionMap.put("download",count+1);
        }else {
            versionMap.put("download",1);
        }
    }

    public static Map<String,Integer> returnVersionMap(){
        return versionMap;
    }

    public static void clearMap(){
        versionMap.clear();
    }
}
