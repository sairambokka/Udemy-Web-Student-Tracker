package com.pkglobal.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StudentDbUtil studentDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	
	

	@Override
	public void init() throws ServletException {
		
		super.init();
		
		try {
			studentDbUtil = new StudentDbUtil(dataSource);
		}
		catch (Exception exp) {
			throw new ServletException(exp);
		}
	}




	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		try {
			
			// read the "command" parameter
			String theCommand = request.getParameter("command");
						
			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
				}
						
			// route to the appropriate method
			switch (theCommand) {
			
			case "LIST":
				listStudents(request, response);
				break;
				
			case "ADD":
				addStudents(request, response);
				break;
			
			case "LOAD":
				loadStudent(request, response);
				break;
				
			case "UPDATE":
				updateStudent(request, response);
				break;
				
			case "DELETE":
				deleteStudent(request, response);
				break;
				
			default:
				listStudents(request, response);
			}
		}
		catch (Exception exp) {
			throw new ServletException(exp);
		}
	}




	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String studentId = request.getParameter("studentId");
		
		studentDbUtil.deleteStudent(studentId);
		
		listStudents(request, response);
		
	}




	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int studentId = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		Student theStudent = new Student(studentId, firstName, lastName, email);
		
		studentDbUtil.updateStudent(theStudent);
		
		listStudents(request, response);
		
	}




	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String studentId = request.getParameter("studentId");
		
		Student theStudent = studentDbUtil.getStudent(studentId);
		
		request.setAttribute("THE_STUDENT", theStudent);
		
		RequestDispatcher req = request.getRequestDispatcher("/update-student-form.jsp");
		
		req.forward(request, response);
	}




	private void addStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		Student std = new Student(firstName, lastName, email);
		
		studentDbUtil.addStudent(std);
		
		listStudents(request, response);
		
	}




	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		List<Student> students = studentDbUtil.getStudents();
		
		request.setAttribute("STUDENT_LIST", students);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		
		dispatcher.forward(request, response);
		
	}

}
