package com.tfl.servlets.library;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tfl.models.Book;
import com.tfl.dao.serialization.BookManager;
import com.tfl.dao.serialization.IBookManager;

//import com.tfl.dao.ProductDAOImpl;

@WebServlet("/BookById")
public class BookById extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookById() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("id"));
        IBookManager mgr=new BookManager("books.dat");
        Book theBook=mgr.readBook(bookId);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (theBook != null) {
            out.println("<h2>Product Details</h2>");
            out.println("<p><strong>flowers ID:</strong> " + theBook.getId() + "</p>");
            out.println("<p><strong>Title:</strong> " + theBook.getTitle() + "</p>");
            out.println("<p><strong>Description:</strong> " + theBook.getAuthor() + "</p>");
            out.println("<p><strong>Unit Price:</strong> " + theBook.getYear() + "</p>");
      } else {
            out.println("<p>Product not found!</p>");
        }
    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
  
    }
}
