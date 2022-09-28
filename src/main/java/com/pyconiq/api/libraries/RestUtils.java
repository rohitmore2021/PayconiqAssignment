package com.pyconiq.api.libraries;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import com.pyconiq.api.common.Constants;
import com.pyconiq.api.common.GlobalConfig;

public class RestUtils {
	private static String strAccessToken = null;

	public static String setAccessToken(GlobalConfig glconfig) {

		try {
			RestAssured.baseURI = glconfig.getURL();
			Map<String, String> data = new HashMap<String, String>();
			data.put("username", glconfig.getAuthUserName());
			data.put("password", glconfig.getAuthPswd());
			Response resp = RestAssured.given().contentType(ContentType.JSON)
					.body(data).post(Constants.POST_AUTH);
			strAccessToken = resp.jsonPath().get("token");
		} catch (Exception e) {
			System.out.println("Could not generate access Token");
		}
		return strAccessToken;
	}

	public static Response createBooking(GlobalConfig glconfig, String payload) {
		RestAssured.baseURI = glconfig.getURL();
		return RestAssured.given().contentType(ContentType.JSON).body(payload)
				.post(Constants.POST_CREATE_Booking);
	}

	public static Response updateBooking(GlobalConfig glconfig, String payload,
			int bookingid, String token) {
		RestAssured.baseURI = glconfig.getURL();
		return RestAssured.given().contentType(ContentType.JSON)
				.header("Cookie", "token=" + token)
				.pathParam("id", bookingid).body(payload)
				.put(Constants.PUT_UPDATE_BOOKING);
	}

	public static Response partialUpdate(GlobalConfig glconfig, String payload,
			int bookingid, String token) {
		RestAssured.baseURI = glconfig.getURL();
		return RestAssured.given().contentType(ContentType.JSON)
				.header("Cookie", "token=" + token)
				.pathParam("id", bookingid).body(payload)
				.patch(Constants.PUT_PARTIALUPDATE_BOOKING);
	}

	public static Response deleteBooking(GlobalConfig glconfig, int bookingid,
			String BasicAuth) {
		RestAssured.baseURI = glconfig.getURL();
		return RestAssured.given().header("Authorization", "Basic " + BasicAuth)
				.pathParam("id", bookingid).delete(Constants.DELETE_BOOKING);
	}

	public static Response getBookingIds(GlobalConfig glconfig) {
		RestAssured.baseURI = glconfig.getURL();
		return RestAssured.given().get(Constants.GET_booking_IDs);
	}

	public static Response getBooking(GlobalConfig glconfig, int bookingid) {
		RestAssured.baseURI = glconfig.getURL();
		return RestAssured.given().pathParam("id", bookingid)
				.get(Constants.GET_booking);
	}

}
