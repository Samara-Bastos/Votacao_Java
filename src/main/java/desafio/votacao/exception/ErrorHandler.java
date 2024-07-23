package desafio.votacao.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class ErrorHandler {
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleErrorNotFoundException(NotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(FindException.class)
    public ResponseEntity<String> handleErrorFindException(FindException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(TempoInvalidoException.class)
    public ResponseEntity<String> handleErrorTempoInvalidoException(TempoInvalidoException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(VotacaoFechadaException.class)
    public ResponseEntity<String> handleErrorVotacaoFechadaException(VotacaoFechadaException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
