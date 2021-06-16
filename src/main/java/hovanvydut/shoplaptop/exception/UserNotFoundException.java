package hovanvydut.shoplaptop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author hovanvydut
 * Created on 5/26/21
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends ResourceNotFound{

    public UserNotFoundException() {
        super("User not found");
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }

    public UserNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
