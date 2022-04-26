package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import com.jersy.dbconnect.dbConnection;

import user.bean.UserBean;

public class UserDao {

	public static String registerDao(UserBean rs) {

		int otp = new Random().nextInt(345);

		Connection con = dbConnection.connect();

		try {

			PreparedStatement ps1 = con.prepareStatement("select email from new_table where email=?");
			ps1.setString(1, rs.getEmail());
			ResultSet rrs = ps1.executeQuery();

			if (rrs.next()) {
				return "Already Exist";
			} else {

				
				PreparedStatement ps = con.prepareStatement("insert into new_table values(?,?,?,?)");

				ps.setString(1, rs.getName());
				ps.setString(2, rs.getEmail());
				ps.setString(3, rs.getPass());
				ps.setString(4, rs.getMobile());
				

				int i = ps.executeUpdate();

				if (i > 0) {
					return "User Registered Successfully";
				} else {
					return "Registered Fail";
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return "fail";

	}

}
