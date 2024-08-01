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
        SessaoVotacao sessaoVotacao = sessaoVotacaoService.buscarSessaoVotacao(id).get();

        if (sessaoVotacao.getAtiva() == false) {
            throw new VotacaoFechadaException("Não foi possivel votar, pois essa votação não está ativa");
        }

        Usuario usuario = usuarioService.buscarUsuario(dto.cpf()).get();

        //Faz uma busca para verificar se esse usuario já realizou algum voto
        List<Voto> ListReturn = repository.findByUsuario(usuario);

        //Se ele já tiver votado, verifica se o voto que esse usuario fez foi na sessão da votação atual
        for (Voto votoReturn : ListReturn) {
            if (votoReturn.getVotacao() == sessaoVotacao) {
                throw new FindException("Esse usuário ja votou nessa pauta");
            }   
        }

        Voto voto = VotoMapper.INSTANCE.dtoToVoto(dto);
        
        voto.setUsuario(usuario);
        voto.setVotacao(sessaoVotacao);
        repository.save(voto);

        sessaoVotacaoService.contabilizarVotoNaSessao(id, dto);

        return VotoMapper.INSTANCE.votoToDto(voto);
            
    }
}