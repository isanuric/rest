package com.rest.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/book")
public class BookController {

    // concept: BookController -> BookService -> BookRepository

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBooks(@PathVariable Long id) {
        return bookService.getBook(id);
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }

}
