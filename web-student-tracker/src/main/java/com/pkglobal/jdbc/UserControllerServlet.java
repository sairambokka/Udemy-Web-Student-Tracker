package com.pkglobal.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class UserControllerServlet
 */
@WebServlet("/UserControllerServlet")
public class UserControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private UserDbUtil userDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	
	
	@Override
	public void init() throws ServletException {
		
		super.init();
		
		try {
			userDbUtil = new UserDbUtil(dataSource);
		}
		catch (Exception exp) {
			throw new ServletException(exp);
		}
	}

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean check = true;
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		User user1 = new User(userName, password);
		
		request.setAttribute("user1", user1);
		
		try {
			check = checkUserData(request,response);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		if(check) {
			HttpSession session = request.getSession();
			session.setAttribute("UserName", userName);
			request.getRequestDispatcher("/StudentControllerServlet").forward(request, response);
			
		}
		
		else {
			response.setContentType("text/html");
			request.getRequestDispatcher("login.html").include(request, response);
			out.println("Invalid credentials !");
		}
	}


	private boolean checkUserData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<User> userList = userDbUtil.getUserData();
		
		User user1 = (User) request.getAttribute("user1");
		
		for(User user : userList) {
			if(user1.getUserName().equals(user.getUserName()) && user1.getPassword().equals(user1.getPassword()) ) {
				
				return true;
			}
			
		}
		
		return false;
	}



}
