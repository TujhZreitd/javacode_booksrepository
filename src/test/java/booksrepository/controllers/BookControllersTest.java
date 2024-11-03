package booksrepository.controllers;

import booksrepository.model.Book;
import booksrepository.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.List;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookControllers.class)
class BookControllersTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book(1, "My book", "Yakushev", LocalDate.of(2024, 11, 2));
    }

    @Test
    void testGetAllBooks() throws Exception {
        when(bookService.findAll()).thenReturn(List.of(book));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(book.getId()))
                .andExpect(jsonPath("$[0].title").value(book.getTitle()))
                .andExpect(jsonPath("$[0].author").value(book.getAuthor()))
                .andExpect(jsonPath("$[0].publicationYear").value(book.getPublicationYear().toString()));
    }

    @Test
    void testGetBook() throws Exception {
        when(bookService.findById(book.getId())).thenReturn(book);

        mockMvc.perform(get("/api/books/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(book.getId()))
                .andExpect(jsonPath("$.title").value(book.getTitle()))
                .andExpect(jsonPath("$.author").value(book.getAuthor()))
                .andExpect(jsonPath("$.publicationYear").value(book.getPublicationYear().toString()));
    }

    @Test
    public void testCreateBook() throws Exception {
        Mockito.doNothing().when(bookService).save(Mockito.any(Book.class));

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(book)))
                .andExpect(status().isOk())
                .andExpect(content().string("Operation complete"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        Mockito.doNothing().when(bookService).save(Mockito.any(Book.class));

        mockMvc.perform(put("/api/books/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(book)))
                .andExpect(status().isOk())
                .andExpect(content().string("Operation complete"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        Mockito.doNothing().when(bookService).deleteById(book.getId());

        mockMvc.perform(delete("/api/books/{id}", book.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("Operation complete"));
    }
}