package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Inquiry {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/electripower?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertInquiry(String inqCName, String inqReason, String inqAccNo, String inqDate, String inqMobile) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into inquirysys(`INQid`,`inqCName`,`inqReason`,`inqAccNo`,`inqDate`,`inqMobile`)" + " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, inqCName);
			preparedStmt.setString(3, inqReason);
			preparedStmt.setString(4, inqAccNo);
			preparedStmt.setString(5, inqDate);
			preparedStmt.setString(6, inqMobile);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the inquiry.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readInquiry() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>ID</th><th>Customer Name</th><th>Reason</th><th>Account Number</th><th>Date</th><th>Mobile Number</th></tr>";
			String query = "select * from inquirysys ";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String INQid = Integer.toString(rs.getInt("INQid"));
				String inqCName = rs.getString("inqCName");
				String inqReason = rs.getString("inqReason");
				String inqAccNo = rs.getString("inqAccNo");
				String inqDate = rs.getString("inqDate");
				String inqMobile = rs.getString("inqMobile");

				// Add into the html table
				output += "<tr><td>" + INQid + "</td>";
				output += "<td>" + inqCName + "</td>";
				output += "<td>" + inqReason + "</td>";
				output += "<td>" + inqAccNo + "</td>";
				output += "<td>" + inqDate + "</td>";
				output += "<td>" + inqMobile + "</td>";
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the inquiry.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateInquiry(String INQid, String inqCName, String inqReason, String inqAccNo, String inqDate, String inqMobile) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE inquirysys SET inqCName=?,inqReason=?,inqAccNo=?,inqDate=?,inqMobile=?" + "WHERE INQid=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, inqCName);
			preparedStmt.setString(2, inqReason);
			preparedStmt.setString(3, inqAccNo);
			preparedStmt.setString(4, inqDate);
			preparedStmt.setString(5, inqMobile);
			preparedStmt.setInt(6, Integer.parseInt(INQid));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the inquiry.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteInquiry(String INQid) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from inquirysys where INQid=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(INQid));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the inquiry.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
