package com.transflower.demo;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {
      
    @GetMapping("/books")  
    private List<Book> getAllBooks()   
    {  
        List<Book> books=new ArrayList<>();
        books.add(new Book(1, "Let us C","Yashwant Kanetkar",10));
        books.add(new Book(2, "Rose","Valentine Flower",19));
        books.add(new Book(4, "Jasmine","Smelling Flower",2));
        return  books;
    }  
    //creating a get mapping that retrieves the detail of a specific book  
    @GetMapping("/books/{bookid}")  
    private Book getBooks(@PathVariable("bookid") int bookid)   
    {  
        return new Book(1, "Let us C","Yashwant Kanetkar",10);

            //return booksService.getBooksById(bookid);  
    }  
    //creating a delete mapping that deletes a specified book  
    @DeleteMapping("/books/{bookid}")  
    private void deleteBook(@PathVariable("bookid") int bookid)   
    {  
        //booksService.delete(bookid);  
    }  
    //creating post mapping that post the book detail in the database  
    @PostMapping("/books")  
    private int saveBook(@RequestBody Book book)   
    {  
        //booksService.saveOrUpdate(book);  
        //return book.getBookid();  
        return 1;
    }  
    //creating put mapping that updates the book detail   
    @PutMapping("/books")  
    private Book update(@RequestBody Book book)   
    {  
        //booksService.saveOrUpdate(book);  
        //return book;  
        return new Book(1, "Let us C","Yashwant Kanetkar",10);
    }  
}
