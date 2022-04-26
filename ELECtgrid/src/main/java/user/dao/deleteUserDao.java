package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.jersy.dbconnect.dbConnection;

import user.bean.deleteUserBean;

public class deleteUserDao {

	public static boolean deleteUser(deleteUserBean deleteUserBean) {

		Connection con = dbConnection.connect();

		try {

			PreparedStatement ps = con.prepareStatement("DELETE FROM user WHERE email=?");
			ps.setNString(1, deleteUserBean.getEmail());
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
