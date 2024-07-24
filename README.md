# Sistema de Votação

Esste projeto foi desenvolvido em java com spring boot e tem como seu principal objetivo a resolução desse DESAFIO

## Executar 

O projeto utiliza o Docker para subir um banco PostgreSQL, por isso ao abrir o mesmo execute:

docker compose up -d # Sobe o container do PostgreSQL

Após isso pode iniciar o projeto normalmente.

## Dependências

- Spring Data JPA
- Spring Web
- PostgreSQL Driver
- SpringBoot DevTools
- Docker Compose Support
- Flyway
- Bin Validation
- Swagger
- Mapstruct

## US's

- ✅ [US001] Montar esquema
    - ✅ [US001-1] Diagrama de classes
- ✅  [US002] Configurar ambientes
    - ✅ [US002-1] Criar repositório remoto
    - ✅ [US002-2] Criar repositório local
    - ✅ [US002-3] Subir container do Postgres no Docker
    - ✅ [US002-4] Conectar aplicação ao banco Postgres
- ✅  [US003] Criar entidades
    - ✅ [US003-1] Entidade Pauta
    - ✅ [US003-2] Entidade Votacao
    - ✅ [US003-3] Entidade Voto
    - ✅ [US003-4] Entidade Usuario
- ✅  [US004] Implementar cadastros
    - ✅ [US004-1] Cadastrar Usuario
    - ✅ [US004-2] Cadastrar Pauta
    - ✅ [US004-3] Abrir sessão de votação
    - ✅ [US004-4] Registrar Voto
    - ✅ [US004-5] Criar exceptions personalizadas
- ✅  [US005] Contabilizar os votos 
- ✅  [US006] Implementar métodos de exibição
    - ✅ [US006-1] Exibir Usuario
    - ✅ [US006-2] Exibir todas as pautas
    - ✅ [US006-3] Exibir sessão de votação
    - ✅ [US006-4] Exibir votos
- ✅  [US007] Criar funcionalidade para dar resultado da pauta
    - ✅ [US007-1] Fechar a pauta após o tempo determinado
    - ✅ [US007-2] Gerar o resultado com base nos votos
- ✅  [US008] Criar validações e tratamento de erros
-   [US009] Fazer testes
    -  [US009-1] Testes unitários
    -  [US009-2] Teste de integração
 


## US's - EXTRA

-  [US00X] Controle de usuário
    -  [US00X-1] ...


## Tomadas de decisões 

- Para verificar se o tempo da sessão expirou eu optei por criar uma tarefa que roda periodicamente e chama a função especificada.
- Tomei essa decisão, pois não estava conseguindo enxergar um local adequado para chamar a minha função 'verificaSeTempoSessaoExpirou' já que o mesmo poderia expirar em qualquer momento.
    - Eu usei o método scheduleAtFixedRate da classe ScheduledExecutorService, passando :
    - verificaSeTempoSessaoExpirou(sessaoVotacao) : como a função/task a ser executada, 
    - 1 : o minuto para a tarefa ser executada após a inicialização, 
    - 1 : o periodo que a tarefa vai ser executada, ou seja, a cada 1 minuto o scheduler vai chamar minha função,
    - TimeUnit.MINUTES : A unidade de tempo 


- Para gerenciar a base de dados eu optei por aplicar o flyway.
    - O flyway é uma ferramenta de versionamento de banco de dados, que além de criar as tabelas através das migrações ele também mantém um histórico de todas as modificações. 
    - Quando ele é executado, ele verifica o estado atual do banco de dados e aplica as migrações necessárias para levá-lo à versão mais recente.


## Endpoints 

POST : /usuario
- Criação de um novo usuário com CPF válido 

GET : /usuario/view
- Visualização de todos os usuários cadastrados

POST : /pauta
- Criação de uma nova pauta

GET : /pauta/view
- Visualização de todas as pautas cadastradas

GET : /pauta/view/:id
- Visualização da pauta selecionada

POST : /voto/:id
- Registra o voto na pauta da sessão especificada no id