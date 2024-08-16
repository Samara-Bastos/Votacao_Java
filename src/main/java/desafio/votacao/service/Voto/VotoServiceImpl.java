package desafio.votacao.service.Voto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import desafio.votacao.dto.Voto.RequestVotoDto;
import desafio.votacao.dto.Voto.ResponseVotoDto;
import desafio.votacao.exception.FindException;
import desafio.votacao.exception.VotacaoFechadaException;
import desafio.votacao.mapper.VotoMapper;
import desafio.votacao.model.SessaoVotacao;
import desafio.votacao.model.Usuario;
import desafio.votacao.model.Voto;
import desafio.votacao.repository.VotoRepository;
import desafio.votacao.service.SessaoVotacao.SessaoVotacaoServiceImpl;
import desafio.votacao.service.Usuario.UsuarioServiceImpl;
import java.util.List;

@Service
public class VotoServiceImpl implements VotoService {
    
    @Autowired
    VotoRepository repository;

    @Autowired
    SessaoVotacaoServiceImpl sessaoVotacaoService;

    @Autowired
    UsuarioServiceImpl usuarioService;
    
    
    @Override
    public ResponseVotoDto registrarVoto(Long id, RequestVotoDto dto){
        SessaoVotacao sessaoVotacao = sessaoVotacaoService.buscarSessaoVotacao(id);
        verificaSeSessaoVotacaoEstaAtiva(sessaoVotacao.getAtiva());
        Usuario usuario = usuarioService.buscarUsuarioPorCpf(dto.cpf());
        verificaSeUsuarioVotouNaSessaoAtual(usuario, sessaoVotacao);
        Voto voto = VotoMapper.INSTANCE.dtoToVoto(dto);
        voto.setUsuario(usuario);
        voto.setVotacao(sessaoVotacao);
        repository.save(voto);
        sessaoVotacaoService.contabilizarVotoNaSessao(sessaoVotacao, dto);
        return VotoMapper.INSTANCE.votoToDto(voto);
            
    }

    private void verificaSeSessaoVotacaoEstaAtiva(boolean ativa){
        if (ativa == false) {
            throw new VotacaoFechadaException("Não foi possivel votar, pois essa votação não está ativa");
        }
    }

    private void verificaSeUsuarioVotouNaSessaoAtual(Usuario usuario, SessaoVotacao sessaoVotacao){
        buscaVotosDoUsuario(usuario).forEach(voto -> {
            if (voto.getVotacao() == sessaoVotacao) {
                throw new FindException("Esse usuário ja votou nessa pauta");
            }
        });
    }

    
    private List<Voto> buscaVotosDoUsuario(Usuario usuario){
        return repository.findByUsuario(usuario);
    }
}