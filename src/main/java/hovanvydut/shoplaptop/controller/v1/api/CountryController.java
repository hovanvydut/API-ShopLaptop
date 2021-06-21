package hovanvydut.shoplaptop.controller.v1.api;

import hovanvydut.shoplaptop.dto.country.CountryDto;
import hovanvydut.shoplaptop.dto.country.CreateNewCountryDto;
import hovanvydut.shoplaptop.dto.country.UpdateCountryDto;
import hovanvydut.shoplaptop.dto.state.CreateNewStateDto;
import hovanvydut.shoplaptop.dto.state.StateDto;
import hovanvydut.shoplaptop.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

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
        return this.countryService.getAllCountry();
    }

    @GetMapping("/{id}")
    public CountryDto getOne(@PathVariable int id) {
        return this.countryService.findById(id);
    }

    @GetMapping("/{countryId}/states")
    public Set<StateDto> getAllStateOfCountry(@PathVariable("countryId") int countryId) {
        return this.countryService.getAllState(countryId);
    }

    @PostMapping()
    public CountryDto createNewCountry(@Valid @RequestBody CreateNewCountryDto createNewCountryDto) {
        return this.countryService.createNewCountry(createNewCountryDto);
    }

    @PutMapping("/{id}")
    public CountryDto updateCountry(@PathVariable int id, @Valid @RequestBody UpdateCountryDto dto) {
        return this.countryService.updateCountryDto(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable int id) {
        this.countryService.deleteOne(id);
    }

    @PostMapping("/{countryId}/states")
    public void addNewState(@PathVariable int countryId, @RequestBody CreateNewStateDto dto) {
        this.countryService.addNewState(countryId, dto);
    }

    @DeleteMapping("/{countryId}/states/{stateId}")
    public void deleteState(@PathVariable int countryId, @PathVariable int stateId) {
        this.countryService.removeState(countryId, stateId);
    }

}
