package booksrepository.service;

import booksrepository.model.Book;

import java.util.List;

public interface BookService {
    void save(Book book);
    Book findById(int id);
    List<Book> findAll();
    void update(Book book);
    void deleteById(int id);
}
