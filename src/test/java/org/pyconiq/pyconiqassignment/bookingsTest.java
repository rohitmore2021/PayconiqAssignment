package org.pyconiq.pyconiqassignment;

import io.restassured.response.Response;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pyconiq.api.common.GlobalConfig;
import com.pyconiq.api.libraries.RestUtils;
import com.pyconiq.api.pojo.BookingDates;
import com.pyconiq.api.pojo.CreateBooking;
import com.pyconiq.api.pojo.CreateBookingResponse;
import com.pyconiq.api.pojo.PartialUpdate;

public class bookingsTest {
	GlobalConfig config;
	public Assert junitassert;
	public RestUtils utils;
	public Gson gson;
	public String tokenForUpdate;
	public String basicAuth;
	public String tokenCreated;
	public CreateBooking createbooking;
	public BookingDates bookingdates;
	public PartialUpdate partialupdate;
	public CreateBookingResponse createbookingresponse;
	public Faker faker;

	@Before
	public void setUP() {
		config = new GlobalConfig();
		utils = new RestUtils();
		basicAuth = config.getBasicAuth();
		tokenForUpdate = config.getTokenForUpdate();
		tokenCreated = utils.setAccessToken(config);
		gson = new GsonBuilder().setPrettyPrinting().create();
		faker = new Faker();
	}

	@Test
	public void verifyCreateBooking() {
		try {
			bookingdates = new BookingDates("2022-06-01", "2022-06-08");
			createbooking = new CreateBooking(faker.name().firstName(), faker
					.name().lastName(), 20000, true, bookingdates, "Breakfast");
			String payload = gson.toJson(createbooking);
			Response response = utils.createBooking(config, payload);

			// Assert response code
			junitassert.assertEquals(response.getStatusCode(), 200);

			// Assert response body
			createbookingresponse = gson.fromJson(
					response.getBody().asString(), CreateBookingResponse.class);
			junitassert.assertEquals(createbookingresponse.getBookingid() > 0,
					true);
			junitassert.assertEquals(createbookingresponse.getBooking()
					.getFirstname(), createbooking.getFirstname());
			junitassert.assertEquals(createbookingresponse.getBooking()
					.getLastname(), createbooking.getLastname());
			junitassert.assertEquals(createbookingresponse.getBooking()
					.getTotalprice(), createbooking.getTotalprice());
			junitassert.assertEquals(createbookingresponse.getBooking()
					.getDepositpaid(), createbooking.getDepositpaid());
			junitassert.assertEquals(createbookingresponse.getBooking()
					.getBookingdates().getCheckin(), createbooking
					.getBookingdates().getCheckin());
			junitassert.assertEquals(createbookingresponse.getBooking()
					.getBookingdates().getCheckout(), createbooking
					.getBookingdates().getCheckout());
			junitassert.assertEquals(createbookingresponse.getBooking()
					.getAdditionalneeds(), createbooking.getAdditionalneeds());
		} catch (Exception e) {
			System.out.println("Caught Exception in verifyCreateBooking :" + e);
		}
	}

	@Test
	public void verifyUpdateBooking() {
		try {

			// First Create booking
			bookingdates = new BookingDates("2022-06-01", "2022-06-08");
			createbooking = new CreateBooking(faker.name().firstName(), faker
					.name().lastName(), 20000, true, bookingdates, "Breakfast");
			String payload = gson.toJson(createbooking);
			Response response = utils.createBooking(config, payload);
			junitassert.assertEquals(response.getStatusCode(), 200);
			createbookingresponse = gson.fromJson(
					response.getBody().asString(), CreateBookingResponse.class);
			Integer bookingid = createbookingresponse.getBookingid();

			// Next update booking
			createbooking.setDepositpaid(false);
			createbooking.setTotalprice(15000);
			String payloadForUpdate = gson.toJson(createbooking);
			Response updateResponse = utils.updateBooking(config,
					payloadForUpdate, bookingid, tokenCreated);
			junitassert.assertEquals(updateResponse.getStatusCode(), 200);
			CreateBooking updateRespReceived = gson.fromJson(updateResponse
					.getBody().asString(), CreateBooking.class);
			// Assert updated values
			junitassert.assertEquals(updateRespReceived.getDepositpaid(),
					createbooking.getDepositpaid());
			junitassert.assertEquals(updateRespReceived.getTotalprice(),
					createbooking.getTotalprice());
		} catch (Exception e) {
			System.out.println("Caught Exception in verifyUpdateBooking :" + e);
		}
	}

	@Test
	public void verifyPartialUpdate() {
		try {
			// First Create booking
			bookingdates = new BookingDates("2022-06-01", "2022-06-08");
			createbooking = new CreateBooking(faker.name().firstName(), faker
					.name().lastName(), 20000, true, bookingdates, "Breakfast");
			String payload = gson.toJson(createbooking);
			Response response = utils.createBooking(config, payload);
			junitassert.assertEquals(response.getStatusCode(), 200);
			createbookingresponse = gson.fromJson(
					response.getBody().asString(), CreateBookingResponse.class);
			Integer bookingid = createbookingresponse.getBookingid();

			// Partial update
			partialupdate = new PartialUpdate(faker.name().firstName(), faker
					.name().lastName());
			String partialUpdatePayload = gson.toJson(partialupdate);
			Response partialUpdateResponse = utils.partialUpdate(config,
					partialUpdatePayload, bookingid, tokenCreated);
			junitassert
					.assertEquals(partialUpdateResponse.getStatusCode(), 200);
			CreateBooking partialUpdateRespReceived = gson.fromJson(
					partialUpdateResponse.getBody().asString(),
					CreateBooking.class);

			junitassert.assertEquals(partialUpdateRespReceived.getFirstname(),
					partialupdate.getFirstname());
			junitassert.assertEquals(partialUpdateRespReceived.getLastname(),
					partialupdate.getLastname());
		} catch (Exception e) {
			System.out.println("Caught Exception in verifyPartialUpdate :" + e);
		}
	}

	@Test
	public void verifyDeleteBooking() {
		try {
			// First Create booking
			bookingdates = new BookingDates("2022-06-01", "2022-06-08");
			createbooking = new CreateBooking(faker.name().firstName(), faker
					.name().lastName(), 20000, true, bookingdates, "Breakfast");
			String payload = gson.toJson(createbooking);
			Response response = utils.createBooking(config, payload);
			junitassert.assertEquals(response.getStatusCode(), 200);
			createbookingresponse = gson.fromJson(
					response.getBody().asString(), CreateBookingResponse.class);
			Integer bookingid = createbookingresponse.getBookingid();

			// Delete Booking by id
			Response deleteReponse = utils.deleteBooking(config, bookingid,
					basicAuth);
			// assert reponse
			junitassert.assertEquals(201, deleteReponse.getStatusCode());

		} catch (Exception e) {
			System.out.println("Caught Exception in verifyDeleteBooking :" + e);
		}
	}

	@Test
	public void verifyGetBookingIds() {
		try {
			// First Create booking
			bookingdates = new BookingDates("2022-06-01", "2022-06-08");
			CreateBooking createbooking1 = new CreateBooking(faker.name()
					.firstName(), faker.name().lastName(), 20000, true,
					bookingdates, "Breakfast");
			String payload = gson.toJson(createbooking1);
			Response response = utils.createBooking(config, payload);
			junitassert.assertEquals(response.getStatusCode(), 200);
			createbookingresponse = gson.fromJson(
					response.getBody().asString(), CreateBookingResponse.class);
			Integer bookingid1 = createbookingresponse.getBookingid();

			CreateBooking createbooking2 = new CreateBooking(faker.name()
					.firstName(), faker.name().lastName(), 20000, true,
					bookingdates, "Breakfast");
			String payload2 = gson.toJson(createbooking1);
			Response response2 = utils.createBooking(config, payload);
			junitassert.assertEquals(response2.getStatusCode(), 200);
			createbookingresponse = gson.fromJson(response2.getBody()
					.asString(), CreateBookingResponse.class);
			Integer bookingid2 = createbookingresponse.getBookingid();

			// retrieve booking ids
			Response bookingidsResp = utils.getBookingIds(config);
			junitassert.assertEquals(bookingidsResp.getStatusCode(), 200);
			List<Integer> bookingids = bookingidsResp.jsonPath().getList(
					"bookingid");

			// Verify list contains our bookings
			junitassert.assertEquals(bookingids.contains(bookingid1), true);
			junitassert.assertEquals(bookingids.contains(bookingid2), true);
		} catch (Exception e) {
			System.out.println("Caught Exception in verifyGetBookingIds :" + e);
		}
	}

	@Test
	public void verifyGetBooking() {
		try {
			// First Create booking
			bookingdates = new BookingDates("2022-06-01", "2022-06-08");
			createbooking = new CreateBooking(faker.name().firstName(), faker
					.name().lastName(), 20000, true, bookingdates, "Breakfast");
			String payload = gson.toJson(createbooking);
			Response response = utils.createBooking(config, payload);
			junitassert.assertEquals(response.getStatusCode(), 200);
			createbookingresponse = gson.fromJson(
					response.getBody().asString(), CreateBookingResponse.class);
			Integer bookingid = createbookingresponse.getBookingid();

			// Get Booking details by id

			Response getBookingResp = utils.getBooking(config, bookingid);
			junitassert.assertEquals(getBookingResp.getStatusCode(), 200);
			CreateBooking respBodyOnGet = gson.fromJson(getBookingResp
					.getBody().asString(), CreateBooking.class);

			// Assert all values
			junitassert.assertEquals(createbooking.getFirstname(),
					respBodyOnGet.getFirstname());
			junitassert.assertEquals(createbooking.getLastname(),
					respBodyOnGet.getLastname());
			junitassert.assertEquals(createbooking.getTotalprice(),
					respBodyOnGet.getTotalprice());
			junitassert.assertEquals(createbooking.getDepositpaid(),
					respBodyOnGet.getDepositpaid());
			junitassert
					.assertEquals(createbooking.getBookingdates().getCheckin(),
							respBodyOnGet.getBookingdates().getCheckin());
			junitassert.assertEquals(createbooking.getBookingdates()
					.getCheckout(), respBodyOnGet.getBookingdates()
					.getCheckout());
			junitassert.assertEquals(createbooking.getAdditionalneeds(),
					respBodyOnGet.getAdditionalneeds());

		} catch (Exception e) {
			System.out.println("Caught Exception in verifyGetBooking :" + e);
		}
	}

	@After
	public void teardown() {

	}

}
