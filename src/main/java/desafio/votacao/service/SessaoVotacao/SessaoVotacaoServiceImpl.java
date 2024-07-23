package desafio.votacao.service.SessaoVotacao;

import java.time.LocalTime;
import java.util.Optional;
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
import desafio.votacao.model.Pauta;
import desafio.votacao.model.SessaoVotacao;
import desafio.votacao.repository.SessaoVotacaoRepository;

@Service
public class SessaoVotacaoServiceImpl implements SessaoVotacaoService {
    
    @Autowired
    SessaoVotacaoRepository repository;

    //Criada uma instância do Scheduled com um pool de threads de tamanho 1, para especificar que apenas uma tarefa será executada por vez neste executor. 
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);


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

        // Agenda a tarefa para chamar a função a cada minuto
        scheduler.scheduleAtFixedRate(() -> verificaSeTempoSessaoExpirou(sessaoVotacao), 1, 1, TimeUnit.MINUTES);
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
    public void verificaSeTempoSessaoExpirou(SessaoVotacao sessaoVotacao ){
        LocalTime agora = LocalTime.now().withNano(0);

        if (agora.equals(sessaoVotacao.getTempoFimSessao())) {
            sessaoVotacao.setAtiva(false);
            sessaoVotacao.setSituacao(Situacao.FECHADA);

            if (sessaoVotacao.getVotosSim() > sessaoVotacao.getVotosNao()) {
                sessaoVotacao.setResultado(Resultado.APROVADA);
            }else if(sessaoVotacao.getVotosSim() < sessaoVotacao.getVotosNao()){
                sessaoVotacao.setResultado(Resultado.REPROVADA);
            }else if(sessaoVotacao.getVotosSim() == sessaoVotacao.getVotosNao()){
                sessaoVotacao.setResultado(Resultado.EMPATE);
            }

            repository.save(sessaoVotacao);
        }
    }


}