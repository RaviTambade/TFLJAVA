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
import com.tfl.dao.IBookManager;;

//import com.tfl.dao.ProductDAOImpl;

@WebServlet("/BookUpdate")
public class BookUpdate extends HttpServlet {
       private static final long serialVersionUID = 1L;

       public BookUpdate(){
              super();
        }
     

       protected void doGet(HttpServletRequest request, HttpServletResponse response){

        }
    

       protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

         // Extract form parameters
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
               IBookManager mgr=new BookManager("books.dat"); 
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
    }

}
