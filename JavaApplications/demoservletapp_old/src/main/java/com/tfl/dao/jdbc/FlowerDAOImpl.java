package com.tfl.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import com.tfl.models.Flower;

public class FlowerDAOImpl implements FlowerDAO {

	@Override
    public List<Flower> getAll(){
        List<Flower> flowers=new ArrayList<>();
        // For demonstration purposes, adding some sample products
        flowers.add(new Flower(1, "Gerbera","Wedding Flower",10,2000));
        flowers.add(new Flower(2, "Rose","Valentine Flower",19,6000));
        flowers.add(new Flower(3, "Lotus","Worship Flower",25,8000));
        flowers.add(new Flower(4, "Jasmine","Smelling Flower",2,54600));

        //database connectivity
        

        return flowers;
    }

	@Override
	public int insert(Flower p){
        return 1;
    }

	@Override
	public Flower getById(int pid){
        Flower flower=new Flower(1, "Gerbera","Wedding Flower",10,2000);
        return flower;

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