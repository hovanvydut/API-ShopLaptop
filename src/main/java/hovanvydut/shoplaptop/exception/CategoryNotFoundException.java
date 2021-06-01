package hovanvydut.shoplaptop.exception;

/**
 * @author hovanvydut
 * Created on 6/1/21
 */

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
