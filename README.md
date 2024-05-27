# nt-consult-hotel
- Projeto feito com o spring boot com o java17 usando a paradigma reativo
- Banco de dados utilizado postgresql, com o framework r2dbc
- Arquitetura utilizada: hexagonal (ports and adapters)
  - Modulo infrastructure, sendo responsáveis pelos port in (controllers) e pelos port out (repository e consumer)
  - Módulo application, sendo responsável pelos usercases / serviços
  - Módulo Domain, sendo responsável pelas entities e value objects
- Inicialização do banco de dados com a ferramenta flyway
- Para a utilização do serviço de notificação, foi utilizado o rabbitmq para gerenciar a fila
- Feito um pequeno sistema em node para simular o disparo de notificação

## Objetivo
O Objetivo do teste foi testar o spring boot com a ferramenta reativa, e a integração com o RabbitMQ

## Para levantar
- Criar o banco de dados hotelaria
- Importar o projeto e iniciar utilizando o java17
- Na pasta principal, rodar o comando docker-compose up -d
- Acessar a pasta notificacao-handler, e rodar npm i e npm run dev
- OBS: posteriormente será configurado o start do node via docker
