/* SPDX-License-Identifier: GPL-3.0-or-later */

package com.github.dtrunk90.bookapi.exception;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global exception handler for the Book API application.
 *
 * <p>This class extends {@link ResponseEntityExceptionHandler} to customize how certain
 * exceptions are handled, especially validation-related errors.</p>
 *
 * <p>It is annotated with {@link RestControllerAdvice}, making it applicable to all REST controllers.</p>
 *
 * <p>The overridden method {@link #handleMethodArgumentNotValid} converts validation exceptions
 * into a structured {@link ValidationProblemDetail} response, which includes field-level
 * and global validation messages. It uses {@link ErrorResponse.Builder} to construct the
 * {@link ProblemDetail} with i18n support via {@link LocaleContextHolder}.</p>
 *
 * @author dtrunk90
 */
@RestControllerAdvice
public class BookApiResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * Handles {@link MethodArgumentNotValidException}, which occurs when validation on
     * an argument annotated with {@code @Valid} fails.
     *
     * <p>Builds a {@link ValidationProblemDetail} from the validation errors and returns it
     * as a structured {@link ProblemDetail} response entity.</p>
     *
     * @param ex      the exception containing validation error details
     * @param headers the HTTP headers to be written to the response
     * @param status  the HTTP status to be returned
     * @param request the current web request
     * @return a {@link ResponseEntity} with a {@link ProblemDetail} body representing the validation failure
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        ErrorResponse.Builder builder = ErrorResponse.builder(ex,
                ValidationProblemDetail.forStatusAndBindingResult(status, ex.getBindingResult()));
        ProblemDetail body = builder.build().updateAndGetBody(getMessageSource(), LocaleContextHolder.getLocale());
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
