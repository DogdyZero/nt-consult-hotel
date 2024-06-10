# nt-consult-hotel
## Informações Gerais
- Projeto feito com o spring boot com o java17 usando o framework spring web flux (paradigma reativo)
- Banco de dados utilizado postgresql, com o framework r2dbc
- Arquitetura utilizada: hexagonal (ports and adapters)
  - Módulo infrastructure, sendo responsáveis pelos port in (controllers) e pelos port out (repository e consumer)
  - Módulo application, sendo responsável pelos usercases / services
  - Módulo Domain,feito usando o DDD (Domain design driven) é responsável pelas entities e value objects
- Inicialização do banco de dados com a ferramenta flyway
- Para a utilização do serviço de notificação, foi utilizado o rabbitmq para gerenciar a fila
- Feito um pequeno sistema em node para simular o disparo de notificação

## Objetivo
O Objetivo do teste foi testar o spring boot com a ferramenta wb flux (reativa), e a integração com o RabbitMQ

## Para levantar
- Importar o projeto na IDE de prefência e configurar para utilizar o java 17
- Na pasta principal, rodar o comando docker-compose up -d para levantar o rabbitMQ, postgres e node-handler

## Ambiente Docker completo
- Rodar o compando `docker compose -f docker-compose-full.yaml up`

## Configuração kubernates
- Acessar a pasta k8s
- executar `sudo chmod +x run.sh` após `./run.sh` para levantar todos os ambientes
- Obs: A configuração inicial foi feita usando o minikube local, e ainda não foi configurado a escalabilidade conforme aumentar a quantidade de requests

# Documentação API
## http://localhost:8080/clientes
**Method**: POST
**URL**: http://localhost:8080/clientes
**Body**:
```json
{
    "nome": "Fernanda",
    "cpf": "36474930829",
    "email": "fer@hotmail.com"
}
```


## http://localhost:8080/clientes
**Method**: GET
**URL**: http://localhost:8080/clientes


## http://localhost:8080/hoteis
**Method**: POST
**URL**: http://localhost:8080/hoteis
**Body**:
```json
{
    "nome":"Recanto das aleluias",
    "localizacao": "São tomé das letras",
    "mediaAvaliacoes": 5,
    "listaQuartos":[
        {
            "numero": "01",
            "tipoQuarto": "standart",
            "preco": 250.00,
            "status" : "DISPONIVEL"
        }

    ]
}
```


## Quartos - Post
**Method**: POST
**URL**: http://localhost:8080/hoteis/Jk0jGI/quartos
**Body**:
```json
 {
    "numero": "12",
    "tipoQuarto": "standart",
    "preco": 250.00,
    "status" : "DISPONIVEL",
    "descricao": null
}
```


## Hotel - Get pelo Código
**Method**: GET
**URL**: http://localhost:8080/hoteis/XT12D4


## Get quarto no hotel
**Method**: GET
**URL**: http://localhost:8080/hoteis/XT12D4/quarto/01


## Reserva - Post
**Method**: POST
**URL**: http://localhost:8080/reservas
**Body**:
```json
{
    "codigoHotel": "XT14F5",
    "numeroQuarto" : "01",
    "checkin": "2024-01-01",
    "checkout": "2024-01-02",
    "diarias": 1,
    "numeroHospedes": 2,
    "cpfCliente" : "187.481.220-92",
    "detalhesPagamento" : "PIX",
    "contato": "(11)98859-6636"
}
```


## Get - reservas
**Method**: GET
**URL**: http://localhost:8080/reservas


## Get - Reservas - Codigo
**Method**: GET
**URL**: http://localhost:8080/reservas/OBESNS


## Alterar Quarto - Status Ocupado
**Method**: PATCH
**URL**: http://localhost:8080/quartos/01/hotel/Fmp1FY/ocupado


## Alterar Quarto - Status Disponivel
**Method**: PATCH
**URL**: http://localhost:8080/quartos/01/hotel/XT12D4/disponivel


## Get-Consulta hotel avançado
**Method**: GET
**URL**: http://localhost:8080/hoteis?numeroQuartos=1
**Filtros**:
- nome
- localizacao
- checkin
- checkout
- numeroHospedes
- numeroQuartos


## combinacao avançada
**Method**: POST
**URL**: http://localhost:8080/hoteis/filtro
**Body**:
```json
{
    "precoMinimo":200,
    "precoMaximo": 1000,
    "comodidades":[
        "café da manhã"
    ],
    "avaliacoes":[
        4,5
    ],
    "localizacoes": [
        "maragogi"]
}
``` 