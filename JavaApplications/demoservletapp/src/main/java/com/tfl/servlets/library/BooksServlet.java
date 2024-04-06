package com.tfl.servlets.library;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import com.tfl.models.Book;
import com.tfl.dao.serialization.BookManager;
import com.tfl.dao.serialization.IBookManager;
 
@WebServlet("/books/*")
public class BooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BooksServlet() {
		super();
	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle GET request here
        
        IBookManager mgr = new BookManager("books.dat");
        List<Book> books = mgr.loadBooks();

        Gson gson = new Gson();
        String json = gson.toJson(books);
        response.setContentType("application/json");

        // Write JSON data to response
        response.getWriter().write(json);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle POST request here
        int id = Integer.parseInt(request.getParameter("id"));
       
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        int year = Integer.parseInt(request.getParameter("year"));
    
        Book book = new Book(id, title, author, year);
        response.setContentType("text/html");

        IBookManager mgr = new BookManager("books.dat");
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
  
        //response.getWriter().println("<h1>Hello from POST request!</h1>");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle PUT request here
        int bookId = Integer.parseInt(request.getParameter("id"));
        String bookTitle = request.getParameter("title");
        String bookAuthor = request.getParameter("author");
        int bookYear = Integer.parseInt(request.getParameter("year"));

        // Create Product object
        Book book = new Book(bookId, bookTitle, bookAuthor, bookYear);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Insert into database
        try {
            IBookManager mgr = new BookManager("books.dat");
            mgr.updateBook(book);
            out.println("<h3>Book updated data!</h3>");
            out.println("<html><body>");
            out.println("<h1>Book Created Successfully</h1>");
            out.println("<p>ID: " + bookId + "</p>");
            out.println("<p>Title: " + bookTitle + "</p>");
            out.println("<p>Author: " + bookAuthor + "</p>");
            out.println("<p>Year: " + bookYear + "</p>");
            out.println("</body></html>");

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Book data not update!</h3>");
        }
       // response.getWriter().println("<h1>Hello from PUT request!</h1>");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         // Handle DELETE request here
         
         int BookId = Integer.parseInt(request.getParameter("id"));
         IBookManager mgr = new BookManager("books.dat");
         mgr.deleteBook(BookId);
 
         response.setContentType("text/html");
         PrintWriter out = response.getWriter();
         out.println("<h3>Book data deleted!</h3>");
      
     }

    protected void doHead(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle HEAD request here
        response.getWriter().println("<h1>Hello from HEAD request!</h1>");

    }

    protected void doOptions(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         // Handle OPTIONS request here
     }

    protected void doTrace(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle TRACE request here
    }

    protected void doPatch(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         // Handle PATCH request here
     }

}
