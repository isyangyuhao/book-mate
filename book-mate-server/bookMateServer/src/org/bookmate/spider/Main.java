package org.bookmate.spider;

import org.bookmate.entities.Book;
import org.bookmate.service.BookService;
import org.bookmate.util.EncoderUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 爬虫主函数
 * @author yangyuhao
 */
public class Main {
	
	public static void main(String args []) {
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		BookService bookService = applicationContext.getBean(BookService.class);
		
		//爬取京东图书的十万条记录[12000001-12100000]
		for (int bookId = 12058860; bookId < 12500001; ++bookId) {
			try {
				//发送请求的URL地址
				String url = "https://item.jd.com/"+ bookId +".html";
				//获取网页源码数据
				String webContent = SpiderUtil.getURLContent(url, "gbk");
				//当前爬取内容编号
				int index = bookId - 12000000;
				//根据网页源码获取当前文档Document对象
				Document document = Jsoup.parse(webContent);
				//获取图书标题 
				Element bookNameElem = document.select("#name > h1").first(); 
				String bookName = bookNameElem.html();
				//将图书标题中带有括号的字段和html标签去掉
				bookName = bookName.replaceAll("\\(.*?\\)|\\{.*?}|\\[.*?]|（.*?）", "");
				bookName = bookName.replaceAll("</?[^<]+>", "");
				bookName = bookName.replaceAll("\\s*", "");
				//去掉图书标题过长的书
				if (bookName.length() > 10) {
					continue;
				}
				//获取图书作者
				Element bookAuthorElem = document.select("#p-author > a").first();
				String bookAuthor = bookAuthorElem.html();
				//获取图书出版社
				Element bookPressElem = document.select("#parameter2 a").first();
				String bookPress = bookPressElem.html();
				//获取图书的封面大图
				Element bookImgBigElem = document.select("#spec-n1 img").first();
				String bookImgBig = bookImgBigElem.attr("src");
				//获取图书的封面小图
				Element bookImgSmallElem = document.select(".lh img").first();
				String bookImgSmall = bookImgSmallElem.attr("src");
				//获取图书类别
				Elements bookClassifyElems = document.select(".breadcrumb > span a");
				String bookClassifyOne = bookClassifyElems.get(0).html();
				String bookClassifyTwo = bookClassifyElems.get(1).html();
				//获取图书内容介绍
				Element bookDescElem = document.select(".book-detail-content").first();
				String bookDesc = "";
				if (bookDescElem != null) {
					bookDesc = bookDescElem.html();
					//去掉html标记
					bookDesc = bookDesc.replaceAll("</?[^<]+>", "");
					bookDesc = bookDesc.replaceAll("\\s*", "");
					bookDesc = bookDesc.replaceAll("&nbsp;", "");
				}
				
				//转换编码方式
				bookName = EncoderUtil.EncoderByUtf8(bookName);
				bookAuthor = EncoderUtil.EncoderByUtf8(bookAuthor);
				bookPress = EncoderUtil.EncoderByUtf8(bookPress);
				bookClassifyOne = EncoderUtil.EncoderByUtf8(bookClassifyOne);
				bookClassifyTwo = EncoderUtil.EncoderByUtf8(bookClassifyTwo);
				bookDesc = EncoderUtil.EncoderByUtf8(bookDesc);
				
				//执行保存
				bookService.addBook(bookId, bookName, bookAuthor, bookPress, bookImgBig,
						bookImgSmall, bookClassifyOne, bookClassifyTwo, bookDesc);
				
				//打印爬取内容
				System.out.println("序号(index): " + index);
				System.out.println("编号(bookId): " + bookId);
				System.out.println("图书名(bookName): " + bookName);
				System.out.println("图书作者(bookAuthor): " + bookAuthor);
				System.out.println("图书出版社(bookPress): " + bookPress);
				System.out.println("图书封面大图(bookImgBig): " + bookImgBig);
				System.out.println("图书封面小图(bookImgSmall): " + bookImgSmall);
				System.out.println("图书一级分类(bookClassifyOne): " + bookClassifyOne);
				System.out.println("图书二级分类(bookClassifyTwo): " + bookClassifyTwo);
				System.out.println("图书内容介绍(bookDesc): " + bookDesc);
				System.out.println("=============================");
				
			} catch(Exception e) {
				e.printStackTrace();
				continue;
			}
		}
				
	}
	
}

