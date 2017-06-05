package org.bookmate.handler.admin;

import java.util.ArrayList;
import java.util.Map;

import org.bookmate.entities.Book;
import org.bookmate.entities.Borrow;
import org.bookmate.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 借阅相关控制器
 * @author yangyuhao
 */
@Controller
public class BorrowHandler {
	
	@Autowired
	private BorrowService borrowService;
	
	@RequestMapping(value="admin-borrow-list-show", method=RequestMethod.GET)
	public String listShow(Map<String, Object> requestMap, @RequestParam("page") Integer page) {
		requestMap.put("nav", "borrow");
		ArrayList<Borrow> borrows = (ArrayList<Borrow>) borrowService.getAllBorrow();
		requestMap.put("borrows", borrows);
		
		int pageCount = borrows.size();  //数据条数
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
		ArrayList<Borrow> pageBorrows = new ArrayList<>();
		if (pageMax == 1) {
			pageBorrows.addAll(borrows);
		} else if (pagePointer == pageMax) {
			int tmp = pageCount % pageSize;
			if (tmp == 0) {
				tmp = pageSize;
			}
			for (int i = pageSize * (pagePointer - 1); i < pageSize * (pagePointer - 1) + tmp; ++i) {
				pageBorrows.add(borrows.get(i));
			}
		} else {
			for (int i = pageSize * (pagePointer - 1); i < pageSize * pagePointer; ++i) {
				pageBorrows.add(borrows.get(i));
			}
		}
		requestMap.put("pageMax", pageMax);
		requestMap.put("pagePoint", pagePointer);
		requestMap.put("pageBorrows",pageBorrows);
		
		return "borrow/borrow_list";
	}
		
}
