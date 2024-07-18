package desafio.votacao.model;

import desafio.votacao.enums.Categoria;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class Pauta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String titulo;

    @Column
    private String descricao;

    @Column
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @OneToOne(mappedBy = "pauta", cascade = CascadeType.ALL)
    private SessaoVotacao votacao;

    @Transient
    private Boolean ativaSessao;
}
