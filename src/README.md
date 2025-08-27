API do Habit Tracker em Java com Spring Boot
Este é o repositório do backend para um sistema de Rastreamento de Hábitos (Habit Tracker). A aplicação é construída com Java e Spring Boot para fornecer uma API RESTful robusta, segura e stateless.

✨ Funcionalidades

API RESTful Segura: Oferece endpoints para operações de autenticação e CRUD (Criar, Ler, Deletar) sobre a entidade Habit.

Autenticação e Autorização com JWT: Implementa um fluxo de segurança stateless. O acesso a endpoints sensíveis é protegido e requer um JSON Web Token válido.

Segurança de Senhas: As senhas dos usuários são criptografadas usando o algoritmo BCrypt antes de serem persistidas.

Arquitetura em Camadas: Segue uma estrutura clara com camadas de Controller, Service e Repository para uma boa separação de responsabilidades.

Persistência de Dados: Utiliza Spring Data JPA e Hibernate para interagir com o banco de dados PostgreSQL.

Configuração Centralizada: As configurações do banco de dados e a chave secreta do JWT podem ser facilmente ajustadas no arquivo application.properties.

🛠️ Tecnologias Utilizadas

Java 21+

Spring Boot 3: Framework principal para o desenvolvimento da API.

Spring Security 6: Para implementação da camada de segurança e autenticação.

JWT (JSON Web Tokens): Biblioteca io.jsonwebtoken para a geração e validação de tokens.

Spring Data JPA / Hibernate: Para a camada de acesso a dados.

PostgreSQL: Banco de dados relacional para persistência dos dados.

Maven: Gerenciador de dependências do projeto.

Lombok: Para reduzir código boilerplate nas classes de modelo e DTOs.

🚀 Configuração e Uso

Pré-requisitos: Certifique-se de ter o JDK 21+ e o Maven instalados, além de uma instância do PostgreSQL rodando.

Clone o repositório:

Bash

git clone https://URL_DO_SEU_REPOSITORIO_AQUI.git
cd habittracker-api
Configurar o Banco de Dados e a Chave Secreta:
O arquivo src/main/resources/application.properties é onde você deve configurar a conexão com o banco de dados (URL, usuário, senha) e a sua chave secreta para o JWT (jwt.secret).

Executar a Aplicação:

Bash

mvn spring-boot:run
A API estará disponível em http://localhost:8080.

🔑 Endpoints da API

A API expõe endpoints RESTful para autenticação e interação com os dados de hábitos.

Autenticação (Público)

POST /api/auth/register: Cria um novo usuário.

POST /api/auth/login: Autentica um usuário e retorna um token JWT.

Hábitos (Protegido - Requer Bearer Token)

POST /api/habits: Cria um novo hábito para o usuário logado.

GET /api/habits: Retorna a lista de hábitos do usuário logado.

DELETE /api/habits/{id}: Deleta um hábito específico do usuário logado.

🔮 Melhorias Futuras

Desenvolvimento do Frontend: Criar a interface em React para consumir esta API.

Implementar Rastreamento: Adicionar a lógica e os endpoints para marcar um hábito como "concluído" em uma data específica.

Validação de Dados: Implementar validações (@Valid, @NotBlank, etc.) nos DTOs de entrada.

Tratamento de Exceções Global: Criar um @RestControllerAdvice para centralizar e padronizar as respostas de erro da API.

Testes: Adicionar testes unitários (JUnit/Mockito) e de integração.

Dockerização: Criar um Dockerfile para facilitar o deploy da aplicação.