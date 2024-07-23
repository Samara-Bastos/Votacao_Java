package desafio.votacao.service.Pauta;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import desafio.votacao.dto.Pauta.RequestPautaDto;
import desafio.votacao.dto.Pauta.ResponsePautaDto;
import desafio.votacao.exception.NotFoundException;
import desafio.votacao.exception.TempoInvalidoException;
import desafio.votacao.mapper.PautaMapper;
import desafio.votacao.model.Pauta;
import desafio.votacao.repository.PautaRepository;
import desafio.votacao.service.SessaoVotacao.SessaoVotacaoServiceImpl;

@Service
public class PautaServiceImpl implements PautaService {
    
    @Autowired
    PautaRepository repository;

    @Autowired
    SessaoVotacaoServiceImpl sessaoVotacaoService;

    @Override
    public ResponsePautaDto create(RequestPautaDto dto){

        Pauta pauta = PautaMapper.INSTANCE.dtoToPauta(dto);
        repository.save(pauta);

        if (dto.ativaSessao() == true) {
            if (dto.tempoSessao() == 0) {
                throw new TempoInvalidoException("O tempo de sessão não pode ser zero");
            }
            sessaoVotacaoService.abrirSessaoVotacao(dto.tempoSessao(), pauta);
        }

        return PautaMapper.INSTANCE.pautaToDto(pauta);
        
    }

    @Override
    public List<ResponsePautaDto> visualizar(){
        return repository.findAll().stream().map(ResponsePautaDto::new).toList();
    };


    @Override
    public ResponsePautaDto visualizarPautaSelecionada( Long id){
        Optional<Pauta> pautaReturn = repository.findById(id);
        
        if (pautaReturn.isEmpty()) {
            throw new NotFoundException("Não foi possivel encontrar a pauta");
        }

        return PautaMapper.INSTANCE.pautaToDto(pautaReturn.get());
    }
    
} 
