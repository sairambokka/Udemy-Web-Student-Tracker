package com.pkglobal.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class UserDbUtil {
	
	private DataSource dataSource;
	
	public UserDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<User> getUserData() throws Exception {
		List<User> users = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRst = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "select * from users";
			
			myStmt = myConn.createStatement();
			
			myRst = myStmt.executeQuery(sql);
			
			while(myRst.next()) {
				
				String userName = myRst.getString("username");
				String password = myRst.getString("password");
				
				User user = new User(userName, password);
				
				users.add(user);	
					}
			return users;
			}
		
		finally {
			close(myConn, myStmt, myRst);
			
		}
	}
	
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();   // doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}

	
	

