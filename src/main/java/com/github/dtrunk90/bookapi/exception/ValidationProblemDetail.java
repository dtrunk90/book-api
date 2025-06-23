/* SPDX-License-Identifier: GPL-3.0-or-later */

package com.github.dtrunk90.bookapi.exception;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import lombok.EqualsAndHashCode;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

/**
 * Extended {@link ProblemDetail} that represents validation errors in a structured format.
 *
 * <p>This class is intended to be used as a standardized error response for failed validations,
 * typically in {@code @Valid} annotated request bodies. It includes both global and field-specific
 * validation errors.</p>
 *
 * <p>Spring's {@link BindingResult} is used to extract and populate these errors.</p>
 *
 * <p>By default, this problem detail is initialized with HTTP status 400 (Bad Request).</p>
 *
 * <p>This class is serializable and includes defensive copying for mutable collections to
 * prevent accidental exposure of internal state.</p>
 *
 * @see ProblemDetail
 * @see BindingResult
 *
 * @author dtrunk90
 */
@EqualsAndHashCode(callSuper = true)
public class ValidationProblemDetail extends ProblemDetail implements Serializable {
    @Serial
    private static final long serialVersionUID = -8742447345383298909L;

    /**
     * A collection of global validation error messages (not bound to specific fields).
     */
    private Collection<String> errors;

    /**
     * A collection of field-specific validation errors.
     */
    private Collection<FieldError> fieldErrors;

    /**
     * Record representing a single field-specific validation error.
     *
     * @param field   the name of the field that failed validation
     * @param message the associated validation error message
     */
    public record FieldError(String field, String message) implements Serializable {
        @Serial
        private static final long serialVersionUID = 2885325823765016787L;
    }

    /**
     * Default constructor that initializes the problem detail with {@code 400 Bad Request}.
     */
    public ValidationProblemDetail() {
        super(HttpStatus.BAD_REQUEST.value());
    }

    /**
     * Returns a copy of the global validation error messages.
     *
     * @return an unmodifiable copy of the global errors, or {@code null} if none
     */
    public Collection<String> getErrors() {
        return errors == null ? null : List.copyOf(errors);
    }

    /**
     * Sets the global validation error messages using a defensive copy.
     *
     * @param errors the collection of global errors to set; may be {@code null}
     */
    public void setErrors(Collection<String> errors) {
        this.errors = errors == null ? null : List.copyOf(errors);
    }

    /**
     * Returns a copy of the field-specific validation errors.
     *
     * @return an unmodifiable copy of the field errors, or {@code null} if none
     */
    public Collection<FieldError> getFieldErrors() {
        return fieldErrors == null ? null : List.copyOf(fieldErrors);
    }

    /**
     * Sets the field-specific validation errors using a defensive copy.
     *
     * @param fieldErrors the collection of field errors to set; may be {@code null}
     */
    public void setFieldErrors(Collection<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors == null ? null : List.copyOf(fieldErrors);
    }

    /**
     * Factory method that creates a {@link ValidationProblemDetail} from a given {@link BindingResult}
     * and HTTP status.
     *
     * @param status         the HTTP status to assign to the problem detail
     * @param bindingResult  the {@link BindingResult} containing validation errors
     * @return a populated {@link ValidationProblemDetail} instance
     * @throws IllegalArgumentException if {@code status} is {@code null}
     */
    public static ProblemDetail forStatusAndBindingResult(HttpStatusCode status, BindingResult bindingResult) {
        Assert.notNull(status, "HttpStatusCode is required");
        ValidationProblemDetail problemDetail = new ValidationProblemDetail();

        if (bindingResult.hasGlobalErrors()) {
            problemDetail.setErrors(bindingResult.getGlobalErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList());
        }

        if (bindingResult.hasFieldErrors()) {
            problemDetail.setFieldErrors(bindingResult.getFieldErrors().stream()
                    .map(fieldError -> new FieldError(fieldError.getField(), fieldError.getDefaultMessage()))
                    .toList());
        }

        return problemDetail;
    }
}
