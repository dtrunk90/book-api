/* SPDX-License-Identifier: GPL-3.0-or-later */

package com.github.dtrunk90.bookapi.repository;

import com.github.dtrunk90.bookapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Book} entities.
 *
 * <p>Extends {@link JpaRepository} to provide standard CRUD operations
 * and query methods for {@link Book} entities identified by a {@link Long} ID.</p>
 *
 * <p>Spring Data JPA automatically provides the implementation at runtime.</p>
 *
 * @author dtrunk90
 */
public interface BookRepository extends JpaRepository<Book, Long> {
}
