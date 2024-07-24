CREATE TABLE Pauta (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT,
    categoria VARCHAR(30) NOT NULL
);

CREATE TABLE Sessao_Votacao (
    id SERIAL PRIMARY KEY,
    ativa BOOLEAN,
    pauta_id INTEGER,
    tempo_inicio TIMESTAMP,
    tempo_fim TIMESTAMP,
    situacao VARCHAR(20),
    FOREIGN KEY (pauta_id) REFERENCES Pauta(id)
);

CREATE TABLE Usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL
);

CREATE TABLE Voto (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(3) NOT NULL,
    usuario_id INTEGER NOT NULL,
    sessao_votacao_id INTEGER NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id),
    FOREIGN KEY (sessao_votacao_id) REFERENCES Sessao_Votacao(id)
);