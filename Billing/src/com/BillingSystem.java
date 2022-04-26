package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Billing;

@Path("/Billing")
public class BillingSystem {
	Billing BillingObj = new Billing();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readBilling() {
		return BillingObj.readBilling();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertBilling(
	 @FormParam("UserID") String UserID,		
	 @FormParam("UserEmail") String UserEmail,
	 @FormParam("Date") String Date,
	 @FormParam("UnitsConsumed") String UnitsConsumed,
	 @FormParam("PricePerUnit") String PricePerUnit)
	{
	 String output = BillingObj.insertBilling(UserID, UserEmail, Date, UnitsConsumed, PricePerUnit);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBilling(String BillData)
	{
	//Convert the input string to a JSON object
	 JsonObject billingObject = new JsonParser().parse(BillData).getAsJsonObject();
	//Read the values from the JSON object
	 String BillID = billingObject.get("BillID").getAsString();
	 String UserID = billingObject.get("UserID").getAsString();
	 String UserEmail = billingObject.get("UserEmail").getAsString();
	 String Date = billingObject.get("Date").getAsString();
	 String UnitsConsumed = billingObject.get("UnitsConsumed").getAsString();
	 String PricePerUnit = billingObject.get("PricePerUnit").getAsString();
	 String output = BillingObj.updateBilling(BillID, UserID, UserEmail, Date, UnitsConsumed, PricePerUnit);
	return output;
	} 
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBilling(String BillData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(BillData, "", Parser.xmlParser());

	//Read the value from the element <BillID>
	 String BillID = doc.select("BillID").text();
	 String output = BillingObj.deleteBilling(BillID);
	return output;
	}
	
}
