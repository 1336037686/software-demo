package com.fjut.util;

import java.util.UUID;

/**
 * @author LGX_TvT
 */
public class MD5Util {

    public static String getUUIDName(String fileName){
        return UUID.randomUUID().toString().replaceAll("-","") + fileName.substring(fileName.indexOf("."));
    }
}
