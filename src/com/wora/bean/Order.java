package com.wora.bean;


public class Order implements Comparable<Order>{
	long id;
	double price;
	String summary;
	String details;
	boolean status;
	
	public Order() {
		super();
	}

	public Order(long id, double price, String summary, String details, boolean status) {
		super();
		this.id = id;
		this.price = price;
		this.summary = summary;
		this.details = details;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", price=" + price + ", summary=" + summary + ", details=" + details + ", status=" + status + "]";
	}


	@Override
	public int compareTo(Order o) {
		if(this.getPrice() == o.getPrice())
			return 0;
		if(this.getPrice() < o.getPrice())
			return 1;
		if(this.getPrice() > o.getPrice())
			return -1;
		
		return 0;
	}

}
