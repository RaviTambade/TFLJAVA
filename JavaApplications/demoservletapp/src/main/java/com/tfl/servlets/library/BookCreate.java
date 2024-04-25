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


@WebServlet("/createBook")
public class BookCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookCreate() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

         // Extract form parameters
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        int year = Integer.parseInt(request.getParameter("year"));

        // Create Product object
        Book book = new Book(id, title, author, year);
        response.setContentType("text/html");
       
       IBookManager mgr=new BookManager("books.dat");
       mgr.createBook(book);

       response.setContentType("text/html");
       PrintWriter out = response.getWriter();
       out.println("<html><body>");
       out.println("<h1>Book Created Successfully</h1>");
       out.println("<p>ID: " + id + "</p>");
       out.println("<p>Title: " + title + "</p>");
       out.println("<p>Author: " + author + "</p>");
       out.println("<p>Year: " + year + "</p>");
       out.println("</body></html>");
    }

}
