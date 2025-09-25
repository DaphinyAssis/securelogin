# SecureLogin

SecureLogin é um sistema de autenticação web seguro desenvolvido em **Spring Boot** com integração **MongoDB**, utilizando **Spring Security** para gerenciamento de login e roles de usuários. O projeto possui uma interface moderna e futurista com **Thymeleaf** e CSS personalizado.

---

## Tecnologias Utilizadas

* Java 17
* Spring Boot 3.5.x
* Spring Security
* Spring Data MongoDB
* Spring Session com MongoDB
* Thymeleaf (templates HTML)
* Maven (gerenciamento de dependências)
* HTML5 / CSS3

---

## Funcionalidades

* Cadastro de usuários com validação de campos e criptografia de senha (BCrypt)
* Login seguro com Spring Security
* Logout seguro
* Exibição de usuário logado no painel de sucesso
* Feedback visual com mensagens de sucesso e erro
* Interface futurista responsiva com CSS animado

---

## Estrutura do Projeto
<img width="405" height="795" alt="{4BE02834-8BCE-4BC0-A11E-DB1910547B7E}" src="https://github.com/user-attachments/assets/e7ffc30c-d39a-4942-bdb5-f92f50024f61" />

---

## Configuração do Banco de Dados

O projeto utiliza **MongoDB** como banco de dados. No arquivo `application.yml`, configure:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/securelogin
spring.data.mongodb.database=securelogin
```

---

## Como Rodar o Projeto

1. Certifique-se que você tem **Java 17** e **Maven** instalados.
2. Clone o projeto:

```bash
git clone https://github.com/daphinyassis/securelogin.git
cd securelogin
```

3. Execute o Maven para baixar as dependências:

```bash
mvn clean install
```

4. Rode a aplicação:

```bash
mvn spring-boot:run
```

5. Acesse no navegador:

```
http://localhost:8080/
```

---

## Páginas Disponíveis

* `/login` – Página de login
* `/register` – Página de cadastro
* `/success` – Página após login bem-sucedido
* `/` – Home page
* `/logout` – Logout seguro

---

## Observações

* UI/UX do sistema agradavel e morderna
<img width="1908" height="860" alt="{F102B088-27CD-4957-9F55-ABB966F0A3E1}" src="https://github.com/user-attachments/assets/0f3937fd-4db2-463c-9f41-5bc180a59884" />

* Senhas são armazenadas de forma **criptografada** usando BCrypt.
  <img width="1351" height="638" alt="{233844ED-AB3F-476B-B9EA-E01D6F888D81}" src="https://github.com/user-attachments/assets/0ea4debf-5dbf-4a92-a953-8cc1535ecd04" />


