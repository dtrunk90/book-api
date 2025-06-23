/* SPDX-License-Identifier: GPL-3.0-or-later */

package com.github.dtrunk90.bookapi.service;

import com.github.dtrunk90.bookapi.model.Book;
import java.util.Collection;

/**
 * Service interface for managing books.
 *
 * <p>This interface defines the operations for retrieving and storing {@link Book} entities.
 * Implementations are responsible for the actual business logic and data access.</p>
 *
 * @author dtrunk90
 */
public interface BookService {
    /**
     * Retrieves all books.
     *
     * @return a collection of all available {@link Book} instances
     */
    Collection<Book> getBooks();

    /**
     * Stores a new book or updates an existing one.
     *
     * @param book the {@link Book} to be stored
     * @return the stored {@link Book} with updated ID
     */
    Book storeBook(Book book);
}
