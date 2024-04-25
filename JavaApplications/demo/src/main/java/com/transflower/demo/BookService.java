package com.transflower.demo;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    public List<Book> getAllBooks(){
        List<Book> books=new ArrayList<>();
        // For demonstration purposes, adding some sample products
        books.add(new Book(1, "Let us C","Yashwant Kanetkar",10));
        books.add(new Book(2, "Rose","Valentine Flower",19));
        books.add(new Book(4, "Jasmine","Smelling Flower",2));
        return books;
    }


    public Book getBooksById(int bookId){
        return new Book(1, "Let us C","Yashwant Kanetkar",10);
    }

    public void delete(int  bookId){
        
    }

    public void saveOrUpdate(Book book){

    }
}
