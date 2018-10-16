package com.demo.common.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 密钥工具类
 */
public class SecuritySecretUtil {
	private static String HMAC_SHA1 = "HmacSHA1";
	private static String hexDigits[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
	private static String randomValue = "d6f9df20697663425c8d3cdc72653c5f";
	private static String salt;

	public static void main(String[] args) throws Exception {

		Map<Object, Object> params = new HashMap<Object, Object>();
		params.put("userName", "hutingfeng");
		params.put("userPwd", "HUt031139");
		String currentMills = "1526927848231";
		params.put("currentMills", currentMills);
		salt = getSalt(currentMills);
		params.put("salt", salt);
		String sign = getSignStr(params, currentMills);
		System.out.println("sign=====================================" + sign);
	}


	/**
	 * 第三方，获取sign示例
	 *
	 * @param parm      需要传递ecar的参数信息
	 * @param appSecret 不参与参数传递的 只用于加密使用
	 * @return
	 * @throws Exception
	 */
	public static String getSignStr(Map<Object, Object> parm, String currentMills) throws Exception {

		//step 1 排序 
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.putAll(parm);
		//step 2 获取 Base64
		String base64Str = getBase64Str(parameters);
		//step 3 获取盐值
		salt = getSalt(currentMills);
		//step 4 获取加密后的signStr
		return getSignStr(salt, base64Str);
	}

	//用于加密防篡改数据 2018-5-12
	public static String getSecretStr(Map<String, Object> parm) {

		//step 1 排序
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.putAll(parm);
		String secretStr = "";
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			Object v = entry.getValue();
			if (v != null) {
				secretStr += v.toString();
			}

		}
//		System.out.println(secretStr.replaceAll("\r\n",""));
		String md5SecretStr = MD5.GetMD5Code(secretStr.replaceAll("\r","").replaceAll("\n",""));
		return md5SecretStr;
	}


	/**
	 * 4444444444444444444
	 * @param key   加密key @see getKey方法
	 * @param base64Str 加密字符串
	 * @return
	 * @throws Exception 
	 */
	private static String getSignStr(String key, String base64Str) throws Exception 
	{
		//step 1 构建以key为基准的HMAC_SHA1
		Mac mac = Mac.getInstance(HMAC_SHA1);
		SecretKeySpec secret = new SecretKeySpec(key.getBytes("UTF-8"), mac.getAlgorithm());
		mac.init(secret);
		//step 2 进行HmacSHA1处理
		byte[] origin = mac.doFinal(base64Str.getBytes("UTF-8"));
		//step 3 进行MD5处理
		MessageDigest md = MessageDigest.getInstance("MD5");

		byte[] b = md.digest(origin);
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++){
			int n = b[i];
			if (n < 0) n += 256;
			int d1 = n / 16;
			int d2 = n % 16;
			resultSb.append(hexDigits[d1] + hexDigits[d2]);
		}
		return resultSb.toString();
	}

	
	/**
	 * 333333333333333
	 * 获取盐值
	 * 注：第三方使用此方法
	 * @param appKey  
	 * @param appSecret
	 * @return
	 */
	private static String getSalt(String currentMills)
	{
		salt = md5(randomValue + currentMills);
		return salt ;
	}
	
	
	/**
	 * 222222222222222
	 * 获取base64字符串
	 * @param parameters
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings({ "rawtypes" })
	private static String getBase64Str(Map<Object, Object> parameters) throws UnsupportedEncodingException {
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext())
		{
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			Object v = entry.getValue();
			if (null != v && !"".equals(v))
			{
				sb.append(k + "=" + v + "&");
			}
		}
		sb.deleteCharAt(sb.lastIndexOf("&"));
		return Base64Util.encode(sb.toString().getBytes("UTF-8")).replaceAll(" ", "");
	}


	/**
	 * base64编码
	 * 
	 * @author WindyHu
	 * @param str
	 * @time 2018-03-09
	 * @return
	 */
	public static String Base64EnCode(String str) {
		// str = "vin=LDC234325345&cityName=武汉";
		byte[] buffer = str.getBytes();
		String base64Str = "";
		//  对字节数组Base64编码
		Base64 base64 = new Base64();
		// Base64编码过的字节数组字符串
		String encodedText = base64.encodeToString(buffer);
		base64Str = encodedText;

		return base64Str;
	}

	/**
	 * hmacSHA1
	 * 
	 * @author WindyHu
	 * @param base64Str
	 * @param appKey
	 * @time 2018-03-09
	 * @return
	 */
	public static String hmacSHA1(String base64Str, String appKey) {
		byte[] base64byte = base64Str.getBytes();
		byte[] appKeybyte = appKey.getBytes();

		try {
			SecretKeySpec signingKey = new SecretKeySpec(appKeybyte, "HmacSHA1");
			Mac mac = Mac.getInstance("HmacSHA1");
			mac.init(signingKey);
			return byte2hex(mac.doFinal(base64byte));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		return null;

	}

	// 二行制转字符串
	public static String byte2hex(byte[] b) {
		StringBuilder hs = new StringBuilder();
		String stmp;
		for (int n = 0; b != null && n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1)
				hs.append('0');
			hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}

	/**
	 * 生成32位md5码
	 * 
	 * @param password
	 * @time 2018-03-09
	 * @return
	 */
	public static String md5(String pw) {

		try {
			// 得到一个信息摘要器
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] result = digest.digest(pw.getBytes());
			StringBuffer buffer = new StringBuffer();
			// 把每一个byte 做一个与运算 0xff;
			for (byte b : result) {
				// 与运算
				int number = b & 0xff;// 加盐
				String str = Integer.toHexString(number);
				if (str.length() == 1) {
					buffer.append("0");
				}
				buffer.append(str);
			}
			// 标准的md5加密后的结果
			return buffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * @Description:加密-32位小写
	 * @author WindyHu
	 * @time 2018-03-13
	 */
	public static String encrypt32(String encryptStr) {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			byte[] md5Bytes = md5.digest(encryptStr.getBytes());
			StringBuffer hexValue = new StringBuffer();
			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16)
					hexValue.append("0");
				hexValue.append(Integer.toHexString(val));
			}
			encryptStr = hexValue.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return encryptStr;
	}

	/**
	 * @Description:加密-16位小写
	 * @author WindyHu
	 * @time 2018-03-13
	 */
	// 静态方法，便于作为工具类
	public static String getMd5_16(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			// 32位加密
			// return buf.toString();
			// 16位的加密
			return buf.toString().substring(8, 24);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

}
