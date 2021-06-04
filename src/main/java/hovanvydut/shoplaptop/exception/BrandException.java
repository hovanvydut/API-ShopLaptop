package hovanvydut.shoplaptop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author hovanvydut
 * Created on 6/3/21
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BrandException extends RuntimeException{
    public BrandException() {
        super("Brand Exception");
    }

    public BrandException(String message) {
        super(message);
    }

    public BrandException(String message, Throwable cause) {
        super(message, cause);
    }

    public BrandException(Throwable cause) {
        super(cause);
    }
}
