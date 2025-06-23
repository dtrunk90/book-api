/* SPDX-License-Identifier: GPL-3.0-or-later */

package com.github.dtrunk90.bookapi.controller;

import com.github.dtrunk90.bookapi.exception.BookNotFoundException;
import com.github.dtrunk90.bookapi.exception.ValidationProblemDetail;
import com.github.dtrunk90.bookapi.model.Book;
import com.github.dtrunk90.bookapi.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.Collection;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing {@link Book} resources.
 *
 * <p>Provides endpoints for retrieving and creating books via HTTP.
 * Uses {@link BookService} to delegate business logic and data persistence.</p>
 *
 * <p>Annotated with {@link RestController} and {@link RequestMapping} to define the base path.
 * Includes OpenAPI annotations for API documentation (e.g. Swagger UI).</p>
 *
 * @author dtrunk90
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {
    /**
     * Service layer used to manage book-related operations.
     */
    private final BookService service;

    /**
     * Retrieves all available books.
     *
     * <p>If no books are found, a {@link BookNotFoundException} is thrown.</p>
     *
     * @return a collection of {@link Book} entities
     * @throws BookNotFoundException if no books exist in the system
     */
    @GetMapping
    @Operation(summary = "Get all books")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Found books",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Book.class)),
                            examples = @ExampleObject(
                                    "[{ \"id\": 1, \"title\": \"New Book\", \"author\": \"John Doe\" }]")) }),
            @ApiResponse(responseCode = "404", description = "Did not find any books", content = { @Content() }) })
    public Collection<Book> getBooks() {
        return Optional.of(service.getBooks())
                .filter(books -> !books.isEmpty())
                .orElseThrow(BookNotFoundException::new);
    }

    /**
     * Creates a new {@link Book} entity.
     *
     * <p>The input is validated, and a 400 Bad Request is returned if validation fails.</p>
     *
     * @param book the {@link Book} to be created (must be valid)
     * @return the created {@link Book} with generated ID
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new book")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Book created successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class),
                            examples = @ExampleObject("{ \"title\": \"New Book\", \"author\": \"John Doe\" }")) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationProblemDetail.class),
                            examples = @ExampleObject("{ \"type\": \"about:blank\", \"title\": \"Bad Request\", \"status\": 400, \"instance\": \"/api/books/\", \"errors\": [\"Some global error\"], \"fieldErrors\": [{ \"field\": \"title\", \"message\": \"Some field error\" }]}")) }) })
    public Book createBook(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Book to create", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Book.class),
                    examples = @ExampleObject("{ \"title\": \"New Book\", \"author\": \"John Doe\" }")))
                               @RequestBody @Valid final Book book) {
        return service.storeBook(book);
    }
}
