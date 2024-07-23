package desafio.votacao.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import desafio.votacao.dto.Usuario.RequestUsuarioDto;
import desafio.votacao.dto.Usuario.ResponseUsuarioDto;
import desafio.votacao.model.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    Usuario dtoToUsuario(RequestUsuarioDto dto);

    ResponseUsuarioDto usuarioToDto(Usuario usuario);
}
