/* SPDX-License-Identifier: GPL-3.0-or-later */

package com.github.dtrunk90.bookapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Book API Spring Boot application.
 *
 * <p>This class bootstraps the application using {@link SpringApplication#run(Class, String[])}.
 * It is annotated with {@link SpringBootApplication}, which enables autoconfiguration,
 * component scanning, and additional configuration conveniences for Spring Boot applications.</p>
 *
 * <p>To start the application, run the {@code main} method. This will launch an embedded web server
 * and initialize the Spring context.</p>
 *
 * @author dtrunk90
 */
@SpringBootApplication
public class BookApiApplication {
    /**
     * Main method that serves as the application entry point.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(BookApiApplication.class, args);
    }
}
