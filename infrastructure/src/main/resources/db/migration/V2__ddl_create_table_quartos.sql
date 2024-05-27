create sequence seq_quartos;

create table quartos
(
    id          bigint primary key default nextval('seq_quartos'),
    numero      varchar(50) not null,
    descricao   varchar(200),
    tipo_quarto varchar(50),
    preco       float4      not null
);