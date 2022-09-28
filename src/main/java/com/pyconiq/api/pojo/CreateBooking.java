package com.pyconiq.api.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateBooking {
	
	@SerializedName("firstname")
	@Expose
	private String firstname;
	@SerializedName("lastname")
	@Expose
	private String lastname;
	@SerializedName("totalprice")
	@Expose
	private Integer totalprice;
	@SerializedName("depositpaid")
	@Expose
	private Boolean depositpaid;
	@SerializedName("bookingdates")
	@Expose
	private BookingDates bookingdates;
	@SerializedName("additionalneeds")
	@Expose
	private String additionalneeds;

	public CreateBooking() {
	}
	public CreateBooking(String firstname, String lastname, Integer totalprice, Boolean depositpaid, BookingDates bookingdates, String additionalneeds) {
	super();
	this.firstname = firstname;
	this.lastname = lastname;
	this.totalprice = totalprice;
	this.depositpaid = depositpaid;
	this.bookingdates = bookingdates;
	this.additionalneeds = additionalneeds;
	}

	public String getFirstname() {
	return firstname;
	}

	public void setFirstname(String firstname) {
	this.firstname = firstname;
	}

	public CreateBooking withFirstname(String firstname) {
	this.firstname = firstname;
	return this;
	}

	public String getLastname() {
	return lastname;
	}

	public void setLastname(String lastname) {
	this.lastname = lastname;
	}

	public CreateBooking withLastname(String lastname) {
	this.lastname = lastname;
	return this;
	}

	public Integer getTotalprice() {
	return totalprice;
	}

	public void setTotalprice(Integer totalprice) {
	this.totalprice = totalprice;
	}

	public CreateBooking withTotalprice(Integer totalprice) {
	this.totalprice = totalprice;
	return this;
	}

	public Boolean getDepositpaid() {
	return depositpaid;
	}

	public void setDepositpaid(Boolean depositpaid) {
	this.depositpaid = depositpaid;
	}

	public CreateBooking withDepositpaid(Boolean depositpaid) {
	this.depositpaid = depositpaid;
	return this;
	}

	public BookingDates getBookingdates() {
	return bookingdates;
	}

	public void setBookingdates(BookingDates bookingdates) {
	this.bookingdates = bookingdates;
	}

	public CreateBooking withBookingdates(BookingDates bookingdates) {
	this.bookingdates = bookingdates;
	return this;
	}

	public String getAdditionalneeds() {
	return additionalneeds;
	}

	public void setAdditionalneeds(String additionalneeds) {
	this.additionalneeds = additionalneeds;
	}

	public CreateBooking withAdditionalneeds(String additionalneeds) {
	this.additionalneeds = additionalneeds;
	return this;
	}

	@Override
	public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append('{');
	sb.append("firstname");
	sb.append(':');
	sb.append(((this.firstname == null)?"<null>":this.firstname));
	sb.append(',');
	sb.append("lastname");
	sb.append(':');
	sb.append(((this.lastname == null)?"<null>":this.lastname));
	sb.append(',');
	sb.append("totalprice");
	sb.append(':');
	sb.append(((this.totalprice == null)?"<null>":this.totalprice));
	sb.append(',');
	sb.append("depositpaid");
	sb.append(':');
	sb.append(((this.depositpaid == null)?"<null>":this.depositpaid));
	sb.append(',');
	sb.append("bookingdates");
	sb.append(':');
	sb.append(((this.bookingdates == null)?"<null>":this.bookingdates));
	sb.append(',');
	sb.append("additionalneeds");
	sb.append(':');
	sb.append(((this.additionalneeds == null)?"<null>":this.additionalneeds));
	sb.append(',');
	if (sb.charAt((sb.length()- 1)) == ',') {
	sb.setCharAt((sb.length()- 1), '}');
	} else {
	sb.append('}');
	}
	return sb.toString();
	}

}
