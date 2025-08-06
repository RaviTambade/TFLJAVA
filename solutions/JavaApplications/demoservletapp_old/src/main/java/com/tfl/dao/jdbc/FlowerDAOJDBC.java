package com.tfl.dao.jdbc;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tfl.models.Flower;
import com.tfl.utils.ConnectionUtils;


public class FlowerDAOJDBC implements FlowerDAO {

	@Override
    public List<Flower> getAll(){
        List<Flower> products=new ArrayList<>();
		try (Connection connection = ConnectionUtils.getOracleConnection()) {
			String query = "SELECT * FROM product";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				 Flower product= new Flower(resultSet.getInt("product_id"), // id  
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
	public int insert(Flower p){

        try (Connection connection = ConnectionUtils.getOracleConnection()) {
			String query = "INSERT INTO product (product_id, product_title, description, unit_price, stock_available) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, p.getId());
			statement.setString(2, p.getTitle());
			statement.setString(3, p.getDescrition());
			statement.setInt(4, p.getUnitPrice());
			statement.setInt(5, p.getQuantity());
			statement.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	
    }

	@Override
	public Flower getById(int pid) {
		Flower product = null;
		try (Connection connection = ConnectionUtils.getOracleConnection()) {
			String query = "SELECT * FROM product WHERE product_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, pid);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				product = new Flower(resultSet.getInt("product_id"), // id
						resultSet.getString("product_title"), // title
						resultSet.getString("description"), // description
						resultSet.getInt("unit_price"), // unit price
						resultSet.getInt("stock_available")); // stock available
			}

		} catch (SQLException e) {
			e.printStackTrace(); // Handle properly in real-time app
		}
		return product;
	}

	@Override
	public int update(Flower p){
       	try (Connection connection = ConnectionUtils.getOracleConnection()) {
			String query = "UPDATE  product SET product_title=?, description=?, unit_price=?, stock_available=? WHERE product_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(5, p.getId());
			statement.setString(1, p.getTitle());
			statement.setString(2, p.getDescrition());
			statement.setInt(3, p.getUnitPrice());
			statement.setInt(4, p.getQuantity());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
    }

    @Override
	public int remove(int pid){
        try (Connection connection = ConnectionUtils.getOracleConnection()) {
			String query = "DELETE FROM product where product_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, pid);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
    }   
}