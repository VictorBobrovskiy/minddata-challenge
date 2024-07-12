package es.minddata.challenge.error;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            MethodArgumentTypeMismatchException.class,
            ConstraintViolationException.class,
            IllegalStateException.class,
            IllegalArgumentException.class,
            MissingServletRequestParameterException.class,
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(final Exception e) {

        log.error("----- Exception " + e.getClass() + " caused BAD_REQUEST status" + getStackTrace(e));

        return new ErrorResponse(
                "Invalid parameters for creating a starship",
                e.getMessage(),
                LocalDateTime.now().format(FORMATTER)
        );
    }

    @ExceptionHandler(StarShipNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(final StarShipNotFoundException e) {

        log.error("----- Error " + e.getClass() + " caused NOT_FOUND status" + getStackTrace(e));

        return new ErrorResponse(
                "Not Found",
                e.getMessage(),
                LocalDateTime.now().format(FORMATTER)
        );
    }

    @ExceptionHandler({
            StarShipExistsException.class,
            DataIntegrityViolationException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleConflicts(final Exception e) {

        log.error("----- Error " + e.getClass() + " caused CONFLICT status");

        return new ErrorResponse(
                "Already Exists",
                e.getMessage(),
                LocalDateTime.now().format(FORMATTER)
        );
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleError(final Throwable e) {

        log.error("----- Error " + e.getClass() + " caused INTERNAL_SERVER_ERROR status" + getStackTrace(e));

        return new ErrorResponse(
                "Something went really wrong",
                e.getMessage(),
                LocalDateTime.now().format(FORMATTER)
        );
    }


    private String getStackTrace(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

}