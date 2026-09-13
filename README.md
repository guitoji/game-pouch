# 🎮 Pouch

API REST para gerenciamento de uma biblioteca pessoal de jogos.

O **Pouch** permite que usuários mantenham sua própria biblioteca de jogos, acompanhem o status de cada jogo, registrem horas jogadas, avaliações e resenhas.

O projeto foi desenvolvido com foco em boas práticas de desenvolvimento de APIs REST utilizando **Java e Spring Boot**, explorando autenticação, autorização, persistência de dados, DTOs, mapeamento de objetos e consultas dinâmicas.

## 🚀 Tecnologias

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Security
* JWT
* PostgreSQL
* Docker
* MapStruct
* Bean Validation
* Maven
* Git / GitHub

## ✨ Funcionalidades

### Usuários

* Cadastro de usuários
* Atualização de dados
* Consulta de usuários
* Autenticação utilizando JWT
* Controle de acesso baseado em roles

### Jogos

* Cadastro de jogos
* Consulta de jogos
* Atualização de jogos
* Exclusão de jogos
* Busca e filtros
* Paginação
* Categorias de jogos
* Avaliação dos jogos

### Biblioteca do usuário

Cada usuário pode adicionar jogos à sua biblioteca e manter informações específicas sobre sua relação com cada jogo, como:

* Status do jogo
* Horas jogadas
* Avaliação
* Resenha

## 🔐 Segurança

A API utiliza **Spring Security** para autenticação e autorização.

Após realizar o login, o usuário recebe um **JWT**, que deve ser enviado nas requisições protegidas através do header:

```http
Authorization: Bearer <token>
```

O acesso aos recursos é controlado de acordo com as permissões/roles atribuídas ao usuário.

## 🏗️ Arquitetura

O projeto segue uma organização baseada na separação de responsabilidades, utilizando camadas como:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

Também são utilizados **DTOs** para separar os objetos expostos pela API das entidades persistidas no banco de dados.

O **MapStruct** é utilizado para realizar o mapeamento entre entidades e DTOs.

## 🗃️ Modelo de dados

O projeto possui como principais entidades:

### User

Representa o usuário da aplicação.

### Game

Representa um jogo cadastrado no sistema.

### UserGame

Representa a relação entre um usuário e um jogo de sua biblioteca.

Essa entidade permite armazenar informações específicas daquela relação, como:

* Status
* Horas jogadas
* Avaliação
* Resenha

A utilização de `UserGame` permite que diferentes usuários tenham informações diferentes sobre o mesmo jogo.

## 🔎 Consultas e filtros

O projeto utiliza **Spring Data JPA Specifications** para construir consultas dinâmicas.

Isso permite combinar diferentes filtros sem a necessidade de criar um método de repository para cada combinação possível.

Exemplos de filtros:

* Título
* Desenvolvedor
* Publicadora
* Avaliação
* Categoria

Também são utilizadas consultas envolvendo relacionamentos entre entidades, como a busca de jogos presentes na biblioteca de determinado usuário.

## 🐘 Banco de dados

O projeto utiliza **PostgreSQL** como banco de dados.

O ambiente de desenvolvimento pode ser executado utilizando **Docker**, facilitando a configuração do banco e mantendo o ambiente consistente.

## ▶️ Executando o projeto

### Pré-requisitos

Antes de executar o projeto, é necessário ter instalado:

* Java 21
* Maven
* Docker

### 1. Clone o repositório

```bash
git clone <URL_DO_REPOSITORIO>
```

### 2. Entre no diretório

```bash
cd pouch
```

### 3. Inicie o banco de dados

```bash
docker compose up -d
```

### 4. Execute a aplicação

Utilizando Maven:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

A API estará disponível em:

```text
http://localhost:8080
```

## 📌 Status do projeto

🚧 Em desenvolvimento.

O projeto continua sendo evoluído com a implementação de novas funcionalidades e melhorias na arquitetura, segurança e qualidade do código.

## 📚 Objetivo

O Pouch foi desenvolvido como um projeto prático para aprofundar conhecimentos em desenvolvimento backend com Java e Spring Boot, colocando em prática conceitos como:

* Desenvolvimento de APIs REST
* Orientação a objetos
* Spring Boot
* Persistência com JPA/Hibernate
* Relacionamentos entre entidades
* DTOs
* MapStruct
* Validação
* Spring Security
* JWT
* Paginação
* Specifications
* PostgreSQL
* Docker
* Git

## 👨‍💻 Autor

**Guilherme Lima - guitoji**

Projeto desenvolvido para estudos e prática de desenvolvimento backend.
