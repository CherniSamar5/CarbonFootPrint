package application.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class DbController {
	private Connection connect;
	private String sql;
	private  PreparedStatement statement;
	private ResultSet result;
	List<User> users = new ArrayList<>();
	
	public DbController() {
		this.connect = Database.connectDb();
	}
	
	public List<User> getUsers() {
		sql = "SELECT * FROM users";
		try {
			statement = connect.prepareStatement(sql);
			result = statement.executeQuery();
			
			while(result.next()) {
				users.add(new User(
						result.getLong(1),
						result.getString(2),
						result.getString(3),
						result.getString(4)

				));
			}
			System.out.println(users);
			
			return users;
		}catch(Exception exp) {
			exp.printStackTrace();
			return null;
		}
	}
	
	public User getUserByUsername(String username) {
		sql = "SELECT * FROM users WHERE username = ?";
		
		try {
			statement = connect.prepareStatement(sql);
			
			statement.setString(1, username);

			result = statement.executeQuery();
			if (result.next()) {
				User user = new User(
						result.getLong(1),
						result.getString(2),
						result.getString(3),
						result.getString(4)
					);
				return user;
			}
			
			return null;
		} catch(Exception exp) {
			exp.printStackTrace();
			return null;
		}
	}
	
	public void addUser(User user) {
		sql = "INSERT INTO users(username, email, password) VALUES(?, ?, ?)";
		
		try {
			statement = connect.prepareStatement(sql);
			
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getMail());
			statement.setString(3, user.getPwd());

			statement.executeUpdate();
		} catch(Exception exp) {
			exp.printStackTrace();
		}
	}
	
	public void updateUser(Long userId, User user) {
		sql = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
		try {
			statement = connect.prepareStatement(sql);
			
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getMail());
			statement.setString(3, user.getPwd());
			statement.setLong(4, userId);
			
			statement.executeUpdate();

		}catch(Exception exp) {
			exp.printStackTrace();
		}
	}
	
	public void deleteUser(Long userId) {
		sql = "DELETE FROM users WHERE id = ?";
		
		try {
			statement = connect.prepareStatement(sql);
			
			statement.setLong(1, userId);
			
			statement.executeUpdate();
		} catch(Exception exp) {
			exp.printStackTrace();
		}
	}

}
