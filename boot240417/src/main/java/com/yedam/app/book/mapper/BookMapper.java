package com.yedam.app.book.mapper;

import java.util.List;

import com.yedam.app.book.service.BookVO;

public interface BookMapper {
	
	// 도서전체조회.
	public List<BookVO> selectAllList();
	
	// (새로운)도서번호조회
	public BookVO getBookNo();
	
	// 도서등록 
	public int insertBook(BookVO bookVO);

}
