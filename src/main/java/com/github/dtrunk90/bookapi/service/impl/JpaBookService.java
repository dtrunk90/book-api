/* SPDX-License-Identifier: GPL-3.0-or-later */

package com.github.dtrunk90.bookapi.service.impl;

import com.github.dtrunk90.bookapi.model.Book;
import com.github.dtrunk90.bookapi.repository.BookRepository;
import com.github.dtrunk90.bookapi.service.BookService;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * JPA-based implementation of the {@link BookService} interface.
 *
 * <p>This service uses a {@link BookRepository} to interact with the underlying data store.
 * It provides basic CRUD operations for {@link Book} entities.</p>
 *
 * <p>The class is annotated with {@link Service} to indicate that it is a Spring-managed service
 * component. Constructor injection is handled via Lombok's {@link RequiredArgsConstructor}.</p>
 *
 * @author dtrunk90
 */
@Service
@RequiredArgsConstructor
public class JpaBookService implements BookService {
    /**
     * The repository used to access book data from the database.
     */
    private final BookRepository repository;

    /**
     * Retrieves all books from the database.
     *
     * @return a collection of all {@link Book} entities
     */
    @Override
    public Collection<Book> getBooks() {
        return repository.findAll();
    }

    /**
     * Stores the given book in the database.
     * If the book already exists (matches by ID), it will be updated.
     *
     * @param book the {@link Book} to be stored
     * @return the persisted {@link Book} instance
     */
    @Override
    public Book storeBook(Book book) {
        return repository.save(book);
    }
}
