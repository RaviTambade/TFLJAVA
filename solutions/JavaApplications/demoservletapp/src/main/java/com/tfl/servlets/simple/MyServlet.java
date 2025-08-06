package com.tfl.servlets.simple;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/tfl")
public class MyServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle GET request here
        response.getWriter().println("<h1>Hello from GET request!</h1>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle POST request here
        response.getWriter().println("<h1>Hello from POST request!</h1>");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle PUT request here
        response.getWriter().println("<h1>Hello from PUT request!</h1>");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         // Handle DELETE request here
         response.getWriter().println("<h1>Hello from DELETE request!</h1>");

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