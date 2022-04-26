package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jersy.dbconnect.dbConnection;

import user.bean.loginBean;

public class loginDao {

	public static String loginDao(loginBean rs) {


		String output = "";
		Connection con = dbConnection.connect();

		try {

			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Name</th><th>E-mail</th>" + "<th>Password</th>"
					+ "<th>User's Mobile</th>" + "<th>OTP</th>" + "<th>Status</th>" + "</tr>";

			PreparedStatement ps1 = con.prepareStatement("select * from user where email=? and password=?");
			ps1.setString(1, rs.getEmail());
			ps1.setString(2, rs.getPassword());
			ResultSet rrs = ps1.executeQuery();

			if (rrs.next()) {


				String name = rrs.getString(1);
				String email = rrs.getString(2);
				String password = rrs.getString(3);
				String mobile = rrs.getString(4);
				String otp = rrs.getString(5);
				String status = rrs.getString(6);

				PreparedStatement ps2 = con.prepareStatement("update user set status = ? where email = ?");
				ps2.setString(1, "online");
				ps2.setNString(2, rs.getEmail());
				int i = ps2.executeUpdate();

				output += "<tr><td>" + name + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + password + "</td>";
				output += "<td>" + mobile + "</td>";
				output += "<td>" + otp + "</td>";
				output += "<td>" + status + "</td>";

				if (i > 0) {

					return output;
				}

			} else {

				return "failed";

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return "fail";

	}

}
