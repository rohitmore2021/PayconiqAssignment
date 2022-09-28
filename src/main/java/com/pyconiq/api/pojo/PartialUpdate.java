package com.pyconiq.api.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PartialUpdate {

@SerializedName("firstname")
@Expose
private String firstname;
@SerializedName("lastname")
@Expose
private String lastname;

/**
* No args constructor for use in serialization
*
*/
public PartialUpdate() {
}

/**
*
* @param firstname
* @param lastname
*/
public PartialUpdate(String firstname, String lastname) {
super();
this.firstname = firstname;
this.lastname = lastname;
}

public String getFirstname() {
return firstname;
}

public void setFirstname(String firstname) {
this.firstname = firstname;
}

public PartialUpdate withFirstname(String firstname) {
this.firstname = firstname;
return this;
}

public String getLastname() {
return lastname;
}

public void setLastname(String lastname) {
this.lastname = lastname;
}

public PartialUpdate withLastname(String lastname) {
this.lastname = lastname;
return this;
}

@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append(PartialUpdate.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
sb.append("firstname");
sb.append('=');
sb.append(((this.firstname == null)?"<null>":this.firstname));
sb.append(',');
sb.append("lastname");
sb.append('=');
sb.append(((this.lastname == null)?"<null>":this.lastname));
sb.append(',');
if (sb.charAt((sb.length()- 1)) == ',') {
sb.setCharAt((sb.length()- 1), ']');
} else {
sb.append(']');
}
return sb.toString();
}

}
