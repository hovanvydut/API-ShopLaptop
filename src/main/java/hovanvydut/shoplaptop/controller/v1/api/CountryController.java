package hovanvydut.shoplaptop.controller.v1.api;

import hovanvydut.shoplaptop.dto.country.CountryDto;
import hovanvydut.shoplaptop.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hovanvydut
 * Created on 6/21/21
 */

@Validated
@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping()
    public List<CountryDto> getAllCountries() {
        System.out.println("aaaaaxxxxxxxxxxx");
        return this.countryService.getAllCountry();
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
