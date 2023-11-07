package com.rest.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {
    // concept: BookController -> BookService -> BookRepository

    private final BookRepository bookRepository;

    public ResponseEntity<Book> addBook(Book book) {
        var persistedBook = bookRepository.save(book);
        return new ResponseEntity<>(persistedBook, HttpStatus.CREATED);
    }

    public ResponseEntity<Book> getBook(Long id) {
        var persistedBook = bookRepository.findById(id);
        if (persistedBook.isPresent()) {
            return new ResponseEntity<>(persistedBook.orElse(null), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // To simplify, the update method will be identical to the add method
    public ResponseEntity<Book> updateBook(Book book) {
        var persistedBook = bookRepository.findById(book.getId());
        if (persistedBook.isPresent()) {
            var updatedBook = bookRepository.save(book);
            return new ResponseEntity<>(updatedBook, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Void> deleteBook(Long id) {
        var persistedBook = bookRepository.findById(id);
        if (persistedBook.isPresent()) {
            bookRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
