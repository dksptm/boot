package com.yedam.app.book.mapper;

import java.util.List;

import com.yedam.app.book.service.BookVO;

public interface RentMapper {

	// 대여현황조회
	public List<BookVO> selectRentInfo();
	
}
