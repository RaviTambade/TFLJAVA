package com.tfl.servlets.library;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.tfl.models.Book;
import com.tfl.dao.BookManager;
import com.tfl.dao.IBookManager;

//import com.tfl.dao.ProductDAOImpl;

@WebServlet("/BookDelete")
public class BookDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookDelete() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int BookId = Integer.parseInt(request.getParameter("id"));
       IBookManager mgr=new BookManager("books.dat");
       mgr.deleteBook(BookId);

    
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h3>Book data deleted!</h3>");
        
    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
  
    }
}
