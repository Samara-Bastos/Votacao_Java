package desafio.votacao.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;
import desafio.votacao.enums.Resultado;
import desafio.votacao.enums.Situacao;


@Table(name = "Sessao_Votacao")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessaoVotacao {

    @Id
    @Column(name = "idvotacao")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVotacao;

    @Column
    private Boolean ativa;
    
    @OneToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    @Column(name = "voto_sim")
    @Builder.Default
    private Integer votosSim  = 0;

    @Column(name = "voto_nao")
    @Builder.Default
    private Integer votosNao  = 0;

    @Column(name = "tempo_inicio")
    private LocalTime tempoInicioSessao;

    @Column(name = "tempo_fim")
    private LocalTime tempoFimSessao;

    @Column
    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    @Column
    @Enumerated(EnumType.STRING)
    private Resultado resultado;

} 