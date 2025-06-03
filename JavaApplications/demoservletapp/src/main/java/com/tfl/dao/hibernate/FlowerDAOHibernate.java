package com.tfl.dao.hibernate;
import java.util.ArrayList;
import java.util.List;

import com.tfl.dao.jdbc.FlowerDAO;
import com.tfl.models.Flower;
 
public class FlowerDAOHibernate implements FlowerDAO {

	@Override
    public List<Flower> getAll(){
        List<Flower> products=new ArrayList<>();
		
        return products;
    }

	@Override
	public int insert(Flower p){

        return 1;
    }

	@Override
	public Flower getById(int pid){
        Flower product=new Flower(1, "Gerbera","Wedding Flower",10,2000);
        return product;
    }

	@Override
	public int update(Flower p){
        return 1;
    }

    @Override
	public int remove(int pid){
        return 1;
    }   
}