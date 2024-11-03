package booksrepository.repository;

import booksrepository.model.Book;

import java.util.List;

public interface BookRepository {
    void save(Book book);
    Book findById(int id);
    List<Book> findAll();
    void update(Book book);
    void deleteById(int id);
}
