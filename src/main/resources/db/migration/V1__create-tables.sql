CREATE TABLE Pauta (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255),
    descricao TEXT,
    categoria VARCHAR(30)
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
    nome VARCHAR(255),
    cpf VARCHAR(11) UNIQUE
);

CREATE TABLE Voto (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(3),
    usuario_id INTEGER,
    sessao_votacao_id INTEGER,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id),
    FOREIGN KEY (sessao_votacao_id) REFERENCES Sessao_Votacao(id)
);