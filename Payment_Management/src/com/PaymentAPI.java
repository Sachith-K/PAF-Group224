package com;

import model.Payment;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payment")
public class PaymentAPI {
	Payment PayObj = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment() {
		return PayObj.readPayment();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(
			@FormParam("CustomerName") String CustomerName,
			@FormParam("AccountNO") String AccountNO,
			@FormParam("Date") String Date,
			@FormParam("PaymentAmount") String PaymentAmount) {
		String output = PayObj.insertPayment(CustomerName, AccountNO, Date, PaymentAmount);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updatePayment(String payData) {
		// Convert the input string to a JSON object
		JsonObject PaymentObject = new JsonParser().parse(payData).getAsJsonObject();

		// Read the values from the JSON object
		String PaymentID = PaymentObject.get("PaymentID").getAsString();
		String CustomerName = PaymentObject.get("CustomerName").getAsString();
		String AccountNO = PaymentObject.get("AccountNO").getAsString();
		String Date = PaymentObject.get("Date").getAsString();
		String PaymentAmount = PaymentObject.get("PaymentAmount").getAsString();
		
		String output = PayObj.updatePayment(PaymentID, CustomerName, AccountNO, Date, PaymentAmount);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String payData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(payData, "", Parser.xmlParser());

		// Read the value from the element
		String PaymentID = doc.select("PaymentID").text();
		String output = PayObj.deletePayment(PaymentID);
		return output;
	}
}
