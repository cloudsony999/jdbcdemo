package com.test.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Helper {

	static {
		try {
			Class x = Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("the driver is " + x.getName());
		} catch (ClassNotFoundException e) {
			System.out.println("not loaded " + e);
		}
	}

	public static Connection makeCon() throws SQLException {
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "c##scott", "tiger");
	}

}
