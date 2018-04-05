package com.example.LibApp;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

import org.junit.Test;

public class test_libApp {
	
	private Books b1;
	private Books b2;
	private Person p1;
	private Person p2;
	private MyLibrary ml;
	@Test
	public void testMyLibrary() {
		MyLibrary ml=new MyLibrary("test");
		assertEquals("test", ml.name);
		assertTrue(ml.books instanceof ArrayList);
		assertTrue(ml.persons instanceof ArrayList);
	}
	public void setup(){
		 b1 = new Books("Learn Java");
		 b2 = new Books("Learn Spring");
		 p1 = new Person();
		 p2 = new Person();
		 p1.setName("John Doe");
		 p2.setName("Jane Doe");
		 ml = new MyLibrary("Test");
	}
	@Test
	public void testAddBook(){
		setup();
		//test initial size of books to zero
		assertEquals(0, ml.getBooks().size());
		ml.addBook(b1);
		ml.addBook(b2);
		assertEquals(2, ml.getBooks().size());
		assertEquals(0, ml.getBooks().indexOf(b1));
		assertEquals(1, ml.getBooks().indexOf(b2));
		ml.removeBook(b1);
		assertEquals(1,ml.getBooks().size());
		assertEquals(0, ml.getBooks().indexOf(b2));
		ml.removeBook(b2);
		assertEquals(0, ml.getBooks().size());
	}
	
	@Test
	public void testCheckOut(){
		setup();
		addItems();
		assertTrue("Book did not checked out correctly", ml.checkOut(b1,p1));
		// ------>>>> assertEquals("John Doe", b1.getPerson().getName());
		assertFalse("Book was already checked out", ml.checkOut(b1,p2));
		assertTrue("Book check in failed", ml.checkIn(b1));
		assertFalse("Book is already checked in", ml.checkIn(b1));
		// ------>>>>assertFalse("Book was never checked out",ml.checkIn(b2));

		//some more test for maxbooks
		setup();
		p1.setMaxBooks(1);
		addItems();
		assertTrue("Book did not checked out correctly", ml.checkOut(b1, p1));
		assertFalse("book should not check out",ml.checkOut(b2,p1));
	}
	private void addItems() {
		ml.addBook(b1);
		ml.addBook(b2);
		ml.addPerson(p1);
		ml.addPerson(p2);
	}
	@Test
	public void testGetBooksForPerson(){
		setup();
		addItems();
		assertEquals(0,ml.getBooksForPerson(p1).size());
		ml.checkOut(b1, p1);
		ArrayList<Books> testBooks = ml.getBooksForPerson(p1);
		assertEquals(1, testBooks.size());
		assertEquals(0, testBooks.indexOf(b1));
		ml.checkOut(b2,p1);
		testBooks = ml.getBooksForPerson(p1);
		assertEquals(2, testBooks.size());
		assertEquals(1, testBooks.indexOf(b2));
	}
	@Test
	public void testGetAvailableBooks(){
		setup();
		addItems();
		ArrayList<Books> testBooks = ml.getAvailableBooks();
		assertEquals(2, testBooks.size());
		assertEquals(1, testBooks.indexOf(b2));
		ml.checkOut(b1, p1);
		testBooks = ml.getAvailableBooks();
		assertEquals(1, testBooks.size());
		assertEquals(0, testBooks.indexOf(b2));
		ml.checkOut(b2, p1);
		testBooks = ml.getAvailableBooks();
		assertEquals(0, testBooks.size());
	}
	@Test
	public void testGetUnAvailableBooks(){
		setup();
		addItems();
		assertEquals(0,ml.getUnAvailableBooks().size());
		ml.checkOut(b1, p1);
		ArrayList<Books> testBooks = ml.getUnAvailableBooks();
		assertEquals(1, testBooks.size());
		assertEquals(0, testBooks.indexOf(b1));
		ml.checkOut(b2,p1);
		testBooks = ml.getUnAvailableBooks();
		assertEquals(2, testBooks.size());
		assertEquals(1, testBooks.indexOf(b2));
	}
	@Test
	public void testToString(){
		setup();
		addItems();
		assertEquals("Test : 2 Books; 2 Persons.",ml.toString());
	}

}
