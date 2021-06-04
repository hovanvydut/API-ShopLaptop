package hovanvydut.shoplaptop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author hovanvydut
 * Created on 6/3/21
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BrandNotFoundException extends ResourceNotFound{

    public BrandNotFoundException() {
        super("Brand not found");
    }

    public BrandNotFoundException(String msg) {
        super(msg);
    }

    public BrandNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
