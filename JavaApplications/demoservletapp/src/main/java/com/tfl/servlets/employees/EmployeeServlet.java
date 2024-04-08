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

        // Set response content type to JSON
        response.setContentType("application/json");

        // Write JSON data to response
        response.getWriter().write(json);

    }
}
