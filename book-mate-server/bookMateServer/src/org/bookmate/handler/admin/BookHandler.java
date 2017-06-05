package org.bookmate.handler.admin;

import java.util.ArrayList;
import java.util.Map;

import org.bookmate.entities.Book;
import org.bookmate.entities.User;
import org.bookmate.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 * 图书相关管理控制器
 * @author yangyuhao
 */
@Controller
public class BookHandler {
	
	@Autowired
	private BookService bookService;
	
	/**
	 * 图书列表显示
	 * @return book/book_list.jsp
	 */
	@RequestMapping(value="admin-book-list-show",method=RequestMethod.GET)
	public String listShow(Map<String, Object> requestMap, @RequestParam("page") Integer page) {
		requestMap.put("nav", "book");
		ArrayList<Book> books = (ArrayList<Book>)bookService.getAllBook();
		requestMap.put("books",books);
		
		int pageCount = books.size();  //数据条数
		int pageSize = 50;  //分页条数
		int pageMax = pageCount / pageSize;  //最大页数
		int pagePointer = 1;  //当前指向页
		if (pageMax != 0 && pageCount % pageSize != 0) {
			++pageMax;
		}
		if (pageMax == 0) {
			pageMax = 1;
		}
		if (page < 1 || page > pageMax) {
			pagePointer = 1;
		} else {
			pagePointer = page;
		}
		ArrayList<Book> pagebooks = new ArrayList<>();
		if (pageMax == 1) {
			pagebooks.addAll(books);
		} else if (pagePointer == pageMax) {
			int tmp = pageCount % pageSize;
			if (tmp == 0) {
				tmp = pageSize;
			}
			for (int i = pageSize * (pagePointer - 1); i < pageSize * (pagePointer - 1) + tmp; ++i) {
				pagebooks.add(books.get(i));
			}
		} else {
			for (int i = pageSize * (pagePointer - 1); i < pageSize * pagePointer; ++i) {
				pagebooks.add(books.get(i));
			}
		}
		requestMap.put("pageMax", pageMax);
		requestMap.put("pagePoint", pagePointer);
		requestMap.put("pageBooks",pagebooks);
		
		return "book/book_list";
	}
	/**
	 * 搜索图书显示
	 * @return book/book_list.jsp
	 */
	@RequestMapping(value="admin-book-searchbook-show",method=RequestMethod.GET)
	public String searchBookShow(Map<String, Object> requestMap,@RequestParam("bookname")String bookname){
		requestMap.put("nav", "book");
		ArrayList<Book> books = (ArrayList<Book>) bookService.getBookByNameLike(bookname);
		requestMap.put("pageBooks", books);
		return "book/book_list";
	}
	/**
	 * 新增图书显示
	 * @return book/book_add.jsp
	 */
	@RequestMapping(value="admin-book-addbook-show",method=RequestMethod.GET)
	public String addBookShow(){
		return "book/book_add";
	}
	/**
	 * 新增图书执行
	 * @param bookNumber
	 * @param bookName
	 * @param bookAuthor
	 * @param bookPress
	 * @param bookClassifyOne
	 * @param bookClassifyTwo
	 * @param bookDesc
	 * @param bookAddress
	 * @param bookSum
	 * @param bookResidue
	 * @return admin/book_list.jsp
	 */
	@RequestMapping(value="admin-book-addbook-execute",method=RequestMethod.POST)
	public String addBookExcute(@RequestParam("bookName") String bookName, @RequestParam("bookAuthor") String bookAuthor, 
			@RequestParam("bookPress") String bookPress, @RequestParam("bookClassifyOne") String bookClassifyOne, 
			@RequestParam("bookClassifyTwo") String bookClassifyTwo, @RequestParam("bookDesc")String bookDesc,
			@RequestParam("bookAddress") String bookAddress, @RequestParam("bookSum") Integer bookSum, 
			@RequestParam("bookResidue")Integer bookResidue, @RequestParam("bookNumber") Integer bookNumber, 
			@RequestParam("bookBigImage") String bookBigImage, @RequestParam("bookSmallImage") String bookSmallImage) {
		bookService.addBook2(bookNumber, bookName, bookAuthor, bookPress, bookBigImage, bookSmallImage,
				bookClassifyOne, bookClassifyTwo, bookDesc, bookAddress, bookSum, bookResidue, 1.0);
		String view = "redirect:/admin-book-list-show?page=1";
		return view;
		
	}
	
	/**
	 * 显示编辑图书信息
	 * @return
	 */
	@RequestMapping(value="admin-book-editbook-show/{id}", method=RequestMethod.GET)
	public String editBookShow(@PathVariable("id") Integer id, Map<String, Object> requestMap) {
		Book book = bookService.getBookById(id);
		requestMap.put("book", book);
		System.out.println(book.getBookClassifyOne());
		return "book/book_edit";
	}
	
	@RequestMapping(value="admin-book-editbook-execute", method=RequestMethod.POST)
	public String editBookExecute(@RequestParam("bookName") String bookName, @RequestParam("bookAuthor") String bookAuthor, 
			@RequestParam("bookPress") String bookPress, @RequestParam("bookClassifyOne") String bookClassifyOne, 
			@RequestParam("bookClassifyTwo") String bookClassifyTwo, @RequestParam("bookDesc")String bookDesc,
			@RequestParam("bookAddress") String bookAddress, @RequestParam("bookSum") Integer bookSum, 
			@RequestParam("bookResidue")Integer bookResidue, @RequestParam("bookNumber") Integer bookNumber, 
			@RequestParam("bookBigImage") String bookBigImage, @RequestParam("bookSmallImage") String bookSmallImage, @RequestParam("bookId") Integer id) {
		
		bookService.updateBook(id, bookNumber, bookName, bookAuthor, bookPress,
				bookBigImage, bookSmallImage, bookClassifyOne, bookClassifyTwo, bookDesc, 
				bookAddress, bookSum, bookResidue);
		
		return "redirect:/admin-book-list-show?page=1";
	}
	
	@RequestMapping(value="admin-book-delete-execute/{id}", method=RequestMethod.GET)
	public String deleteOneBook(@PathVariable("id") Integer id) {
		bookService.deleteOneBook(id);
		return "redirect:/admin-book-list-show?page=1";
	}
	
}
