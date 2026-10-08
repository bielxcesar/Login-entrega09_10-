# Entrega dia 09/10 - Sistema de Autenticação e Gestão de Utilizadores Generico

Este projeto é um módulo de segurança e gestão de utilizadores desenvolvido com Java Spring Boot, Thymeleaf e MongoDB Atlas, construído com foco na modularidade e na separação de responsabilidades para futura integração em sistemas mais amplos.

## 1. Estrutura do Sistema e Decisões de Design

O sistema foi arquitetado seguindo o padrão **MVC (Model-View-Controller)**, garantindo que as regras de negócio, o acesso aos dados e a apresentação visual operem de forma independente.

*   **Segurança (Spring Security):** A autenticação e a autorização são geridas de forma centralizada. As palavras-passe são encriptadas com algoritmo *BCrypt* antes de serem armazenadas. O sistema implementa controlo de acesso baseado em perfis (*roles*), garantindo que rotas administrativas e áreas de redação sejam exclusivas para utilizadores devidamente autorizados. </br>


*   **Escalabilidade Visual e Temas:** A interface gráfica foi construída utilizando o motor de templates **Thymeleaf**. O design (HTML e CSS) está completamente desacoplado da lógica Java. Esta decisão garante a **escalabilidade visual do sistema**: novos layouts, templates ou temas completos podem ser adicionados no futuro alterando apenas os ficheiros no diretório `src/main/resources/templates`, sem necessidade de refatorar o código *backend*. 

## 2. Integração com MongoDB Atlas

O armazenamento de dados é suportado pelo MongoDB Atlas, um banco de dados NoSQL baseado em documentos.
*   A comunicação entre a aplicação Java e a base de dados na nuvem é feita através do **Spring Data MongoDB**, que mapeia as entidades (`@Document`) diretamente para as coleções do MongoDB de forma fluida.
*   Por questões de segurança, as credenciais da base de dados não estão expostas no código. A ligação é estabelecida dinamicamente através de uma *Connection String* configurada nas propriedades da aplicação.

## 3. Como Configurar e Executar o Projeto Localmente

### Pré-requisitos
*   [Java Development Kit (JDK) 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html) ou superior.
*   [Apache Maven](https://maven.apache.org/download.cgi) instalado.
*   Conta ativa no [MongoDB Atlas](https://www.mongodb.com/cloud/atlas) com um *cluster* configurado.

### Passo 1: Clonar o Repositório
Faça o clone deste repositório para a sua máquina local:
```bash
git clone [https://github.com/bielxcesar/Login-entrega09_10-.git](https://github.com/bielxcesar/Login-entrega09_10-.git)
cd seu-repositorio
```
Passo 2: Configurar a Ligação ao MongoDB Atlas
Por questões de segurança, as credenciais da base de dados não ficam expostas no código. É necessário criar um ficheiro de variáveis de ambiente local.
Na pasta raiz do teu projeto (no mesmo nível do ficheiro pom.xml), cria um ficheiro chamado exatamente .env.
Adiciona a tua Connection String fornecida pelo painel do MongoDB Atlas dentro desse ficheiro:

```
MONGODB_URI=mongodb+srv://<o_teu_utilizador>:<a_tua_senha>@cluster0.x2w3wah.mongodb.net/jurishome?retryWrites=true&w=majority
````

De seguida, navegue até ao diretório src/main/resources/ e abra o ficheiro application.properties.

Certifique-se de que ele está configurado para puxar a ligação do ficheiro .env oculto, ficando desta forma:
```
spring.application.name=JurisHome
spring.data.mongodb.uri=${MONGODB_URI}
spring.thymeleaf.cache=false
```

### Passo 3: Executar a Aplicação
Pode iniciar o servidor localmente através do Maven ou da sua IDE preferida.

Via terminal (Maven):
```bash
mvn spring-boot:run
```
Via IDE (IntelliJ IDEA / Eclipse):
```bash
Localize o ficheiro principal da aplicação (Application.java) na pasta src/main/java/com/example e clique em Run.
```
### Passo 4: Acesso ao Sistema
Quando o servidor iniciar com sucesso (indicado pela mensagem Started [NomeDaAplicação] in X seconds), abra o seu navegador e aceda aos seguintes endereços:
* Página de Registo: http://localhost:8080/cadastro
* Página de Autenticação: http://localhost:8080/login

