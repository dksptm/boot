package com.yedam.app.book.service;

import java.util.List;

public interface BookService {

	// 도서전체조회.
	public List<BookVO> bookList();
	
	// 도서번호조회.
	public BookVO getBookNo();
	
	// 도서등록.
	public int bookInfoInsert(BookVO bookVO);
	
	// 도서대여현황
	public List<BookVO> rentInfo();
}
