package com.tap.tflclassroom.demo;
import java.sql.*;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        
        String URL = "jdbc:mysql://localhost:3306/tflmentoringdb";
        String USERNAME = "root";
        String PASSWORD = "password";
        Connection connection = null;
 
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        System.out.println("Connection established successfully...");
 
        System.out.println();
        System.out.println();
        System.out.println();
        Statement smt = connection.createStatement();
        ResultSet rs = smt.executeQuery("select * from topics");
        ResultSetMetaData rmd = rs.getMetaData();
        int columncount = rmd.getColumnCount();
        System.out.println("Topics");
        for (int i = 1; i <= columncount; i++) {
            System.out.print(rmd.getColumnName(i) + "\t");
        }
        System.out.println("\n*");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3)+"  "+rs.getInt(4));
            System.out.println();
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("topics display successfully....");
        connection.close();



    }
}



//mvn --version   
//mvn archetype:generate -DgroupId=com.tap.tflclassroom.demo -DartifactId=tflclassroom-demo -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
//mvn clean install
//mvn exec:java -Dexec.mainClass="com.tap.tflclassroom.demo
