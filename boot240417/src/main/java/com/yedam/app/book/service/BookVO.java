package com.yedam.app.book.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class BookVO {

	private Integer bookNo;
	private String bookName;
	private String bookCoverimg;
	// 기본포맷 : yyyy/MM/dd
	// JSON : yyyy-MM-dd
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date bookDate;
	private Integer bookPrice;
	private String bookPublisher;
	private String bookInfo;	
	
	private int rentCount;
	private int rentTotalPrice;
	
}
