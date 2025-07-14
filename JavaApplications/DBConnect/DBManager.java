import java.sql.*;

public class DBManager {
    public static void main(String[] args) throws Exception {

        
        String URL = "jdbc:mysql://localhost:3306/assessmentdb";
        String USERNAME = "root";
        String PASSWORD = "password";
        Connection connection = null;

        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        System.out.println("Connection established successfully...");
        System.out.println();
      
        Statement smt = connection.createStatement();
        ResultSet rs = smt.executeQuery("select * from questionbank");

        ResultSetMetaData rmd = rs.getMetaData();
        int columncount = rmd.getColumnCount();
        System.out.println("Questions ");
        for (int i = 1; i <= columncount; i++) {
            System.out.print(rmd.getColumnName(i) + "\t");
        }
        System.out.println("\n*");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "  " + rs.getInt(2) + "  " + rs.getString(3)+"  "+rs.getString(4));
            System.out.println();
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Questions display successfully....");
        connection.close();
    }
}