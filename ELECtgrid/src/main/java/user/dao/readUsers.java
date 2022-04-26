package user.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.jersy.dbconnect.dbConnection;

public class readUsers {

	public static String readUsers() {

		String output = "";

		try {

		Connection con = dbConnection.connect();


		if (con == null) {
			return "Error while connecting to the database for reading.";

		}

		// Prepare the html table to be displayed
		output = "<table border='1'><tr><th>User's Name</th><th>User's E-mail</th>" + "<th>User's Mobile</th>"
				+ "<th>Active or Not</th> </tr>";

		String query = "select * from user";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		while (rs.next()) {
			String name = rs.getString(1);
			String email = rs.getString(2);
			String mobile = rs.getString(4);
			String status = rs.getString(6);

			// Add into the html table
			output += "<tr><td>" + name + "</td>";
			output += "<td>" + email + "</td>";
			output += "<td>" + mobile + "</td>";
			output += "<td>" + status + "</td>";

	}
		con.close();

		// Complete the html table
		output += "</table>";

	} catch (Exception e) {
		output = "Error while reading the items.";
		System.err.println(e.getMessage());
	}
	return output;

}
}