package com.learn.mycart.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.learn.mycart.dao.UserDao;
import com.learn.mycart.entites.User;
import com.learn.mycart.helper.FactoryProvider;

@WebServlet("/Login_servlet")
public class LogniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LogniServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// .....coding area....
		try {
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			// validation

			// authenticate user
			UserDao userDao = new UserDao(FactoryProvider.getFactory());
			User user = userDao.getUserByEmailAndPassword(email, password);
		
			//	System.out.println(user);
			
			
			HttpSession httpSession = request.getSession();
			
			if (user==null) {
			
			//	System.out.println("<h1> Invalid Details </h>");
				httpSession.setAttribute("message", "Invalid details !! Try with another one");
				response.sendRedirect("login.jsp");
				return;
			
			}else {
				
				//System.out.println("<h1> Welcome "+ user.getUserName()+" <h1>");
			//	httpSession.setAttribute("message", "Login Successfull !!");
		
				//login
				httpSession.setAttribute("current-user",user);
				
				if (user.getUserType().equals("admin")) {
					//admin:-admin.jsp
					response.sendRedirect("admin.jsp");
					
				}else if (user.getUserType().equals("normal")) {
					//normal:-normal.jsp
					response.sendRedirect("normal.jsp");
				}else {
					System.out.print("We have not identified user type ");
				}
				
			
			}
				

		} catch (Exception e) {
			e.printStackTrace();
		}

		doGet(request, response);
	}

}
