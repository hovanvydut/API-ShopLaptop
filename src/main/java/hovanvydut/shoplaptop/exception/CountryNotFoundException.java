package hovanvydut.shoplaptop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author hovanvydut
 * Created on 6/21/21
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CountryNotFoundException extends ResourceNotFound {
    public CountryNotFoundException() {
        super("Category not found");
    }

    public CountryNotFoundException(String msg) {
        super(msg);
    }

    public CountryNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
