Projeto de Sistema de Gerenciamento de Solicitações de Espaço
Descrição
Este projeto é um sistema de gerenciamento de solicitações de espaços, composto por vários microsserviços, incluindo UserService, SpaceService, SpaceRequestService e um Gateway que gerencia as requisições entre os serviços. O sistema permite aos usuários solicitar o uso de espaços e gerenciar as solicitações feitas.

Arquitetura
A arquitetura do sistema segue o padrão de microsserviços utilizando o Spring Boot, Spring Cloud e Eureka para o registro e descoberta de serviços. O sistema é composto pelas seguintes partes principais:

UserService: Serviço de gerenciamento de usuários.
SpaceService: Serviço de gerenciamento de espaços.
SpaceRequestService: Serviço responsável por gerenciar as solicitações de espaços feitas pelos usuários.
Gateway: Serve como o ponto de entrada para as requisições dos usuários e roteia as chamadas para os microsserviços apropriados.
Requisitos
Java 17 ou superior
Maven 3.6 ou superior
Docker (para rodar o banco de dados e serviços em containers)
IDE de sua preferência (exemplo: IntelliJ IDEA, VSCode)
Configuração
Clonar o repositório:

bash
Copiar código
git clone https://github.com/usuario/nome-do-repositorio.git
cd nome-do-repositorio
Configurar o Eureka (opcional se não utilizar um servidor Eureka externo):

Certifique-se de que o Eureka Server está rodando no endereço configurado no application.properties ou no application.yml de cada serviço.
Se estiver utilizando o Spring Cloud Eureka, basta rodar o Eureka Server em localhost:8761.
Executar os serviços:

Cada microsserviço pode ser executado individualmente. Para isso, navegue até o diretório do microsserviço e execute:

bash
Copiar código
mvn spring-boot:run
Gateway:

O Gateway é responsável por rotear as requisições entre os microsserviços. Quando estiver rodando, ele estará disponível na porta 9000. As requisições são encaminhadas para os respectivos microsserviços, como o UserService, SpaceService e SpaceRequestService.

Swagger para Documentação:

A documentação da API está disponível no Swagger UI. Após rodar os microsserviços e o Gateway, acesse a interface Swagger através do seguinte link:

Gateway Swagger
Endpoints
Abaixo estão os principais endpoints para interagir com o sistema:

1. UserService (Serviço de Usuário)
GET /api/users/{username}: Busca informações de um usuário pelo nome de usuário.
2. SpaceService (Serviço de Espaços)
GET /api/spaces/{spaceName}: Busca informações sobre um espaço pelo nome do espaço.
3. SpaceRequestService (Serviço de Solicitações de Espaço)
POST /api/space-request/createSpaceRequest: Cria uma nova solicitação de uso de espaço.
Exemplo de Payload:
json
Copiar código
{
  "userName": "user1",
  "spaceName": "space1",
  "startDate": "2024-12-01T10:00:00",
  "endDate": "2024-12-05T18:00:00"
}
PUT /api/space-request/{id}/accept: Aceita uma solicitação de espaço.
PUT /api/space-request/{id}/decline: Recusa uma solicitação de espaço.
4. Gateway (Ponto de Entrada para as APIs)
O Gateway roteia as requisições para os microsserviços. O Swagger UI pode ser usado para visualizar e testar os endpoints.
Estrutura de Pastas
userService/: Serviço responsável pela gestão de usuários.
spaceService/: Serviço responsável pela gestão de espaços.
spaceRequestService/: Serviço responsável pela gestão das solicitações de espaço.
gateway/: Gateway que gerencia as requisições dos clientes.
Tecnologias Usadas
Spring Boot: Framework principal utilizado para desenvolvimento dos microsserviços.
Spring Cloud: Utilizado para descoberta de serviços via Eureka.
Swagger: Para documentação da API.
JWT (JSON Web Tokens): Para autenticação de usuários.
Docker: Para facilitar a configuração do banco de dados e serviços em containers.
Execução Local
Para rodar os serviços localmente, siga os passos abaixo:

Rode o Eureka Server (caso não tenha um servidor Eureka em execução).
Inicie o UserService, SpaceService, SpaceRequestService, e Gateway.
Acesse o Swagger UI na URL do Gateway:
http://localhost:9000/swagger-ui.html
Considerações Finais
Este projeto serve como uma base para a criação de um sistema de gerenciamento de solicitações de espaços, podendo ser expandido e modificado conforme a necessidade do projeto. Ele utiliza boas práticas de desenvolvimento de microsserviços com Spring Boot, Spring Cloud, e Eureka, além de proporcionar uma documentação robusta via Swagger.

Licença
Este projeto está licenciado sob a Licença MIT.
