package com.pkglobal.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class jdbcTest
 */
@WebServlet("/jdbcTest")
public class jdbcTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource datasource;
	
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rst = null;
		
		try {
			conn = datasource.getConnection();
			
			String sql = "select * from student";
			
			stmt = conn.createStatement();
			
			rst = stmt.executeQuery(sql);
			
			while(rst.next()) {
				String email = rst.getString("email");
				out.println(email);
			}
			
		}
		
		catch(Exception exp) {
			exp.printStackTrace();
		}
		
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
