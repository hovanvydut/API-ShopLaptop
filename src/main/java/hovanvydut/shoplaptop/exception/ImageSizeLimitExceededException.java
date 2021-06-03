package hovanvydut.shoplaptop.exception;

/**
 * @author hovanvydut
 * Created on 6/3/21
 */

public class ImageSizeLimitExceededException extends SizeException{
    public ImageSizeLimitExceededException() {
        super();
    }

    public ImageSizeLimitExceededException(String message) {
        super(message);
    }

    public ImageSizeLimitExceededException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageSizeLimitExceededException(Throwable cause) {
        super(cause);
    }
}
