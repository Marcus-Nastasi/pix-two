# Pix 2

Esse projeto simula operações baseadas em chaves pix.

## Tecnologias:

- **Linguagem**: Java 21
- **Framework**: Spring Framework
- **Banco de dados**: PostgreSQL
- **Testes**: JUnit/Mockito
- **Cache**: Redis Cache
- **Versionamento**: Git
- **Container**: Docker e Docker Compose
- **Documentação**: Javadoc e Open API (Swagger)

## Documentação:
O sistema conta com 3 níveis de documentação:
1. **Readme**: documentação contendo informações gerais sobre o projeto, 
   oferecendo uma visão geral sobre padrão arquitetural, práticas, dependências,
   modos de execução, testes e rotas de API.
2. **Javadoc**: documentação do código a nível de classes e métodos.
3. **Open API (Swagger)**: documentação das rotas de API e objetos relevantes para as rotas,
   como modelos de requests e responses.

## Arquitetura:
O projeto foi elaborado seguindo os princípios de **Arquitetura Limpa (Uncle Bob)**,
- **Isolamento**: Garante isolamento entre a camada de domínio, aplicação, infraestrutura e portas.
- **Flexibilidade**: O padrão arquitetural adotado permite total flexibilidade. Com a camada de domínio e 
   regras de negócio separadas de infraestrutura, o projeto é facilmente adaptável a mudanças de frameworks 
   e bibliotecas sem que as regras de negócio sejam afetadas.
- **SOLID**: O projeto visa seguir os princípios SOLID, garantindo escalabilidade e evitando bad smells.
- **DDD**: O projeto também é orientado ao domínio, resguardando regras de negócio fortemente ligadas as
   classes de domínio.

Metodologia **12-Factor**:
- **Versionamento**: Uso de Git e GitHub para versionamento.
- **Config/Ambientes**: Ambiente controlado pelo docker e docker compose, garantindo igualdade de ambientes de execução.
   Propriedades da aplicação divididas entre ambiente de dev, docker e produção, e automatizadas por parâmetros.
- **Testes**: **90 testes** unitários garantindo o devido funcionamento das regras de negócio da aplicação.
- **Isolamento/dependências**: Utiliza Maven (Java) e Docker, o que garante que dependências estão claramente definidas (pom.xml no Maven e Dockerfile).
- **Backing Services**: Utilizando PostgreSQL e Redis como recursos anexáveis, acessados por configuração, e isolados por ambiente.
- **Build, release and run**: Pipelines de teste, build e run automatizadas pelo Docker e Docker Compose.
- **Concurrency**: Roda serviços separados como Redis e PostgreSQL via Docker. A arquitetura está pronta para escalar a aplicação com múltiplos processos.
- **Disposability**: A aplicação Spring Boot pode ser rapidamente inicializada ou derrubada com Docker.
- **Dev/Prod Parity**: Utiliza Docker Compose local e perfis (dev vs prd) que refletem o comportamento em produção (Redis e Postgres).

## Como executar:
### Pré-requisitos:
- **Git**
- **Docker e Docker Compose**
- **java 21 (JDK) e Maven** (opcional)
- *Ter as portas 5432 (Pg), 6379 (Redis) e 8080 (aplicação) livres*

### Passos para rodar na IDE:
1. **Clone o repositório:**
   ```bash
   git clone https://github.com/Marcus-Nastasi/pix-two.git
2. **Garantir que exista um banco de dados Postgres na porta 5432**
3. **Garantir a existência de um cache Redis na porta 6379**
4. **Execute com sua IDE de preferência**

### Passos para rodar no container:
1. **Clone o repositório:**
   ```bash
   git clone https://github.com/Marcus-Nastasi/pix-two.git
   
2. **Rode o docker compose no terminal (na pasta docker do projeto):**
    ```bash
   [sudo] docker-compose up --build -d
   
3. **Aguarde a construção e execução dos containers**

4. **Quando a aplicação for construída, acesse o endpoint do swagger**
   ```bash
    http://localhost:8080/swagger-ui/index.html

5. **Ou faça requisições com o seu Http Client preferido**
   ```bash
    http://localhost:8080/api/pix

### Rotas de API:
- A aplicação disponibiliza as seguintes rotas para requisição:
   ```bash
   # buscar todas as ocorrências de chave pix (paginado):
   GET: http://localhost:8080/api/pix?page=0&size=10
   
   # buscar chave pix por ID (UUID):
   GET: http://localhost:8080/api/pix/{id}
   
   # buscar chaves pix com filtros especiais:
   GET: http://localhost:8080/api/pix/search?page=0&size=10&keyType=cpf&agencyNumber=1234&accountNumber=12345678&name=mark&creationDate=2025-06-15&inactivationDate=2025-06-15
   
   # registrar chave pix:
   POST: http://localhost:8080/api/pix
   
   # exemplo de objeto de registro:
   #{
   #  "pixType": "celular",
   #  "value": "+21 33 123456789",
   #  "accountType": "poupança",
   #  "agencyNumber": 4355,
   #  "accountNumber": 72398,
   #  "firstName": "Label",
   #  "lastName": "Junes"
   #}
   
   # atualizar uma chave pix:
   PATCH: http://localhost:8080/api/pix
   
   # exemplo de objeto de atualização:
   #{
   #  "id": "066242df-e544-4106-be6b-221e4199a550",
   #  "accountType": "poupança",
   #  "agencyNumber": 4355,
   #  "accountNumber": 72398,
   #  "firstName": "Label Jhon",
   #  "lastName": "Junes"
   #}
   
   # inativar uma chave pix por ID (UUID):
   DELETE: http://localhost:8080/api/pix/{id}

- Veja mais exemplos de requisições na documentação da API
   com Open API, na rota http://localhost:8080/swagger-ui/index.html