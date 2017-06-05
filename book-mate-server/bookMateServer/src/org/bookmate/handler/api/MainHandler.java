package org.bookmate.handler.api;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bookmate.entities.Book;
import org.bookmate.entities.Borrow;
import org.bookmate.entities.Forum;
import org.bookmate.entities.ForumAndComment;
import org.bookmate.service.BookService;
import org.bookmate.service.BorrowService;
import org.bookmate.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 信息流推送接口
 * @author yangyuhao
 *
 */
@Controller
public class MainHandler {
	
	@Autowired
	private BorrowService borrowService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ForumService forumService;
	
	/**
	 * 获取指定用户即将到期的图书
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="api-index-getBorrowWillOverdue/{userId}")
	public List<Borrow> getBorrowWillOverdue(@PathVariable("userId") Integer userId) {
		//获取用户正在借阅的图书
		ArrayList<Borrow> borrows = 
				(ArrayList<Borrow>) borrowService.getBorrowByUserAndStatus(userId, 1);
		//存放即将逾期的图书集合
		ArrayList<Borrow> borrowsWillOverdue = new ArrayList<>(); 
		 //获取当前时间戳
		long nowTime = System.currentTimeMillis(); 
		//遍历正在借阅的图书,将即将逾期的图书放到即将逾期的集合中
		for (int i = 0; i < borrows.size(); ++i) {
			//借书时间戳
			long borrowStart = borrows.get(i).getBorrowStartTime().getTime();
			int distanceDay = (int) ((nowTime - borrowStart) / 1000 / 60 / 60 / 24);
			//将不足七天的图书放到即将逾期的集合中
			if ((30 - distanceDay) <= 7 && (30 - distanceDay) >= 0) {
				borrowsWillOverdue.add(borrows.get(i));
			}
		}
		//判断即将逾期集合中是否存在值
		if (borrowsWillOverdue.size() > 0) {
			return borrowsWillOverdue;
		} else {
			return null;
		}
	}
	
	/**
	 * 获取用户逾期借阅记录接口
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="api-index-getBorrowOverdue/{userId}")
	public List<Borrow> getBorrowOverdue(@PathVariable("userId") Integer userId) {
		ArrayList<Borrow> borrows = (ArrayList<Borrow>) borrowService.getBorrowByUserAndStatus(userId, 3);
		if (borrows.size() == 0) {
			return null;
		} else {
			return borrows;
		}
	}
	
	/**
	 * 获取热门图书接口
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="api-index-getHotBook")
	public Set<Book> getHotBook() {
		ArrayList<Book> bookList = (ArrayList<Book>) bookService.getHotBook();
		HashSet<Book> bookSet = new HashSet<>();
		bookSet.addAll(bookList);
		return bookSet;
	}
	
	/**
	 * 获取热门图书接口
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="api-index-getRecommendBook/{userId}")
	public Set<Book> getRecommendBook(@PathVariable("userId") Integer userId) {
		ArrayList<Book> bookList = (ArrayList<Book>) bookService.getRecommendBook(userId);
		HashSet<Book> bookSet = new HashSet<>();
		bookSet.addAll(bookList);
		return bookSet;
	}
	
	/**
	 * 获取最新图书接口
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="api-index-getNewBook")
	public Set<Book> getNewBook() {
		ArrayList<Book> bookList = (ArrayList<Book>) bookService.getNewBook();
		HashSet<Book> bookSet = new HashSet<>();
		bookSet.addAll(bookList);
		return bookSet;
	}
	
	/**
	 * 获取书友评论图书接口
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="api-index-getForumBook")
	public Set<Forum> getForumBook() {
		ArrayList<Forum> forumList = (ArrayList<Forum>) forumService.getAllForum();
		Set<Forum> forumSet = new HashSet<>();
		forumSet.addAll(forumList);
		return forumSet;
	}
	
}
