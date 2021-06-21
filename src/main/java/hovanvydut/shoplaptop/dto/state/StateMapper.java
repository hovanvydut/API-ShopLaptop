package hovanvydut.shoplaptop.dto.state;

import hovanvydut.shoplaptop.model.State;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

/**
 * @author hovanvydut
 * Created on 6/21/21
 */

@Mapper
public interface StateMapper {
    public static StateMapper MAPPER = Mappers.getMapper(StateMapper.class);

    StateDto from (State state);

    Set<StateDto> from (Set<State> states);

    State to (CreateNewStateDto createNewStateDto);

}
