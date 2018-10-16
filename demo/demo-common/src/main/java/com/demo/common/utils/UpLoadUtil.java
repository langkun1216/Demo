package com.demo.common.utils;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by zou on 2018/6/22.
 */
public class UpLoadUtil {

    /**
     * 文件上传
     * @param file
     * @param filePath
     * @param fileName
     * @throws Exception
     */
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    /**
     * 图片上传
     * @param bytes
     * @param filePath
     * @throws Exception
     */
    public static void uploadImg(byte[] bytes, String filePath) throws Exception {
        File file = new File(filePath);
        // 检测是否存在目录
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        FileOutputStream out = new FileOutputStream(filePath);
        out.write(bytes);
        out.flush();
        out.close();
    }
}
