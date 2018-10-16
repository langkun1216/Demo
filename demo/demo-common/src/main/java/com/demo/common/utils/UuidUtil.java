package com.demo.common.utils;

import java.util.UUID;

/**
 * Created by shuzy on 2018/5/11.
 */
public class UuidUtil {
    public static String getUuid(){
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid;
    }
}
