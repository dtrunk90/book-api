/* SPDX-License-Identifier: GPL-3.0-or-later */

package com.github.dtrunk90.bookapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Entity representing a book.
 *
 * <p>This class is mapped to a database table via JPA and represents the basic information
 * of a book, including its title and author.</p>
 *
 * <p>Validation constraints ensure that both {@code title} and {@code author} are not blank.
 * The {@code id} field is auto-generated by the persistence provider.</p>
 *
 * <p>Lombok annotations are used to reduce boilerplate code:
 * <ul>
 *   <li>{@link Data} generates getters, setters, equals, hashCode, and toString</li>
 *   <li>{@link Builder} enables the builder pattern</li>
 *   <li>{@link NoArgsConstructor} and {@link AllArgsConstructor} provide constructors</li>
 *   <li>{@link FieldDefaults} sets all field access levels to private by default</li>
 * </ul>
 * </p>
 *
 * @author dtrunk90
 */
@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {
    /**
     * The unique identifier for the book.
     * Auto-generated by the persistence provider.
     */
    @Id
    @GeneratedValue
    long id;

    /**
     * The title of the book.
     * Must not be blank.
     */
    @Column
    @NotBlank
    String title;

    /**
     * The author of the book.
     * Must not be blank.
     */
    @Column
    @NotBlank
    String author;
}
