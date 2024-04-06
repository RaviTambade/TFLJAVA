package com.tfl.servlets.flowers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.tfl.models.Flower;
import com.tfl.dao.jdbc.FlowerDAO;
import com.tfl.dao.jdbc.FlowerDAOJDBC;

//import com.tfl.dao.ProductDAOImpl;

@WebServlet("/flowerById")
public class FlowerById extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FlowerById() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("product_id"));
        FlowerDAO ps = new FlowerDAOJDBC();
        Flower product = ps.getById(productId);

    

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (product != null) {
            out.println("<h2>Product Details</h2>");
            out.println("<p><strong>flowers ID:</strong> " + product.getId() + "</p>");
            out.println("<p><strong>Title:</strong> " + product.getTitle() + "</p>");
            out.println("<p><strong>Description:</strong> " + product.getDescrition() + "</p>");
            out.println("<p><strong>Unit Price:</strong> " + product.getUnitPrice() + "</p>");
            out.println("<p><strong>Stock Available:</strong> " + product.getQuantity() + "</p>");
        } else {
            out.println("<p>Product not found!</p>");
        }
    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
  
    }
}
