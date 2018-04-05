package com.example.LibApp;

import java.util.*;

public class MyLibrary {
	public String name;

	public MyLibrary(String name) {
		this.name = name;
	}
	
	ArrayList<Books> books = new ArrayList<Books>();
	ArrayList<Person> persons = new ArrayList<Person>();
	ArrayList<Books> availableBooks = books;
	ArrayList<Books> unavailableBooks = new ArrayList<Books>();
	

	public ArrayList<Books> getBooks() {
		return books;
	}
	
	public void addBook(Books b) {
		books.add(b);
	}
	
	public void removeBook(Books b) {
		books.remove(b);
	}
	
	public Boolean checkIn(Books b) {
		
		if(books.indexOf(b) == -1) {
			System.out.println("Book check in failed");;
			books.add(b);
			availableBooks.add(b);
			unavailableBooks.remove(b);
			return true;
		}
		else {
			System.out.println("Book is already checked in");
			return false;
		}	
	}
	
	public Boolean checkOut(Books b, Person p) {
		
		if(books.indexOf(b) != -1 && p.maxBooks > 0) {
			books.remove(b);
			availableBooks.remove(b);
			unavailableBooks.add(b);
			p.maxBooks = p.maxBooks - 1;
			p.personbooklist.add(b);
			System.out.println("Book did not checked out correctly");
			return true;
		}
		else if(p.maxBooks<=0) {
			System.out.println("book should not check out");
			return false;
		}
		else {
			System.out.println("Book was already checked out");
			return false;
		}
	}
	
	public void addPerson(Person p) {
		persons.add(p);
	}
	
	public ArrayList<Books> getBooksForPerson(Person p) {
		return p.personbooklist;
	}
	
	public ArrayList<Books> getAvailableBooks(){
		return availableBooks;
	}
	
	public ArrayList<Books> getUnAvailableBooks(){
		return unavailableBooks;
	}
	
	public String toString() {
		System.out.println(persons.size());
		String s = "Test : "+books.size()+" Books; "+persons.size()+ " Persons.";
		return s;
	}
	
}
