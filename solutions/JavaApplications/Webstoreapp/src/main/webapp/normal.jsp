<%@page import="com.learn.mycart.entites.User"%>
<%

     User user = (User) session.getAttribute("current-user");
     if(user==null){
    	 
    	 session.setAttribute("message", "You are not logged in !! Login First ");
    	 response.sendRedirect("login.jsp");
    	 return;
    	
     }else{
    	 if(user.getUserType().equals("admin")) {
    		 session.setAttribute("message", "You are not Normal User !! Do not access this page");
        	 response.sendRedirect("login.jsp");
    		 return;
    	 }
     }

%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
     <meta charset="UTF-8">
     <title>Normal User</title>
</head>
<body>
<h1>This is normal user panel</h1>
</body>
</html>