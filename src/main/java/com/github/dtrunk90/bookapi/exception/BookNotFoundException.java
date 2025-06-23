/* SPDX-License-Identifier: GPL-3.0-or-later */

package com.github.dtrunk90.bookapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when no books are found in the system.
 *
 * <p>This exception is mapped to an HTTP 404 Not Found response
 * via the {@link ResponseStatus} annotation.</p>
 *
 * <p>It is typically thrown by the controller when a book retrieval operation
 * yields an empty result.</p>
 *
 * @author dtrunk90
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException {
}
