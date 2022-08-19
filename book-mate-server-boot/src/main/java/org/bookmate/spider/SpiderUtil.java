package org.bookmate.spider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * 爬虫工具类,对数据进行搜集、存取
 * @author yangyuhao
 */
public class SpiderUtil {
	
	/**
	 * 获得urlStr对应的网页源码
	 * @param urlStr 传入需要获得的URL地址
	 * @return URL地址的源码
	 */
	public static String getURLContent(String urlStr, String charset) {
		StringBuilder stringBuilder = new StringBuilder();
		try {
			URL url = new URL(urlStr);
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName(charset)));
			String temp = "";
			while ((temp=reader.readLine()) != null) {
				stringBuilder.append(temp);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}

	
}
