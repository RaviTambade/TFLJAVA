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

@WebServlet("/flowerUpdate")
public class FlowerUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FlowerUpdate() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

         // Extract form parameters
        int productId = Integer.parseInt(request.getParameter("product_id"));
        String productTitle = request.getParameter("product_title");
        String description = request.getParameter("description");
        int unitPrice = Integer.parseInt(request.getParameter("unit_price"));
        int stockAvailable = Integer.parseInt(request.getParameter("stock_available"));

        // Create Product object
        Flower product = new Flower(productId, productTitle, description, unitPrice, stockAvailable);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
       
        // Insert into database
        try {
            FlowerDAO ps = new FlowerDAOJDBC(); 
            ps.update(product);
            out.println("<h3>flower data update!</h3>");
            
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>flower data not update!</h3>"); 
        }
    }

}
