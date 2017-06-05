package org.bookmate.handler.api;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bookmate.entities.Book;
import org.bookmate.service.BookService;
import org.bookmate.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 图书相关操作API
 * @author yangyuhao
 */
@Controller
public class BookAPIHandler {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private RecordService recordService;
	
	/**
	 * 获取全部图书一级分类接口
	 * @return 分类集合yang
	 */
	@ResponseBody
	@RequestMapping(value="api-book-classifyone-all")
	public ArrayList<String> getAllClassifyOne() {
		ArrayList<String> list = (ArrayList<String>) bookService.getAllClassifyOne();
		return list;
	}
	
	/**
	 * 根据一级分类获取对应图书列表接口
	 * @return 图书集合
	 */
	@ResponseBody
	@RequestMapping(value="api-book-book-byclassifyone/{classifyOne}")
	public ArrayList<Book> getBookByClassifyOne(@PathVariable("classifyOne") String classifyOne) {
		ArrayList<Book> books = (ArrayList<Book>) bookService.getBookByClassifyOne(classifyOne);
		return books;
	}
	
	/**
	 * 根据id获取图书信息接口
	 * @return 图书信息
	 */
	@ResponseBody
	@RequestMapping(value="api-book-book-byid/{id}")
	public Book getBookById(@PathVariable("id") Integer id) {
		Book book = bookService.getBookById(id);
		return book;
	}
	
	/**
	 * 根据图书名模糊查询符合的图书
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="api-book-book-bynamelike/{name}")
	public ArrayList<Book> getBookByNameLike(@PathVariable("name") String name) {
		ArrayList<Book> books = (ArrayList<Book>) bookService.getBookByNameLike(name);
		return books;
	}
	
	/**
	 * 记录用户浏览信息
	 * @param userId 用户id
	 * @param bookId 图书id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="api-record-browse/{userId}/{bookId}")
	public String recordBrowse(@PathVariable("userId") Integer userId, 
			@PathVariable("bookId") Integer bookId) {
		recordService.recordBrowse(userId, bookId);
		return "1";
	}
	
	@ResponseBody
	@RequestMapping(value="api-book-recommend/{bookId}")
	public Set<Book> getBookRecommend(@PathVariable("bookId") Integer bookId) {
		ArrayList<Book> bookList = (ArrayList<Book>) bookService.getBookRecommendBook(bookId);
		Set<Book> bookSet = new HashSet<>();
		bookSet.addAll(bookList);
		return bookSet;
	}
	
}
