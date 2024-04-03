package com.yedam.spring.xml;

public class Restaurant {
	
	// 필드. 
	private Chef chef; // new 연산자 없다. -> xml에 등록만하면 밀어넣는다.
	
	// setter 있어야 세터방식.
	public void setChef(Chef chef) {
		this.chef = chef;
	}

	// 메소드.
	public void run() {
		chef.cooking();
	}
	
}
