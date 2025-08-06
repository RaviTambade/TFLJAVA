package com.tfl.dao.hibernate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import com.tfl.models.Employee;

public class EmployeeDAO {

    private final SessionFactory sessionFactory;

    public EmployeeDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public List<Employee> getAll(){
        try (Session session = sessionFactory.openSession()) {
            return session.createNativeQuery("select * from employees", Employee.class).list();
        }
    }

	public int insert(Employee p){
        try (Session session = sessionFactory.openSession()) {
           
            Transaction transaction = session.beginTransaction();
            String sqlQuery = "INSERT INTO employees (firstname, lastname, email, contact)  VALUES(:firstname, :lastname, :email, :contact)";
            NativeQuery<?> query = session.createNativeQuery(sqlQuery,Employee.class);
            query.setParameter("firstname", p.getFirstName());
            query.setParameter("lastname", p.getLastName());
            query.setParameter("email", p.getEmail());
            query.setParameter("contact", p.getContact());
            query.executeUpdate();
            transaction.commit();
        }
        return 1;
    }
 
	public List<Employee> getById(int pid){
      
        try (Session session = sessionFactory.openSession()) {
            return session.createNativeQuery("select * from employees WHERE id="+pid, Employee.class).list();
        }
    }

	public int update(Employee p){

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String sqlQuery = "UPDATE employees SET firstname = :firstname, lastname = :lastname, email = :email ,contact = :contact WHERE id = :id";
            NativeQuery<?> query = session.createNativeQuery(sqlQuery,Employee.class);
            query.setParameter("firstname", p.getFirstName());
            query.setParameter("lastname", p.getLastName());
            query.setParameter("email", p.getEmail());
            query.setParameter("contact", p.getContact());
            query.setParameter("id", p.getId());
            query.executeUpdate();
            transaction.commit();
        }
        return 1;
    }
 
	public int remove(int pid){
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String sqlQuery = "DELETE FROM employees WHERE id = :id";
            NativeQuery<?> query = session.createNativeQuery(sqlQuery,Employee.class);
            query.setParameter("id", pid);
            query.executeUpdate();
            transaction.commit();
        }
        return 1;
    }   
}