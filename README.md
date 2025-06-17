# Pix 2

Esse projeto simula operações baseadas em chaves pix.

## Tecnologias:

- **Java 21**
- **Spring Framework**
- **PostgreSQL**
- **JUnit/Mockito**
- **Redis Cache**
- **Git**
- **Docker e Docker Compose**
- **Javadoc**
- **Open API (Swagger)**

## Arquitetura:
O projeto foi elaborado seguindo os princípios de arquitetura limpa (Uncle Bob), e segue a metodologia **12-Factor**:

- **Ambientes**: Ambiente controlado pelo docker e docker compose, garantindo igualdade de ambientes de execução.
- **Versionamento**: Uso de Git para versionamento e 
- **Testes**: **60 testes** unitários garantindo as regras de negócio da aplicação.
- **Isolamento**: Separação de responsabilidades em camadas da aplicação.

## Como executar:

### Pré-requisitos:
- **Git**
- **java 21 (JDK) e Maven**
- **Docker e Docker Compose**

### Passos para rodar na IDE:
1. **Clone o repositório:**
   ```bash
   git clone https://github.com/Marcus-Nastasi/pix-two.git

2. **Execute com sua IDE de preferência**

### Passos para rodar containerizado:
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
A aplicação disponibiliza as seguintes rotas para requisição:

```bash
# buscar todas as ocorrências de chave pix (paginado):
GET: http://localhost:8080/api/pix?page=0&size=10

# buscar chave pix por ID (UUID):
GET: http://localhost:8080/api/pix/{id}

# buscar chaves pix com filtros especiais:
GET: http://localhost:8080/api/pix?page=0&size=10&keyType=cpf&agencyNumber=1234&accountNumber=12345678&name=mark&creationDate=2025-06-15T12:49:55.737393&inactivationDate=2025-06-15T16:17:51.528222

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
