package com.tfl.dao.serialization;
import java.util.List;
import com.tfl.models.Book;

public interface IBookManager {
    List<Book> loadBooks();
 	void saveBooks();
	void createBook(Book book); 
	Book readBook(int id);
	int updateBook(Book updatedBook);  
	void deleteBook(int id);  
}


    
 