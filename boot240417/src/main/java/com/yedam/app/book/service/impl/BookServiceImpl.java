package com.yedam.app.book.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.book.mapper.BookMapper;
import com.yedam.app.book.mapper.RentMapper;
import com.yedam.app.book.service.BookService;
import com.yedam.app.book.service.BookVO;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	BookMapper bookMapper;
	
	@Autowired
	RentMapper rentMapper;

	@Override
	public List<BookVO> bookList() {
		return bookMapper.selectAllList();
	}

	@Override
	public BookVO getBookNo() {
		return bookMapper.getBookNo();
	}

	@Override
	public int bookInfoInsert(BookVO bookVO) {
		return bookMapper.insertBook(bookVO);
	}

	@Override
	public List<BookVO> rentInfo() {
		return rentMapper.selectRentInfo();
	}

}
