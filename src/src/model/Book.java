/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Book implements Serializable {
	private String ID;
	private String title;
	private float price;
	private String author;
	private Date publicationTime;
	private String publisher;
	public Object getPublicationTime;

	public Book(String iD, String title, float price, String author, Date publicationTime, String publisher) {
		this.ID = iD;
		this.title = title;
		this.price = price;
		this.author = author;
		this.publicationTime = publicationTime;
		this.publisher = publisher;
	}

	public Book() {
		// TODO Auto-generated constructor stub
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublicationTime() {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		if (publicationTime == null) {
			return "";
		}
		return simpleDateFormat.format(publicationTime);
	}

	public void setPublicationTime(Date publicationTime) {
		this.publicationTime = publicationTime;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		return "Book [ID=" + ID + ", title=" + title + ", price=" + price + ", author=" + author + ", publicationTime="
				+ publicationTime + ", publisher=" + publisher + "]\n";
	}


}
