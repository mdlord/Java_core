package com.example.LibApp;

import java.util.*;

public class Person {
	
	private String name;
	private int number;
	public int maxBooks = 2;
	public ArrayList<Books> personbooklist = new ArrayList<Books>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public void setMaxBooks(int num) {
		this.maxBooks = num;
	}
	
}
