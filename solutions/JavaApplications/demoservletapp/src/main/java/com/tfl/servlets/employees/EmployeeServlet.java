package com.tfl.servlets.employees;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tfl.dao.hibernate.EmployeeDAO;
import com.tfl.models.Employee;
 
 
import org.hibernate.SessionFactory;
 
import org.hibernate.cfg.Configuration;
import com.google.gson.Gson;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
    private final EmployeeDAO employeeDAO;

    public EmployeeServlet() {

        SessionFactory sf=new Configuration().configure().buildSessionFactory();
        this.employeeDAO = new EmployeeDAO(sf);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Employee> employees = employeeDAO.getAll();
        Gson gson = new Gson();
        String json = gson.toJson(employees);
        response.setContentType("application/json");
        response.getWriter().write(json);

    }

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle POST request here
       
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String contact = request.getParameter("contact");
        
        
    
        Employee emp = new Employee(firstName, lastName,email,contact);
        response.setContentType("text/html");
            employeeDAO.insert(emp);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Empolyee Created Successfully</h1>");
      
        out.println("<p>First Name: " + emp.getFirstName() + "</p>");
        out.println("<p>Last Name: " + emp.getLastName() + "</p>");
        out.println("<p>Email: " + emp.getEmail() + "</p>");
        out.println("<p>Contact No: " + emp.getContact() + "</p>");
    
        out.println("</body></html>");

    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String contact = request.getParameter("contact");
 

        Employee emp = new Employee(id, firstName, lastName, email, contact);
        employeeDAO.update(emp);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>Employee data updated Successfully</h1>");
        out.println("<p>Employee ID: " + emp.getId() + "</p>");
        out.println("<p>First Name: " + emp.getFirstName() + "</p>");
        out.println("<p>Last Name: " + emp.getLastName() + "</p>");
        out.println("<p>Email: " + emp.getEmail() + "</p>");
        out.println("<p>Contact No: " + emp.getContact() + "</p>");
        out.println("</body></html>");

    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         // Handle DELETE request here
         
         int Id = Integer.parseInt(request.getParameter("id"));
         employeeDAO.remove(Id);

 
         response.setContentType("text/html");
         PrintWriter out = response.getWriter();
         out.println("<h3>employee data deleted!</h3>");
      
     }

     protected void doOptions(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         // Handle OPTIONS request here
         int Id = Integer.parseInt(request.getParameter("id"));
         List<Employee> employees = employeeDAO.getById(Id);
         PrintWriter out = response.getWriter();
         out.println("<h3>employee data display!</h3>");
         Gson gson = new Gson();
         String json = gson.toJson(employees);
         response.setContentType("application/json");
         response.getWriter().write(json);
     }

    // protected void doHead(HttpServletRequest request, HttpServletResponse response)
    //         throws ServletException, IOException {
           
        
    // }


    // protected void doTrace(HttpServletRequest request, HttpServletResponse response)
    //         throws ServletException, IOException {
    //     // Handle TRACE request here
     
    // }

    // protected void doPatch(HttpServletRequest request, HttpServletResponse response)
    //          throws ServletException, IOException {
    //      // Handle PATCH request here  
         

    //  }


}
