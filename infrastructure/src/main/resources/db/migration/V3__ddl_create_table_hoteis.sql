create sequence seq_hoteis;

create table hoteis
(
    id              bigint primary key default nextval('seq_hoteis'),
    nome            varchar(50)  not null,
    localizacao     varchar(200) not null,
    media_avaliacao int
);