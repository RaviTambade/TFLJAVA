package com.tfl.servlets.flowers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tfl.dao.jdbc.FlowerDAO;
import com.tfl.dao.jdbc.FlowerDAOJDBC;

//import com.tfl.dao.ProductDAOImpl;

@WebServlet("/flowerDelete")
public class FlowerDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FlowerDelete() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("product_id"));
        FlowerDAO ps = new FlowerDAOJDBC();
       ps.remove(productId);

    
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h3>flower data deleted!</h3>");
        
    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
  
    }
}
