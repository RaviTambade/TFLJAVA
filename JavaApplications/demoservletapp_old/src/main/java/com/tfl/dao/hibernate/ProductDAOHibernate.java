package com.tfl.dao.hibernate;
import java.util.ArrayList;
import java.util.List;

import com.tfl.dao.jdbc.ProductDAO;
import com.tfl.models.Product;
 

public class ProductDAOHibernate implements ProductDAO {

	@Override
    public List<Product> getAll(){
        List<Product> products=new ArrayList<>();
		
        return products;
    }

	@Override
	public int insert(Product p){

        return 1;
    }

	@Override
	public Product getById(int pid){
        Product product=new Product(1, "Gerbera","Wedding Flower",10,2000);
        return product;
    }

	@Override
	public int update(Product p){
        return 1;
    }

    @Override
	public int remove(int pid){
        return 1;
    }   
}