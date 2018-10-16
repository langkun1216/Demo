package com.demo.common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.extern.java.Log;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

/**
 * @Description : 生成二维码
 * @Author : xiongyong
 * @Date : 2018/8/3 14:18
 */
@Log
public class QrcodeUtil {

   public static String createQrcode(String url) {
       String format = "png";
       Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
       hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
       hints.put(EncodeHintType.MARGIN, 0);
       BitMatrix bitMatrix = null;
       try {

           bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, 300, 300,hints);

           BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
           ByteArrayOutputStream os = new ByteArrayOutputStream();//新建流。
           ImageIO.write(image, format, os);//利用ImageIO类提供的write方法，将bi以png图片的数据模式写入流。
           byte b[] = os.toByteArray();//从流中获取数据数组。
           return new BASE64Encoder().encode(b);
       } catch (WriterException e) {
          log.info(e.toString());
       } catch (IOException e) {
           log.info(e.toString());
       }
       return "";
   }
}
