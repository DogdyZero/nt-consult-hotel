create sequence seq_clientes;
create table clientes
(
    id    bigint primary key default nextval('seq_clientes'),
    nome  varchar(200) not null,
    cpf   varchar(50)  not null,
    email varchar(50)  not null
);