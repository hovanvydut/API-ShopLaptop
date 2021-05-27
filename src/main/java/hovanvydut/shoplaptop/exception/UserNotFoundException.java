package hovanvydut.shoplaptop.exception;

/**
 * @author hovanvydut
 * Created on 5/26/21
 */

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
