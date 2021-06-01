package hovanvydut.shoplaptop.controller.v1.request.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @author hovanvydut
 * Created on 6/1/21
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCategoryRequest implements Serializable {

    private static final long serialVersionUID = 74458L;

    @Min(1)
    private String name;

    private boolean enabled;

    private int parentId;

    @Override
    public String toString() {
        return "CreateCategoryRequest{" +
                "name='" + name + '\'' +
                ", enabled=" + enabled +
                ", parentId=" + parentId +
                '}';
    }
}
