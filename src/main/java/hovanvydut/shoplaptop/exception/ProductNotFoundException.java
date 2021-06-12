package hovanvydut.shoplaptop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author hovanvydut
 * Created on 6/10/21
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends ResourceNotFound{

    public ProductNotFoundException() {
        super("Product not found");
    }

    public ProductNotFoundException(String msg) {
        super(msg);
    }

    public ProductNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
