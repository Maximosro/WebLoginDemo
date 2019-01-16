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

	/**
	 * Conexion jdbc al h2 - Esta utilizara la configuracion especificada en el
	 * aplication.properties
	 */
	@Autowired
	JdbcTemplate jdbcTemplate;

	/**
	 * Constructor
	 */
	public MainServices() {
		super();
	}

	/**
	 * Dado un usuario y una contraseña valida si el usuario es correcto.
	 * 
	 * @param user
	 * @param pass
	 * @return
	 * @throws SQLException
	 */
	public boolean validarUser(String user, String pass) throws SQLException {
		DataSource datasource = jdbcTemplate.getDataSource();
		try (Connection con = datasource.getConnection()) {
			try (PreparedStatement st = con
					.prepareStatement("Select USER,PASS FROM PUBLIC.DBUSER WHERE USER=? AND PASS=?")) {
				st.setString(1, user);
				st.setString(2, pass);
				try (ResultSet result = st.executeQuery()) {
					if (result.next()) {
						return true;
					}
				}
			}
		}

		return false;
	}

}
