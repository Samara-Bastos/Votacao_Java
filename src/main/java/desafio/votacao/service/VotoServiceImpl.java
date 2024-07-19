package desafio.votacao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import desafio.votacao.dto.RequestVotoDto;
import desafio.votacao.enums.TipoVoto;
import desafio.votacao.exception.VotacaoFechadaException;
import desafio.votacao.mapper.VotoMapper;
import desafio.votacao.model.SessaoVotacao;
import desafio.votacao.model.Usuario;
import desafio.votacao.model.Voto;
import desafio.votacao.repository.VotoRepository;

@Service
public class VotoServiceImpl {
    
    @Autowired
    VotoRepository repository;

    @Autowired
    SessaoVotacaoServiceImpl sessaoVotacaoService;

    @Autowired
    UsuarioServiceImpl usuarioService;

    private List<Voto> votos = new ArrayList<>();
    
    
    public void registrarVoto(Long id, RequestVotoDto dto){
        SessaoVotacao sessaoVotacao = sessaoVotacaoService.buscarSessaoVotacao(id).get();

        if (sessaoVotacao.getAtiva() == true) {
            Usuario usuario = usuarioService.buscarUsuario(dto.cpf()).get();
            Voto voto = VotoMapper.INSTANCE.dtoToVoto(dto);
            
            votos.add(voto);

            voto.setUsuario(usuario);
            voto.setVotacao(sessaoVotacao);

            repository.save(voto);


            //Contabilizando os votos na sessão de votação
            if ((dto.tipo() == TipoVoto.SIM)) {
                sessaoVotacao.setVotosSim(votos);
            }else if (dto.tipo() == TipoVoto.NAO) {
                sessaoVotacao.setVotosNao(votos);
            }
            
        }else{
            throw new VotacaoFechadaException("Não foi possivel votar, pois essa votação não está ativa");
        }
    }

}
