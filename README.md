# Sistema de Gerenciamento de Solicitações de Espaço

## Descrição

Este projeto consiste em um sistema de gerenciamento de solicitações de uso de espaços, composto por múltiplos microsserviços que se comunicam entre si. O sistema tem como objetivo permitir que os usuários solicitem o uso de espaços, e que as solicitações sejam gerenciadas de forma eficiente.

O sistema é baseado nas seguintes tecnologias:
- **Spring Boot**
- **Spring Cloud**
- **Eureka** (para descoberta de serviços)
- **Swagger** (para documentação da API)
- **JWT** (para autenticação de usuários)

## Arquitetura

A arquitetura do sistema segue o modelo de **microsserviços**, sendo composta pelos seguintes serviços:

1. **UserService**: Gerencia os usuários.
2. **SpaceService**: Gerencia os espaços disponíveis.
3. **SpaceRequestService**: Gerencia as solicitações de uso dos espaços.
4. **Gateway**: Roteia as requisições para os microsserviços corretos.

## Tecnologias Usadas

- **Spring Boot**: Framework para o desenvolvimento dos microsserviços.
- **Spring Cloud Eureka**: Para descoberta de serviços.
- **Swagger**: Para documentação da API.
- **JWT**: Para autenticação.
- **Docker**: Para facilitar a execução dos microsserviços em containers.

## Pré-Requisitos

- **Java 17** ou superior.
- **Maven** 3.6 ou superior.
- **Docker** (para rodar containers, caso necessário).
- IDE de sua preferência (exemplo: IntelliJ IDEA, Visual Studio Code).

## Instalação

### 1. Clonar o Repositório

```bash
git clone https://github.com/usuario/nome-do-repositorio.git
cd nome-do-repositorio
