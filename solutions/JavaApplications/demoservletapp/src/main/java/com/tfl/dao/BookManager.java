package com.tfl.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.io.*;
import com.tfl.models.Book;

public class BookManager implements IBookManager {

    private List<Book> books;
    private String filePath;

    public BookManager(String filePath){
        this.filePath=filePath;
        books=new ArrayList<>();
        loadBooks();
    }

    public BookManager(){       
    }

    @Override
    public List<Book> loadBooks() {   
        // boolean status=false;
        try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(filePath))){
            this.books=(List<Book>)ois.readObject();
            return this.books;
        }
        catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveBooks() {
        boolean status=false;
        try(ObjectOutputStream ois=new ObjectOutputStream(new FileOutputStream(filePath))){
            ois.writeObject(books);
        }
        catch(IOException  e){
            e.printStackTrace();
        }
    }

    @Override
    public void createBook(Book book) {
        books.add(book);
        saveBooks();
    }

    @Override
    public Book readBook(int id) {
       for(Book book: books){
        if(book.getId()==id){
            return book;
        }
       }
       return null;
    }

    @Override
    public int updateBook(Book updatedBook) {

        for(int i=0;i<books.size();i++){
            Book currentBook=books.get(i);

            if(currentBook.getId()==updatedBook.getId()){
                books.set(i, updatedBook);
                saveBooks();
                return 1;
            }
        }
        return 1;
    }

    @Override

    public void deleteBook(int id) {
        Iterator<Book> iterator=books.iterator();
        while(iterator.hasNext()){
            Book book=iterator.next();
            if(book.getId()==id)
            {
                iterator.remove();
                saveBooks();
                return ;
            }
        }
    }
}