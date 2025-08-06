package com.tfl.dao.jdbc;
import java.util.List;
import com.tfl.models.Flower;

public interface FlowerDAO {
    List<Flower> getAll();
	int insert(Flower p);
	Flower getById(int pid);
	int update(Flower p);
	int remove(int pid);    
}
