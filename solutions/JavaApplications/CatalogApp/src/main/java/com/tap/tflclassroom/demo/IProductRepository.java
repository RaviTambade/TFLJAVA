package com.tap.tflclassroom.demo;

import java.util.List;

public interface IProductRepository {

    //abstract methods
    //These methods do not have implementation logic
    public boolean  insert();
    public boolean  update();
    public boolean  remove();
    public List<Product> getAll();
    public Product getById(int id);
}
