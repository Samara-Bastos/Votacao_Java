package desafio.votacao.service.SessaoVotacao;

import java.time.LocalTime;
import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import desafio.votacao.dto.Voto.RequestVotoDto;
import desafio.votacao.enums.Resultado;
import desafio.votacao.enums.Situacao;
import desafio.votacao.enums.TipoVoto;
import desafio.votacao.exception.NotFoundException;
import desafio.votacao.model.Pauta;
import desafio.votacao.model.SessaoVotacao;
import desafio.votacao.repository.SessaoVotacaoRepository;

@Service
public class SessaoVotacaoServiceImpl implements SessaoVotacaoService {
    
    @Autowired
    SessaoVotacaoRepository repository;


    @Override
    public void abrirSessaoVotacao(int tempo, Pauta pauta){

        SessaoVotacao sessaoVotacao = SessaoVotacao.builder()
                                                    .ativa(true)
                                                    .pauta(pauta)
                                                    .tempoInicioSessao(LocalTime.now().withNano(0))
                                                    .tempoFimSessao(LocalTime.now().plusMinutes(tempo).withNano(0))
                                                    .situacao(Situacao.ABERTA)
                                                    .build();  

        repository.save(sessaoVotacao);
    }

    @Override
    public Optional<SessaoVotacao> buscarSessaoVotacao(Long id){
        Optional<SessaoVotacao> sessaoVotacao = repository.findById(id);
        
        if(sessaoVotacao.isEmpty()){
            throw new NotFoundException("Não foi possivel encontrar essa sessão de votação");
        }

        return sessaoVotacao;
    }

    @Override
    public void contabilizarVotoNaSessao(Long id, RequestVotoDto dto){
        SessaoVotacao sessaoVotacao = buscarSessaoVotacao(id).get();

        if ((dto.tipo() == TipoVoto.SIM)) {
            sessaoVotacao.setVotosSim(sessaoVotacao.getVotosSim() + 1);
        }else if (dto.tipo() == TipoVoto.NAO) {
            sessaoVotacao.setVotosNao(sessaoVotacao.getVotosNao() + 1);
        }

        repository.save(sessaoVotacao);
    }

    
    @Override
    @Scheduled(fixedRate = 1000) 
    public void verificaSeTempoSessaoExpirou(){
        LocalTime agora = LocalTime.now().withNano(0);
        List<SessaoVotacao> sessaoVotacaoList = repository.findBySessaoVotacaoAtiva();

        for(SessaoVotacao sessaoReturn : sessaoVotacaoList){
            if (agora.equals(sessaoReturn.getTempoFimSessao())) {
            
                sessaoReturn.setAtiva(false);
                sessaoReturn.setSituacao(Situacao.FECHADA);
    
                if (sessaoReturn.getVotosSim() > sessaoReturn.getVotosNao()) {
                    sessaoReturn.setResultado(Resultado.APROVADA);
                }else if(sessaoReturn.getVotosSim() < sessaoReturn.getVotosNao()){
                    sessaoReturn.setResultado(Resultado.REPROVADA);
                }else if(sessaoReturn.getVotosSim() == sessaoReturn.getVotosNao()){
                    sessaoReturn.setResultado(Resultado.EMPATE);
                }
    
                repository.save(sessaoReturn);
            }
        }
    }


}