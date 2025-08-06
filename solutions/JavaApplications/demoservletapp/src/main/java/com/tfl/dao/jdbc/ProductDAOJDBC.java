package com.tfl.dao.jdbc;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tfl.models.Product;
import com.tfl.utils.ConnectionUtils;

public class ProductDAOJDBC implements ProductDAO {

	@Override
    public List<Product> getAll(){
        List<Product> products=new ArrayList<>();
		try (Connection connection = ConnectionUtils.getMysqlConnection()) {
			String query = "SELECT * FROM products";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				 Product product= new Product(resultSet.getInt("product_id"), // id  
						resultSet.getString("product_title"), // titile
						resultSet.getString("description"), // description
						resultSet.getInt("unit_price"), // unitprice
						resultSet.getInt("stock_available"));// quantity
				// new coin -- add list of coins
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Handle properly in real time app
		}
         return products;
    }

    @Override
	public Product getById(int id){
        Product product=null;
		try (Connection connection = ConnectionUtils.getMysqlConnection()) {
			String query = "SELECT * FROM products WHERE product_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				 product= new Product(resultSet.getInt("product_id"), // id  
						resultSet.getString("product_title"), // titile
						resultSet.getString("description"), // description
						resultSet.getInt("unit_price"), // unitprice
						resultSet.getInt("stock_available"));// quantity
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Handle properly in real time app
		}
        return product;
    }


	@Override
	public int insert(Product p){
        try{
            Connection connection = ConnectionUtils.getMysqlConnection();
            String sql = "INSERT INTO products (name, email) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, p.getTitle());
            statement.setString(2, p.getDescrition());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                  System.out.println("User created successfully.");
            }    
        }
        catch(SQLException ee){

        }
      return 1;
    }


	@Override
	public int update(Product p){
        try{
            Connection connection = ConnectionUtils.getMysqlConnection();
            String sql = "Update products  SET (name, email) WHERE ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, p.getTitle());
            statement.setString(2, p.getDescrition());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                  System.out.println("User created successfully.");
            }    
        }
        catch(SQLException ee){

        }
      return 1;
    }

    @Override
	public int remove(int id){
    int result=0;
     try{
            Connection connection = ConnectionUtils.getMysqlConnection();
            String query = "DELETE FROM users WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                result=1;
                            System.out.println("Product deleted successfully.");
            }
        }
     catch(SQLException ee){

     }     
    return result;
    }   
}