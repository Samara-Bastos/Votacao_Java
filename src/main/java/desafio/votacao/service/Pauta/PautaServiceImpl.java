package desafio.votacao.service.Pauta;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import desafio.votacao.dto.Pauta.RequestPautaDto;
import desafio.votacao.dto.Pauta.ResponsePautaDto;
import desafio.votacao.exception.NotFoundException;
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
    public ResponsePautaDto registrar(RequestPautaDto dto){
        Pauta pauta = PautaMapper.INSTANCE.dtoToPauta(dto);
        repository.save(pauta);
        sessaoVotacaoService.abrirSessaoVotacao(dto.ativaSessao(), dto.tempoSessao(), pauta);
        return PautaMapper.INSTANCE.pautaToDto(pauta);
    }

    @Override
    public List<ResponsePautaDto> visualizar(){
        return repository.findAll().stream().map(ResponsePautaDto::new).toList();
    };


    @Override
    public ResponsePautaDto visualizarPautaSelecionada( Long id){
        Pauta pauta = verificaSeExistePautaPeloId(id);
        return PautaMapper.INSTANCE.pautaToDto(pauta); 
    }

    private Pauta verificaSeExistePautaPeloId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi possivel encontrar a pauta"));
    }
    
} 
