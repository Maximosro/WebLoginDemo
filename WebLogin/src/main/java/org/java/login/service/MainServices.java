package org.java.login.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class MainServices {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public MainServices() {
		super();
	}

	public boolean validarUser(String user, String pass) throws SQLException {
		DataSource datasource = jdbcTemplate.getDataSource();
		Connection con = datasource.getConnection();
		PreparedStatement st = con.prepareStatement("Select USER,PASS FROM PUBLIC.DBUSER WHERE USER=? AND PASS=?");
		st.setString(1, user);
		st.setString(2, pass);

		ResultSet result = st.executeQuery();
		if (result.next()) {
			return true;
		}

		return false;
	}

}
