package hovanvydut.shoplaptop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author hovanvydut
 * Created on 5/25/21
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoleNotFoundException extends ResourceNotFound{

    public RoleNotFoundException() {
        super("Role not found");
    }

    public RoleNotFoundException(String msg) {
        super(msg);
    }

    public RoleNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
