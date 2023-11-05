package com.rest.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addBook() throws Exception {
        var book = "{\"id\":1, \"title\":\"test-book1\", \"author\":\"test-author\"}";

        mockMvc.perform(post("/v1/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(book))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("test-book1"))
                .andExpect(jsonPath("$.author").value("test-author"));
    }

    @Test
    void getBooks_notFoud() throws Exception {
        mockMvc.perform(get("/v1/book/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getBooks() throws Exception {
        // create
        var book = "{\"id\":1, \"title\":\"test-book1\", \"author\":\"test-author\"}";
        mockMvc.perform(post("/v1/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(book))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("test-book1"))
                .andExpect(jsonPath("$.author").value("test-author"));

        // check: is created?
        mockMvc.perform(get("/v1/book/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("test-book1"))
                .andExpect(jsonPath("$.author").value("test-author"));

    }

    @Test
    void updateBook() throws Exception {
        // create
        var book = "{\"id\":1, \"title\":\"test-book1\", \"author\":\"test-author\"}";
        mockMvc.perform(post("/v1/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(book))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("test-book1"))
                .andExpect(jsonPath("$.author").value("test-author"));

        // update
        var bookUpdate = "{\"id\":1, \"title\":\"test-book1-new-version\", \"author\":\"test-author\"}";
        mockMvc.perform(post("/v1/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookUpdate))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("test-book1-new-version"))
                .andExpect(jsonPath("$.author").value("test-author"));
    }

    @Test
    void deleteBook() throws Exception {
        // create
        var book = "{\"id\":1, \"title\":\"test-book1\", \"author\":\"test-author\"}";
        mockMvc.perform(post("/v1/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(book))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("test-book1"))
                .andExpect(jsonPath("$.author").value("test-author"));

        // delete
        mockMvc.perform(delete("/v1/book/1"))
                .andExpect(status().isNoContent());

    }
}
























