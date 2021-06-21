package hovanvydut.shoplaptop.service;

import hovanvydut.shoplaptop.dto.country.CountryDto;
import hovanvydut.shoplaptop.dto.country.CreateNewCountryDto;
import hovanvydut.shoplaptop.dto.country.UpdateCountryDto;
import hovanvydut.shoplaptop.dto.state.CreateNewStateDto;
import hovanvydut.shoplaptop.dto.state.StateDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * @author hovanvydut
 * Created on 6/21/21
 */

public interface CountryService {
    public List<CountryDto> getAllCountry();

    CountryDto findById(int id);

    CountryDto createNewCountry(@Valid CreateNewCountryDto createNewCountryDto);

    CountryDto updateCountryDto(@Valid UpdateCountryDto updateCountryDto);

    void deleteOne(int id);

    Set<StateDto> getAllState(int countryId);

    void addNewState(int countryId, CreateNewStateDto createNewStateDto);

    void removeState(int countryId, int stateId);
}
