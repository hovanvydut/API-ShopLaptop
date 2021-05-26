package hovanvydut.shoplaptop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author hovanvydut
 * Created on 5/25/21
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(String msg) {
        super(msg);
    }

    public ResourceNotFound(String msg, Throwable cause) {
        super(msg, cause);
    }

}
