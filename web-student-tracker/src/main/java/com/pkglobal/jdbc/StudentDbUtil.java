package com.pkglobal.jdbc;

import java.util.*;
import java.sql.*;

import javax.sql.DataSource;

public class StudentDbUtil {

	private DataSource dataSource;

	public StudentDbUtil(DataSource thedatasource) {
		dataSource = thedatasource;
	}
	
	public List<Student> getStudents() throws Exception {
		List<Student> students = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRst = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "select * from student";
			
			myStmt = myConn.createStatement();
			
			myRst = myStmt.executeQuery(sql);
			
			while(myRst.next()) {
				
				int id = myRst.getInt("id");
				String firstName = myRst.getString("first_name");
				String lastName = myRst.getString("last_name");
				String email = myRst.getString("email");
				
				Student std = new Student(id,firstName, lastName, email);
				
				students.add(std);	
					}
			return students;
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

	public void addStudent(Student theStudent) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into student "
					   + "(first_name, last_name, email) "
					   + "values (?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public Student getStudent(String studentId) throws Exception {
		Student theStudent = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRst = null;
		int StudentId;
		
		try {
			
			StudentId = Integer.parseInt(studentId);
			
			myConn = dataSource.getConnection();
			
			String sql = "select * from student where id=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, StudentId);
			
			myRst = myStmt.executeQuery();
			
			if(myRst.next()) {
				String firstName = myRst.getString("first_name");
				String lastName = myRst.getString("last_name");
				String email = myRst.getString("email");
				
				theStudent = new Student(StudentId, firstName, lastName, email);
			}
			
			else {
				throw new Exception("Could not find student id : " + StudentId);
			}
			
			return theStudent;
		}
		
		finally {
			
			close(myConn, myStmt, myRst);
		}
	}

	public void updateStudent(Student theStudent) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "update student "
					+ "set first_name=?, last_name=?, email=? "
					+ "where id=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			myStmt.setInt(4, theStudent.getId());
			
			myStmt.execute();
		}
		
		finally {
			close(myConn, myStmt, null);
		}
		
	}

	public void deleteStudent(String studentId) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			int StudentId = Integer.parseInt(studentId);
			
			myConn = dataSource.getConnection();
			
			String sql = "delete from student where id=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, StudentId);
			
			myStmt.execute();
		}
		
		finally {
			close(myConn, myStmt, null);
		}
		
	}
}
