package com.tfl.servlets.library;

import java.io.IOException;
 
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import com.tfl.models.Book;
import com.tfl.dao.BookManager;
import com.tfl.dao.IBookManager;

//import com.tfl.dao.ProductDAOImpl;

@WebServlet("/BookList")
public class BookList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookList() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
                //ProductDAO ps=new ProductDAOImpl();-------hardcoded
                
    
                IBookManager   mgr=new BookManager("books.dat");
                 List<Book> books= mgr.loadBooks();
               

               
                  Gson gson = new Gson();
                String json = gson.toJson(books);
            response.setContentType("application/json");

                // Write JSON data to response
                response.getWriter().write(json);
            
                 // Convert data to JSON
    

                // Set response content type to JSON
            
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
  
    }
}
