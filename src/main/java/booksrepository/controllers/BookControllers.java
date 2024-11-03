package booksrepository.controllers;

import booksrepository.model.Book;
import booksrepository.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/books")
public class BookControllers {

    private final BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id) {
        return bookService.findById(id);
    }

    @PostMapping
    public ResponseEntity<String> createBook(@RequestBody Book book) {
        bookService.save(book);
        return ResponseEntity.ok("Operation complete");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@RequestBody Book book, @PathVariable int id) {
        book.setId(id);
        bookService.update(book);
        return ResponseEntity.ok("Operation complete");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        bookService.deleteById(id);
        return ResponseEntity.ok("Operation complete");
    }
}
