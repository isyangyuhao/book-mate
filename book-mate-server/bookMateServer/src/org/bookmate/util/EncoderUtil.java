package org.bookmate.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 编码相关工具类
 * @author yangyuhao
 */
public class EncoderUtil {
	
	/**
	 * 将字符串进行md5加密
	 * @param str 带加密字符串
	 * @return newStr 加密后的新字符串
	 */
	public static String EncoderByMd5(String str) {
		String newStr = "";
	    try {
	        // 生成一个MD5加密计算摘要
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        // 计算md5函数
	        md.update(str.getBytes());
	        // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
	        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
	        newStr = new BigInteger(1, md.digest()).toString(16);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return newStr;
	}
	
	/**
	 * 将字符串编码方式转换为UTF-8
	 * @param str 待转换字符串
	 * @return newStr 转换为utf-8的字符串
	 * @throws UnsupportedEncodingException
	 */
	public static String EncoderByUtf8(String str) throws UnsupportedEncodingException {
		String newStr = new String(str.getBytes(), "UTF-8");
		return newStr;
	}
	
}
