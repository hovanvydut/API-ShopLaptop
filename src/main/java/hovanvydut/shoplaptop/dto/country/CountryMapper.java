package hovanvydut.shoplaptop.dto.country;

import hovanvydut.shoplaptop.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author hovanvydut
 * Created on 6/21/21
 */

@Mapper
public interface CountryMapper {

    public static CountryMapper MAPPER = Mappers.getMapper(CountryMapper.class);

    CountryDto from (Country country);

    List<CountryDto> from (List<Country> countries);

    List<CountryDto> from (Iterable<Country> countries);

    Country to(CreateNewCountryDto createNewCountryDto);

    Country to(UpdateCountryDto updateCountryDto);
}
