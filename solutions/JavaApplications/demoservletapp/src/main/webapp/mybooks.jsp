<!DOCTYPE html>
<html>
    <head>
        <title>Transflower Books Collection </title>
    </head>
    <body>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
        <%@ page import="java.util.List" %>
        <%@ page import="java.util.ArrayList" %>
        <%@ page import="java.util.Iterator" %>
        <%@ page import="com.tfl.models.Book" %>
        <%@ page import="com.tfl.dao.serialization.IBookManager" %>
        <%@ page import="com.tfl.dao.serialization.BookManager" %>
        <%
            IBookManager mgr = new BookManager("books.dat");
            List<Book> books = mgr.loadBooks(); 
        %>

        <h2>Transflower Library</h2>
        <hr/>
        <table border="1"> 
            <thead> 
                <tr> 
                    <th>#</th> 
                    <th>Title</th> 
                    <th>Author</th> 
                    <th>Year</th> 
                </tr> 
            </thead> 
            <tbody> 
                <%for (int i = 0; i < books.size(); i++) {
                    Book currentBook=books.get(i);
                    %>
                    <tr>
                        <td><%= currentBook.getId() %></td>
                        <td><%= currentBook.getTitle() %></td>
                        <td><%= currentBook.getAuthor() %></td>
                        <td><%= currentBook.getYear() %></td>
                    </tr>
                    <%}%>
            </tbody> 
        </table> 
    </body>
</html>