package com.academy.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.academy.model.UserDetail;

public class UserDetailRowMapper implements RowMapper<UserDetail> {
	
	/*
	 * This method queries the DB for multiple rows and then will convert these rows into an object. 
	 */
	public UserDetail mapRow(ResultSet rs, int row) throws SQLException {
		
		//Object of UserDetail being instantiated
		UserDetail userDetail = new UserDetail();
		//The id of the object UserDetail is being mapped with the id being retrieved from db (Column id)
		userDetail.setId(rs.getInt("id"));
		//The FirstName of the object UserDetail is being mapped with the first name being retrieved from db (Coulumn first_name)
		userDetail.setFirstName(rs.getString("first_name"));
		//The LasttName of the object UserDetail is being mapped with the last name being retrieved from db (Coulumn last_name)
		userDetail.setLastName(rs.getString("last_name"));
		//The email of the object UserDetail is being mapped with the email being retrieved from db (Coulumn email)
		userDetail.setEmail(rs.getString("email"));
		//The dob of the object UserDetail is being mapped with the dob being retrieved from db (Coulumn dob)
		userDetail.setDob(rs.getString("dob"));
		return userDetail;
	}
}