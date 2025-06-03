# 🏢 Sistema de Gerenciamento de Solicitações de Espaço

## 📄 Descrição

Este projeto consiste em um sistema distribuído para **gerenciamento de solicitações de uso de espaços físicos**, utilizando uma arquitetura de **microsserviços**. O sistema permite que usuários solicitem o uso de espaços e que essas solicitações passem por um fluxo de gerenciamento e aprovação.

---

## 🧱 Arquitetura

A arquitetura segue o padrão de **microsserviços**, com separação de responsabilidades e comunicação entre serviços via REST, utilizando o **Spring Cloud Eureka** para descoberta de serviços e **API Gateway** para roteamento.

### 🧩 Microsserviços

1. **UserService**
   - Cadastro, autenticação e gerenciamento de usuários.
   - Geração de tokens JWT.
   - Validação de credenciais e funções (roles).

2. **SpaceService**
   - CRUD de espaços físicos disponíveis.
   - Gerenciamento de horários e disponibilidade.

3. **SpaceRequestService**
   - Criação, listagem e atualização de solicitações de uso.
   - Fluxo de aprovação e validação de conflitos de agenda.

4. **Gateway**
   - Roteia as requisições para os microsserviços corretos.
   - Aplica filtros de segurança baseados em JWT.
   - Centraliza o acesso às APIs.

---

## 🚀 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **Spring Cloud (Eureka, Gateway)**
- **JWT (JSON Web Token)**
- **Swagger/OpenAPI**
- **Spring Security**
- **Spring Data JPA**

---

## 🔐 Segurança

- A autenticação é feita via **JWT**, gerado pelo `UserService` após login.
- O token é enviado em todas as requisições via header:

- O **Gateway** intercepta as requisições e valida o token JWT antes de permitir acesso aos serviços internos.
- Cada microsserviço pode conter segurança adicional baseada em roles.

---

## 🧭 Descoberta de Serviços

- O sistema utiliza **Eureka Server** como registro de serviços.
- Todos os microsserviços (exceto o Gateway) se registram no Eureka.
- O Gateway utiliza Eureka para resolver dinamicamente os destinos das requisições.

---

## 🧪 Documentação da API

Cada serviço possui documentação acessível via Swagger:

- **UserService**: `http://localhost:PORT/swagger-ui.html`
- **SpaceService**: `http://localhost:PORT/swagger-ui.html`
- **SpaceRequestService**: `http://localhost:PORT/swagger-ui.html`

> Substitua `PORT` pelas portas de cada microsserviço.

---

## ✅ Pré-Requisitos
Java 17 ou superior

Maven 3.6+
IDE de sua preferência (IntelliJ, VSCode, Eclipse)


## ⚙️ Configuração e Execução

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/Leonn001/roomManagement.git
   cd seu-repositorio

## Inicie o Eureka Server

cd eureka-server
./mvnw spring-boot:run

