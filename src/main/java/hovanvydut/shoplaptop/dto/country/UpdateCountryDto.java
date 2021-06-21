package hovanvydut.shoplaptop.dto.country;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author hovanvydut
 * Created on 6/21/21
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class UpdateCountryDto {

    @NotBlank
    @Pattern(regexp = "[a-zA-Z]", message = "Must be included at least character a-z, A-Z")
    private String name;

    @NotBlank
    @Pattern(regexp = "[a-zA-Z]", message = "Must be included at least character a-z, A-Z")
    private String code;

}
