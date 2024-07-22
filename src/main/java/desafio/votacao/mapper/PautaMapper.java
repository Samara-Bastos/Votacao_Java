package desafio.votacao.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import desafio.votacao.dto.RequestPautaDto;
import desafio.votacao.model.Pauta;


@Mapper(componentModel = "spring")
public interface PautaMapper {
    PautaMapper INSTANCE = Mappers.getMapper(PautaMapper.class);

    @Mapping(target = "ativaSessao", ignore = true)
    @Mapping(target = "tempoSessao", ignore = true)
    Pauta dtoToPauta(RequestPautaDto request);
}
