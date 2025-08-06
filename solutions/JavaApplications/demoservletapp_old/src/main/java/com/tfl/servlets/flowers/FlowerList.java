package com.tfl.servlets.flowers;

import java.io.IOException;
 
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import com.tfl.models.Flower;
import com.tfl.dao.jdbc.FlowerDAO;
import com.tfl.dao.jdbc.FlowerDAOJDBC;

//import com.tfl.dao.ProductDAOImpl;

@WebServlet("/flowerList")
public class FlowerList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FlowerList() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
                //ProductDAO ps=new ProductDAOImpl();-------hardcoded
                FlowerDAO ps=new FlowerDAOJDBC(); //------data from mysql database ecommerce 
                List<Flower> products=ps.getAll();
    
               /*  response.setContentType("text/html");
                PrintWriter out=response.getWriter();
    
                out.println("<table border='2'><tr><th>Product id</th><th>Title</th><th>Description</th><th>Unit Price</th><th>Quantity</th><th>action</th></tr>");
                for(Product p:products) {
                    out.println("<tr><td>"+p.getId()+"</td><td>"+p.getTitle()+"</td><td>"+p.getDescrition()+"</td><td>"+p.getUnitPrice()+"</td><td>"+p.getQuantity()+"</td>");
                    out.println("<td><a href='delete?pid="+p.getId()+"'>delete</a>/<a href='edit?pid="+p.getId()+"'>edit</a><td></tr>");
                }
                out.println("</table>");
                out.println("<a href='addproduct.html'>Add new Flower</a>");

                */

                 // Convert data to JSON
                Gson gson = new Gson();
                String json = gson.toJson(products);

                // Set response content type to JSON
                response.setContentType("application/json");

                // Write JSON data to response
                response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
  
    }
}
