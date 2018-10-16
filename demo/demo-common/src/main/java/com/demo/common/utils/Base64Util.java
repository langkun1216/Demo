package com.demo.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 
 * @author WindyHu
 *
 */
public class Base64Util {
	
	private static final char[] legalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

	public static String encode(byte[] data)
	{
		int start = 0;
		int len = data.length;
		StringBuffer buf = new StringBuffer(data.length * 3 / 2);

		int end = len - 3;
		int i = start;
		int n = 0;

		while (i <= end)
		{
			int d = ((((int) data[i]) & 0x0ff) << 16) | ((((int) data[i + 1]) & 0x0ff) << 8) | (((int) data[i + 2]) & 0x0ff);

			buf.append(legalChars[(d >> 18) & 63]);
			buf.append(legalChars[(d >> 12) & 63]);
			buf.append(legalChars[(d >> 6) & 63]);
			buf.append(legalChars[d & 63]);

			i += 3;

			if (n++ >= 14)
			{
				n = 0;
				buf.append(" ");
			}
		}

		if (i == start + len - 2)
		{
			int d = ((((int) data[i]) & 0x0ff) << 16) | ((((int) data[i + 1]) & 255) << 8);

			buf.append(legalChars[(d >> 18) & 63]);
			buf.append(legalChars[(d >> 12) & 63]);
			buf.append(legalChars[(d >> 6) & 63]);
			buf.append("=");
		}
		else if (i == start + len - 1)
		{
			int d = (((int) data[i]) & 0x0ff) << 16;

			buf.append(legalChars[(d >> 18) & 63]);
			buf.append(legalChars[(d >> 12) & 63]);
			buf.append("==");
		}

		return buf.toString();
	}

	private static int decode(char c)
	{
		if (c >= 'A' && c <= 'Z') return ((int) c) - 65;
		else if (c >= 'a' && c <= 'z') return ((int) c) - 97 + 26;
		else if (c >= '0' && c <= '9') return ((int) c) - 48 + 26 + 26;
		else switch (c)
		{
			case '+':
				return 62;
			case '/':
				return 63;
			case '=':
				return 0;
			default:
				throw new RuntimeException("unexpected code: " + c);
		}
	}

	/**
	 * Decodes the given Base64 encoded String to a new byte array. The byte
	 * array holding the decoded data is returned.
	 */

	public static byte[] decode(String s)
	{

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try
		{
			decode(s, bos);
		}
		catch (IOException e)
		{
			throw new RuntimeException();
		}
		byte[] decodedBytes = bos.toByteArray();
		try
		{
			bos.close();
			bos = null;
		}
		catch (IOException ex)
		{
			System.err.println("Error while decoding BASE64: " + ex.toString());
		}
		return decodedBytes;
	}

	private static void decode(String s, OutputStream os) throws IOException
	{
		int i = 0;

		int len = s.length();

		while (true)
		{
			while (i < len && s.charAt(i) <= ' ')
				i++;

			if (i == len) break;

			int tri = (decode(s.charAt(i)) << 18) + (decode(s.charAt(i + 1)) << 12) + (decode(s.charAt(i + 2)) << 6) + (decode(s.charAt(i + 3)));

			os.write((tri >> 16) & 255);
			if (s.charAt(i + 2) == '=') break;
			os.write((tri >> 8) & 255);
			if (s.charAt(i + 3) == '=') break;
			os.write(tri & 255);

			i += 4;
		}
	}
	
	/*public static void main(String[] args) throws Exception
	{
		// Base64编码 MjE2ODg4MDAwMDAyOTU=
		
		String appkey ="EP02300000300" +"3";
		
		appkey  = Base64Util.encode(appkey.getBytes("UTF-8"));
		
		System.out.println(appkey);
		
		System.out.println(new String (Base64Util.decode(appkey)));
		
		String random =  RandomHelper.getRandomNumbersAndLetters(40);
		
		String appSecret =  Md5Util.MD5("EP02300000300"+ random);
		
		System.out.println(appSecret);
	//	F07177F915F6D75C40F313E419BC7BB7
		
	}*/
	
	public static String getBASE64(String s) {
		if (s == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
	}
	// 将 BASE64 编码的字符串 s 进行解码\解密
	public static String getFromBASE64(String s) {
		if (s == null)
			return null;
		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b,"UTF-8");
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 加密
	 * @param ming
	 * @return
	 */
	public static String mTOa(Object ming){
		return Base64Util.getBASE64((String)ming);
	}

	/**
	 * 解密
	 * @param an
	 * @return
	 */
	public static String aTOm(String an){
		return Base64Util.getFromBASE64(an);
	}

}
