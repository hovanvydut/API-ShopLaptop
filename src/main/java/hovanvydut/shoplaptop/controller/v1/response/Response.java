package hovanvydut.shoplaptop.controller.v1.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {

    private HttpStatus status;
    private T payload;
    private Object errors;
    private Object metadata;

    public static <T> Response<T> OK() {
        Response<T> response = new Response<>();
        response.setStatus(HttpStatus.ACCEPTED);
        return response;
    }

    public static <T> Response<T> NOT_FOUND() {
        Response<T> response = new Response<>();
        response.setStatus(HttpStatus.NOT_FOUND);
        return response;
    }

}
