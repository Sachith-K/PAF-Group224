package com.jersy.services;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import user.bean.UserBean;
import user.bean.changePasswordBean;
import user.bean.deleteUserBean;
import user.bean.loginBean;
import user.dao.UserDao;
import user.dao.changePasswordDao;
import user.dao.deleteUserDao;
import user.dao.loginDao;
import user.dao.readUsers;

@Path("/users")
public class UserServices {

	// register a user
	@Path("/register")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String registerUser(String userdate) throws JsonParseException, JsonMappingException, IOException {

		String str = null;

//		JsonParser jsonParser = new JsonParser();
//		JsonElement jasElement = jsonParser.parse(userdate);
//		if (jasElement.isJsonObject()) {
//
//			JsonObject jsonObject = jasElement.getAsJsonObject();
//
//			UserBean userBean = new UserBean(jsonObject.get("name").getAsString(),
//					jsonObject.get("email").getAsString(), jsonObject.get("pass").getAsString(),
//					jsonObject.get("mobile").getAsString());
//			str = UserDao.registerDao(userBean);
//
//		}

		try {

			ObjectMapper mapper = new ObjectMapper();
			UserBean userBean = mapper.readValue(userdate, UserBean.class);
			str = UserDao.registerDao(userBean);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}


		return str;
	}


	// login a user
	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String loginUser(String s) throws JsonParseException, JsonMappingException, IOException {

		try {

			ObjectMapper objectMapper = new ObjectMapper();
			loginBean loginBean = objectMapper.readValue(s, loginBean.class);

			String result = loginDao.loginDao(loginBean);

			if (result.equals("failed")) {
				return "Username Or Password Incorrect";
			} else {
				return result;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}

	// change password
	@Path("/changepassword")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String changePassword(String s) {

		try {

			ObjectMapper objectMapper = new ObjectMapper();
			changePasswordBean changePasswordBean = objectMapper.readValue(s, changePasswordBean.class);

			if (changePasswordDao.checkEmailPwd(changePasswordBean) == true
					&& changePasswordDao.changePassword(changePasswordBean) == true) {

				return "change password successfully";

			} else {
				return "password change failed";
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "fail";
	}

	// delete user
	@Path("/deleteuser")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteUser(String s) {

		try {

			ObjectMapper objectMapper = new ObjectMapper();
			deleteUserBean deleteUserBean = objectMapper.readValue(s, deleteUserBean.class);

			if (deleteUserDao.deleteUser(deleteUserBean) == true) {

				return "user delete successfully";

			} else {
				return "remove failed";
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "fail";
	}

	// display all the users
	@GET
	@Path("/read")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		readUsers r = new readUsers();

		return r.readUsers();
	}

}
