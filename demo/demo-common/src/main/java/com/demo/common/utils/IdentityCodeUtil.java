package com.demo.common.utils;

import java.util.Calendar;

/**
 * Created by shuzy on 2018/5/16.
 */
public class IdentityCodeUtil {
    /**
     * 通过手机号和brandCode生成验证码需要的一个临时key
     * @param phone
     * @param brandCode
     * @return
     */
    public static String getKeyByPhoneAndBrandCode(String phone,String brandCode){
        Calendar now = Calendar.getInstance();
        String year = now.get(Calendar.YEAR) + "";
        String month = (now.get(Calendar.MONTH) + 1) + "";
        String day = (now.get(Calendar.DAY_OF_MONTH)) + "";
        String key = brandCode+"_" +phone+ "_" + year + "_" + month + "_" + day;
        return key;
    }

}
