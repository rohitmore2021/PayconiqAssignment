package com.pyconiq.api.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateBookingResponse {

@SerializedName("bookingid")
@Expose
private Integer bookingid;
@SerializedName("booking")
@Expose
private CreateBooking booking;

/**
* No args constructor for use in serialization
*
*/
public CreateBookingResponse() {
}

/**
*
* @param booking
* @param bookingid
*/
public CreateBookingResponse(Integer bookingid, CreateBooking booking) {
super();
this.bookingid = bookingid;
this.booking = booking;
}

public Integer getBookingid() {
return bookingid;
}

public void setBookingid(Integer bookingid) {
this.bookingid = bookingid;
}

public CreateBookingResponse withBookingid(Integer bookingid) {
this.bookingid = bookingid;
return this;
}

public CreateBooking getBooking() {
return booking;
}

public void setBooking(CreateBooking booking) {
this.booking = booking;
}

public CreateBookingResponse withBooking(CreateBooking booking) {
this.booking = booking;
return this;
}

@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append(CreateBookingResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
sb.append("bookingid");
sb.append('=');
sb.append(((this.bookingid == null)?"<null>":this.bookingid));
sb.append(',');
sb.append("booking");
sb.append('=');
sb.append(((this.booking == null)?"<null>":this.booking));
sb.append(',');
if (sb.charAt((sb.length()- 1)) == ',') {
sb.setCharAt((sb.length()- 1), ']');
} else {
sb.append(']');
}
return sb.toString();
}

}
