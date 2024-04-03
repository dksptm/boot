package com.yedam.spring.java;

public class Restaurant {
	
	private Chef chef;
	
	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void run() {
		chef.cooking();
	}
	
}
