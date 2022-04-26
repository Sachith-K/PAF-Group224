package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jersy.dbconnect.dbConnection;

import user.bean.changePasswordBean;

public class changePasswordDao {

	public static boolean checkEmailPwd(changePasswordBean changePasswordBean) {

		Connection con = dbConnection.connect();

		try {

			PreparedStatement ps = con.prepareStatement("select * from user where email=? and password=?");
			ps.setNString(1, changePasswordBean.getEmail());
			ps.setNString(2, changePasswordBean.getPassword());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}

	public static boolean changePassword(changePasswordBean changePasswordBean) {

		Connection con = dbConnection.connect();

		try {

			PreparedStatement ps = con.prepareStatement("update user set password=? where email=?");
			ps.setNString(1, changePasswordBean.getNewpassword());
			ps.setNString(2, changePasswordBean.getEmail());
			int i = ps.executeUpdate();

			if (i > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}

}
