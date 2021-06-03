package hovanvydut.shoplaptop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author hovanvydut
 * Created on 6/3/21
 */

@ResponseStatus(value = HttpStatus.PAYLOAD_TOO_LARGE, reason = "File size is greater than limit")
public class SizeException extends RuntimeException{
    public SizeException() {
        super();
    }

    public SizeException(String message) {
        super(message);
    }

    public SizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SizeException(Throwable cause) {
        super(cause);
    }
}
