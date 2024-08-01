package desafio.votacao.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import desafio.votacao.dto.Voto.RequestVotoDto;
import desafio.votacao.dto.Voto.ResponseVotoDto;
import desafio.votacao.model.Voto;



@Mapper(componentModel = "spring")
public interface VotoMapper {
    VotoMapper INSTANCE = Mappers.getMapper(VotoMapper.class);

    Voto dtoToVoto(RequestVotoDto dto);

    ResponseVotoDto votoToDto(Voto voto);
}
