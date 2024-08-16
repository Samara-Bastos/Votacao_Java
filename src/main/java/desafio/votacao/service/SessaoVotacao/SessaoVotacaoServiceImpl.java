package desafio.votacao.service.SessaoVotacao;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
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

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

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
        scheduler.scheduleAtFixedRate(() -> verificaSeTempoSessaoExpirou(sessaoVotacao.getIdVotacao(),sessaoVotacao.getTempoFimSessao()), 1, 1, TimeUnit.MINUTES);
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
    public void verificaSeTempoSessaoExpirou(Long id, LocalTime tempoFimSessao){
        LocalTime agora = LocalTime.now().withNano(0);
        if (!agora.equals(tempoFimSessao)) {
            return;
        }
        SessaoVotacao sessaoBuscada = buscarSessaoVotacao(id);
        geraResultadoDaSessaoVotacao(sessaoBuscada);
        fechaSessaoVotacaoAtiva(sessaoBuscada);            
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