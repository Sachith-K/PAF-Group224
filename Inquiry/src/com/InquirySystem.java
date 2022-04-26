package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.*;

import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Inquiry;

@Path("/Inquiry")
public class InquirySystem {
	Inquiry InquiryObj = new Inquiry();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readInquiry() {
		return InquiryObj.readInquiry();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertInquiry(
	 @FormParam("inqCName") String inqCName,			
	 @FormParam("inqReason") String inqReason,
	 @FormParam("inqAccNo") String inqAccNo,
	 @FormParam("inqDate") String inqDate,
	 @FormParam("inqMobile") String inqMobile)
	{
	 String output = InquiryObj.insertInquiry(inqCName, inqReason, inqAccNo, inqDate, inqMobile);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateInquiry(String InquiryData)
	{
	//Convert the input string to a JSON object
	 JsonObject inq_Object = new JsonParser().parse(InquiryData).getAsJsonObject();
	//Read the values from the JSON object
	 String INQid = inq_Object.get("INQid").getAsString();
	 String inqCName = inq_Object.get("inqCName").getAsString();
	 String inqReason = inq_Object.get("inqReason").getAsString();
	 String inqAccNo = inq_Object.get("inqAccNo").getAsString();
	 String inqDate = inq_Object.get("inqDate").getAsString();
	 String inqMobile = inq_Object.get("inqMobile").getAsString();
	 String output = InquiryObj.updateInquiry(INQid, inqCName, inqReason, inqAccNo, inqDate, inqMobile);
	return output;
	} 
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteInquiry(String InquiryData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(InquiryData, "", Parser.xmlParser());

	//Read the value from the element <ID>
	 String INQid = doc.select("INQid").text();
	 String output = InquiryObj.deleteInquiry(INQid);
	return output;
	}
}
