package com.learn.mycart.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.learn.mycart.entites.User;
import com.learn.mycart.helper.FactoryProvider;

@WebServlet("/Register_servlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//doGet(request, response);

		try {

			String userName = request.getParameter("user_name");
			String userEmail = request.getParameter("user_email");
			String userPassword = request.getParameter("user_password");
			String userPhone = request.getParameter("user_phone");
			String userAddress = request.getParameter("user_address");

			
			if (userName.isEmpty()) {
				System.out.println("Name is Blank");
				return;
			}

			// creating user object to store data

			User user = new User(userName, userEmail, userPassword, userPhone, "default.jpg", userAddress,"normal");

			Session hibernateSession = FactoryProvider.getFactory().openSession();
			Transaction tx = hibernateSession.beginTransaction();
			int userId=(int) hibernateSession.save(user);
			
			
			tx.commit();
			hibernateSession.close();

			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("message", "Registration Successfull !! User Id is "+userId);
			response.sendRedirect("register.jsp");
			
		//	System.out.println(userName);
			return;

		//	 hibernateSession.close();
			 /* 
			 * System.out.println("Successfuully Saved");
			 * System.out.println("<br> User id is "+userId);
			 */
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
