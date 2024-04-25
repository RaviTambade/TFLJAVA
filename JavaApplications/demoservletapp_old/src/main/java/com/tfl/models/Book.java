package com.tfl.models;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String title;
    private String author;
    private int year;

    public Book(int id, String title, String author, int year){
        this.id=id;
        this.title=title;
        this.author=author;
        this.year=year;
    }

    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return this.id;
    }

    public void setYear(int year){
        this.year=year;
    }
    public int getYear(){
        return this.year;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return this.author;
    }

}
