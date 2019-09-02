# Legal Process Manager!

Olá, me chamo Higor e vou te ajudar a configurar o ambiente de desenvolvimento desta aplicação de forma simples e rápida.

Mas antes, é necessário que voce tenha o **Docker** e o **docker-compose** instalado em sua maquina.

Voce pode conferir onde fazer esta instalação nestes dois links:
 - [Como instalar o Docker](https://docs.docker.com/install/) 
 - [Como instalar o docker-compose](https://docs.docker.com/compose/install/)

# Subindo a stack

Clone este projeto e navegue na raiz da aplicação (onde se encontra este mesmo arquivo de **Markdown**).

Como o Docker e o docker-compose já deveriam estar instalados na sua maquina, você será capaz de rodar este comando:
`docker-compose up --build`
O sufixo `--build` pode ser suprimido nas proximaś vezes que quiser rodar esta aplicação.

## Estrutura e Endpoints

Esta aplicação esta organizada da seguinte forma:

### Backend
```
.
├── Dockerfile
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src
    ├── main
    │   ├── java.com.softplan.backend
    │   │   ├── DemoApplication.java
    │   │   ├── Initializer.java
    │   │   ├── model
    │   │   │   ├── Process.java
    │   │   │   ├── ProcessRepository.java
    │   │   │   ├── Seem.java
    │   │   │   ├── User.java
    │   │   │   └── UserRepository.java
    │   │   └── web
    │   │       ├── ProcessController.java
    │   │       └── UserController.java
    │   └── resources
    │       └── application.properties
    └── test.java.com.softplan.backend
        └── DemoApplicationTests.java
```

#### Principais arquivos trabalhados
 - **Initializer:** Um mock que é persistido ao iniciar servidor, infelizmente eles não se sobre escrevem, criando duplicatas.
 - **model/Process.java:** é uma classe, com a anotação ***@Entity*** e persistivel, não há unidade de testes e não há validações nos campos.
 - **model/ProcessRepository.java:** é uma interface, que implementa ***JpaRepository*** não há metodos implementados que sobreescrevam os metodos herdados.
 - **model/Seem.java:** é uma classe não implementada, com a anotação ***@Entity*** e persistivel, não há unidade de testes e não há validações nos campos.
 - **model/User.java:** é uma classe, com a anotação ***@Entity*** e persistivel, não há unidade de testes e não há validações nos campos.
 - **model/UserRepository.java:** é uma interface, que implementa ***JpaRepository*** não há metodos implementados que sobreescrevam os metodos herdados.
 - **web/ProcessController.java:** é uma classe, com a anotação ***@RestController*** e trata do roteamento para a entidade Process através das endpoints `/process/:id` e `/processes`, não há unidade de testes de integração e não há validações nos campos.
 - **web/UserController.java:** é uma classe, com a anotação ***@RestController*** e trata do roteamento para a entidade User através das endpoints `/user/:id` e `/users`, não há unidade de testes de integração e não há validações nos campos.

### Endpoints

| Vérbo HTTP     |Path                           |Entradas                     | Saidas                     |
|----------------|-------------------------------|-----------------------------|----------------------------|
|GET             |`/processes`            		   |                             | `200`                      |
|GET             |`/process/:id`            		 | `:id`(Long)                 | `200`                      |
|POST            |`/process/`					 	         | `:date`(Date), `:city`(String), `:stateOrProvince`(String), `:pending`(Boolean), `:description`(String)                                              | `201`                      |
|PUT             |`/process/:id`					 	     | `:id`(Long),`:date`(Date), `:city`(String), `:stateOrProvince`(String), `:pending`(Boolean), `:description`(String)                                    | `200`                      |
|DELETE          |`/process/:id`					 	     | `:id`(Long)                 | `204`                      |
|GET             |`/users`            		       |                             | `200`                      |
|GET             |`/user/:id`            		     | `:id`(Long                  | `200`                      |
|POST            |`/user/`					 	           | `:name`(String), `:email`(String), `:type`(String)                                                                                                     | `201`                      |
|PUT             |`/user/:id`					 	         | `:id`(Long),`:name`(String), `:email`(String), `:type`(String)                                                                                         | `200`                      |
|DELETE          |`/user/:id`					 	         | `:id`(Long)                 | `204`

#### User Mock
```
{
  "id": 1, /* Não necessário no metodo POST */
  "date": "1991-08-31",
  "city": "São Paulo",
  "stateOrProvince": "SP",
  "pending": true,
  "description": "Lorem Ipsum Dollor sit amet"
}
```

#### Process Mock
```
{
  "id": 1, /* Não necessário no metodo POST */
  "name": "Luis Eduardo",
  "email": "luis@eduardo.com",
  "type": "ADMIN",
}
```

### Frontend
```
.
├── Dockerfile
├── nginx.conf
├── package.json
├── public
│   ├── favicon.ico
│   ├── index.html
│   ├── logo192.png
│   ├── logo512.png
│   ├── manifest.json
│   └── robots.txt
├── README.md
├── src
│   ├── App.css
│   ├── App.js
│   ├── AppNavbar.js
│   ├── App.test.js
│   ├── Home.js
│   ├── index.css
│   ├── index.js
│   ├── logo.svg
│   ├── processes
│   │   ├── ProcessEdit.js
│   │   └── ProcessList.js
│   ├── serviceWorker.js
│   └── users
│       ├── UserEdit.js
│       └── UserList.js
└── yarn.lock
```

#### Principais arquivos trabalhados
 - **src/App.js:** Principal componente, serve como a camada de roteamento.
 - **src/AppNavbar.js:** Componente da barra superior da aplicacão.
 - **src/Home.js:** Componente que serve como pagina principal da aplicação.
 - **src/processes/ProcessEdit.js:** Componente faz parte do CRUD da entidade Process (create/edit).
 - **src/processes/ProcessList.js:** Componente Lista de todos os processos.
 - **src/users/UserEdit.js:** Componente faz parte do CRUD da entidade User (create/edit).
 - **src/users/UserList.js:** Componente Lista de todos os usuários.

ps.: Recursos como paginação não foram implementadas.

### Endpoints

| Vérbo HTTP     |Path                           | Saidas                     |
|----------------|-------------------------------|----------------------------|
|GET             |`/`            		             | `200`                      |
|GET             |`/users/`            		       | `200`                      |
|GET             |`/users/:id`					 	       | `200`                      |
|GET             |`/processes/`					 	       | `200`                      |
|GET             |`/processes/:id`  		 	       | `200`                      |

## Stack

 - SpringBoot
 - Maven
 - JPA
 - React
 - Bootstrap
 - Reactstrap