# nt-consult-hotel
## Informações Gerais
- Projeto feito com o spring boot com o java17 usando a paradigma reativo
- Banco de dados utilizado postgresql, com o framework r2dbc
- Arquitetura utilizada: hexagonal (ports and adapters)
  - Módulo infrastructure, sendo responsáveis pelos port in (controllers) e pelos port out (repository e consumer)
  - Módulo application, sendo responsável pelos usercases / serviços
  - Módulo Domain, sendo responsável pelas entities e value objects
- Inicialização do banco de dados com a ferramenta flyway
- Para a utilização do serviço de notificação, foi utilizado o rabbitmq para gerenciar a fila
- Feito um pequeno sistema em node para simular o disparo de notificação

## Objetivo
O Objetivo do teste foi testar o spring boot com a ferramenta reativa, e a integração com o RabbitMQ

## Para levantar
- Importar o projeto na IDE de prefência e configurar para utilizar o java 17
- Na pasta principal, rodar o comando docker-compose up -d para levantar o rabbitMQ, postgres e node-handler

## Ambiente Full
- Rodar o compando `docker compose -f docker-compose-full.yaml up`

## Documentação API
- 