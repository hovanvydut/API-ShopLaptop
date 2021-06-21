package hovanvydut.shoplaptop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author hovanvydut
 * Created on 6/3/21
 */

@ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
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
