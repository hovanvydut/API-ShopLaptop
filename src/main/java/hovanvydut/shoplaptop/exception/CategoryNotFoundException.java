package hovanvydut.shoplaptop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author hovanvydut
 * Created on 6/1/21
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException() {
        super("Category not found");
    }

    public CategoryNotFoundException(String msg) {
        super(msg);
    }

    public CategoryNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
