package com.yedam.app.book.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.app.book.service.BookService;
import com.yedam.app.book.service.BookVO;

@Controller
public class BookController {
	
	@Autowired
	BookService bookService;
	
	// 도서전체조회 : GET
	@GetMapping("bookList")
	public String bookList(Model model) {
		List<BookVO> list = bookService.bookList();
		model.addAttribute("books", list);
		return "book/list";
	}
	
	// 도서등록 - 페이지 : GET
	@GetMapping("bookInsert")
	public String bookInsertForm(Model model) {
		BookVO bookVO = bookService.getBookNo();
		model.addAttribute("book", bookVO);
		return "book/insert";
	}
	
	// 도서등록 - 처리 : POST
	@PostMapping("bookInsert")
	public String bookInsertProcess(BookVO bookVO) {
		bookService.bookInfoInsert(bookVO);
		return "redirect:bookList";
	}
	
	// 도서대여현황 : GET
	@GetMapping("rentList")
	public String rentInfo(Model model) {
		List<BookVO> list = bookService.rentInfo();
		model.addAttribute("rents", list);
		return "book/rentInfo";
	}
	
}
