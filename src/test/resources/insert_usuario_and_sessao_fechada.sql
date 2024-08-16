
INSERT INTO Usuario (id, nome, cpf) VALUES (1, 'Usuário Teste', '79905896058');

INSERT INTO Pauta (id, titulo, descricao, categoria) VALUES (1, 'Pauta Teste', 'Descrição da pauta', 'TECNOLOGIA');

INSERT INTO Sessao_Votacao (idVotacao, ativa, pauta_id, tempo_inicio, tempo_fim, situacao, voto_sim, voto_nao,resultado) 
VALUES (1, false, 1, '09:00:00', '17:00:00', 'FECHADA', 0, 0, 'EMPATE');
