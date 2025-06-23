/* SPDX-License-Identifier: GPL-3.0-or-later */

package com.github.dtrunk90.bookapi.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dtrunk90.bookapi.BookApiApplication;
import com.github.dtrunk90.bookapi.model.Book;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Integration tests for {@link BookController}.
 *
 * <p>These tests verify the behavior of the Book API's REST endpoints using {@link MockMvc}.
 * Each test uses a clean Spring application context with an in-memory H2 database.</p>
 *
 * <p>The {@link org.springframework.test.annotation.DirtiesContext} annotation ensures
 * that the application context is reset after each test method, so that tests do not
 * interfere with one another via shared state (e.g., persisted data).</p>
 *
 * <ul>
 *   <li>Tests the creation of a book via HTTP POST</li>
 *   <li>Tests the retrieval of books via HTTP GET</li>
 * </ul>
 *
 * <p>Uses {@link SpringBootTest} to load the full application context and {@link AutoConfigureMockMvc}
 * to test the web layer without launching a real HTTP server.</p>
 *
 * @author dtrunk90
 */
@AutoConfigureMockMvc
@SpringBootTest(classes = BookApiApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookControllerTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    Book book = Book.builder()
            .title("New Book")
            .author("John Doe")
            .build();

    /**
     * Test that a book can be successfully created via POST.
     *
     * <p>Verifies that the response has status 201, correct content type,
     * and returns the created book with an auto-generated ID.</p>
     */
    @Test
    @SneakyThrows
    public void givenValidBook_whenPostBook_thenStatus201AndBookReturned() {
        mvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andDo(print())
                .andExpectAll(
                        status().isCreated(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.id", is(1)),
                        jsonPath("$.title", is(book.getTitle())),
                        jsonPath("$.author", is(book.getAuthor())));
    }

    /**
     * Test that books can be retrieved after at least one book is created.
     *
     * <p>First creates a book using POST, then retrieves the list via GET
     * and verifies that the list contains the created book.</p>
     */
    @Test
    @SneakyThrows
    public void givenCreatedBook_whenGetBooks_thenStatus200AndBookListReturned() {
        // Ensure at least one book exists
        mvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated());

        mvc.perform(get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$", hasSize(1)),
                        jsonPath("$[0].id", is(1)),
                        jsonPath("$[0].title", is(book.getTitle())),
                        jsonPath("$[0].author", is(book.getAuthor())));
    }
}
