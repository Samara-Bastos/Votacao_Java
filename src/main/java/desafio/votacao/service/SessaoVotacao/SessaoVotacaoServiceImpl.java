package desafio.votacao.service.SessaoVotacao;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import desafio.votacao.dto.Voto.RequestVotoDto;
import desafio.votacao.enums.Resultado;
import desafio.votacao.enums.Situacao;
import desafio.votacao.enums.TipoVoto;
import desafio.votacao.exception.NotFoundException;
import desafio.votacao.exception.TempoInvalidoException;
import desafio.votacao.model.Pauta;
import desafio.votacao.model.SessaoVotacao;
import desafio.votacao.repository.SessaoVotacaoRepository;

@Service
public class SessaoVotacaoServiceImpl implements SessaoVotacaoService {
    
    @Autowired
    SessaoVotacaoRepository repository;

    @Override
    public void abrirSessaoVotacao(boolean ativaSessao, int tempo, Pauta pauta){
        if (!ativaSessao) {
            return;
        }
        verificaSeTempoSessaoEValido(tempo);
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
    public SessaoVotacao buscarSessaoVotacao(Long id){
        return repository.findById(id)
        .orElseThrow(() -> new NotFoundException("Não foi possivel encontrar essa sessão de votação"));
    }

    @Override
    public void contabilizarVotoNaSessao(SessaoVotacao sessaoVotacao, RequestVotoDto voto){
        if ((voto.tipo() == TipoVoto.SIM)) {
            sessaoVotacao.setVotosSim(sessaoVotacao.getVotosSim() + 1);
        }else {
            sessaoVotacao.setVotosNao(sessaoVotacao.getVotosNao() + 1);
        }
        repository.save(sessaoVotacao);
    }

    @Override
    @Scheduled(fixedRate = 60000)
    public void verificaSeTempoSessaoExpirou(){
        LocalTime agora = LocalTime.now().withNano(0);
        buscarSessaoVotacaoAtiva().forEach(sessaoBuscada ->{
            if (agora.equals(sessaoBuscada.getTempoFimSessao()) || agora.isAfter(sessaoBuscada.getTempoFimSessao())) {
                geraResultadoDaSessaoVotacao(sessaoBuscada);
                fechaSessaoVotacaoAtiva(sessaoBuscada);
            }
        });         
    }

    private List<SessaoVotacao> buscarSessaoVotacaoAtiva(){
        return repository.findBySessaoVotacaoAtiva();  
    }
    
    private void fechaSessaoVotacaoAtiva(SessaoVotacao sessaoVotacao){
        sessaoVotacao.setAtiva(false);
        sessaoVotacao.setSituacao(Situacao.FECHADA);
        repository.save(sessaoVotacao);
    }

    private void geraResultadoDaSessaoVotacao(SessaoVotacao sessaoVotacao){
        if (sessaoVotacao.getVotosSim() > sessaoVotacao.getVotosNao()) {
            sessaoVotacao.setResultado(Resultado.APROVADA);
        }else if(sessaoVotacao.getVotosSim() < sessaoVotacao.getVotosNao()){
            sessaoVotacao.setResultado(Resultado.REPROVADA);
        }else if(sessaoVotacao.getVotosSim() == sessaoVotacao.getVotosNao()){
            sessaoVotacao.setResultado(Resultado.EMPATE);
        }
        repository.save(sessaoVotacao);   
    }

    private void verificaSeTempoSessaoEValido(int tempo){
        if (tempo <= 0) {
            throw new TempoInvalidoException("O tempo de sessão não pode ser zero ou menor que zero");
        }
    }
}