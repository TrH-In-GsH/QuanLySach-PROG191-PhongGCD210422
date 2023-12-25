package model;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class BookModel {
	private ArrayList<Book> books;
	private String option;
	private String fileName;

	public BookModel() {
		this.books = new ArrayList<Book>();
		this.option = "";
		this.fileName = "";
	}

	public BookModel(ArrayList<Book> books) {
		this.books = books;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void insert(Book book) {
		this.books.add(book);
	}

	public void updateBook(Book book, int i) {
		try {
			books.set(i, book);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
		}

	}

	public void delete(Book book) {
		this.books.remove(book);
	}

	public void deleteBook(int book) {
		this.books.remove(book);
	}

	public boolean isExist(Book book) {
		for (Book bk : this.books) {
			if (bk.getID().equals(book.getID())) {
				return true;
			}
		}
		return false;
	}
}
