package desafio.votacao.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import desafio.votacao.dto.RequestVotoDto;
import desafio.votacao.model.Voto;



@Mapper(componentModel = "spring")
public interface VotoMapper {
    VotoMapper INSTANCE = Mappers.getMapper(VotoMapper.class);

    Voto dtoToVoto(RequestVotoDto dto);
}
