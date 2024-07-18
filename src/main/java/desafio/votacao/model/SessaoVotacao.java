package desafio.votacao.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import desafio.votacao.enums.Situacao;


@Table(name = "Sessao_Votacao")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessaoVotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Boolean ativa;
    
    @OneToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    @OneToMany
    @Builder.Default
    private List<Voto> votos  = new ArrayList<>();

    @Column(name = "tempo_inicio")
    private LocalDateTime tempoInicioSessao;

    @Column(name = "tempo_fim")
    private LocalDateTime tempoFimSessao;

    @Column
    @Enumerated(EnumType.STRING)
    private Situacao situacao;
} 