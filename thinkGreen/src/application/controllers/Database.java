package application.controllers;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

	public static Connection connectDb() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/think_green",
					"root",
					""
			);
			
			return connect;
			
		} catch (Exception exp) {
			exp.printStackTrace();
			return null;
		}
	}
}
