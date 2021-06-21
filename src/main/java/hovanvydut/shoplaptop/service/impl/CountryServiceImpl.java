package hovanvydut.shoplaptop.service.impl;

import hovanvydut.shoplaptop.dto.country.CountryDto;
import hovanvydut.shoplaptop.dto.country.CountryMapper;
import hovanvydut.shoplaptop.dto.country.CreateNewCountryDto;
import hovanvydut.shoplaptop.dto.country.UpdateCountryDto;
import hovanvydut.shoplaptop.dto.state.CreateNewStateDto;
import hovanvydut.shoplaptop.dto.state.StateDto;
import hovanvydut.shoplaptop.dto.state.StateMapper;
import hovanvydut.shoplaptop.exception.CountryNotFoundException;
import hovanvydut.shoplaptop.model.Country;
import hovanvydut.shoplaptop.model.State;
import hovanvydut.shoplaptop.repository.CountryRepository;
import hovanvydut.shoplaptop.repository.StateRepository;
import hovanvydut.shoplaptop.service.CountryService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author hovanvydut
 * Created on 6/21/21
 */

@Validated
@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final StateRepository stateRepository;

    public CountryServiceImpl(CountryRepository countryRepository, StateRepository stateRepository) {
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository;
    }

    @Override
    public List<CountryDto> getAllCountry() {
        return CountryMapper.MAPPER.from(this.countryRepository.findAll());
    }

    @Override
    public CountryDto findById(int id) {
        Optional<Country> countryOpt = this.countryRepository.findById(id);
        Country country = countryOpt.orElseThrow(() -> new CountryNotFoundException("Country id = " + id + " not found."));

        return CountryMapper.MAPPER.from(country);
    }

    @Override
    public CountryDto createNewCountry(@Valid CreateNewCountryDto createNewCountryDto) {
        Country country = CountryMapper.MAPPER.to(createNewCountryDto);
        Country savedCountry = this.countryRepository.save(country);

        return CountryMapper.MAPPER.from(savedCountry);
    }

    @Override
    public CountryDto updateCountryDto(@Valid UpdateCountryDto updateCountryDto) {
        Country country = CountryMapper.MAPPER.to(updateCountryDto);
        Country savedCountry = this.countryRepository.save(country);

        return CountryMapper.MAPPER.from(savedCountry);
    }

    @Override
    public void deleteOne(int id) {
        Optional<Country> countryOpt = this.countryRepository.findById(id);
        Country country = countryOpt.orElseThrow(() -> new CountryNotFoundException("Country id = " + id + " not found."));

        this.countryRepository.delete(country);
    }

    @Override
    public Set<StateDto> getAllState(int countryId) {
        Optional<Country> countryOpt = this.countryRepository.findById(countryId);
        Country country = countryOpt.orElseThrow(() -> new CountryNotFoundException("Country id = " + countryId + " not found."));

        return StateMapper.MAPPER.from(country.getStates());
    }

    @Override
    public void addNewState(int countryId, CreateNewStateDto createNewStateDto) {
        Optional<Country> countryOpt = this.countryRepository.findById(countryId);
        Country country = countryOpt.orElseThrow(() -> new CountryNotFoundException("Country id = " + countryId + " not found."));

        State state = StateMapper.MAPPER.to(createNewStateDto);
        country.getStates().add(state);

        this.countryRepository.save(country);
    }


    @Override
    public void removeState(int countryId, int stateId) {
        Optional<Country> countryOpt = this.countryRepository.findById(countryId);
        Country country = countryOpt.orElseThrow(() -> new CountryNotFoundException("Country id = " + countryId + " not found."));

        Optional<State> stateOpt = country.getStates().stream().filter(stateElm -> stateElm.getId() == stateId).findFirst();

        stateOpt.ifPresent(stateElm -> {
            country.getStates().remove(stateElm);
        });
    }
}
