create sequence seq_comodidades;
create table comodidades
(
    id        bigint primary key not null default nextval('seq_comodidades'),
    nome      varchar(50)        not null,
    descricao varchar(200),
    hotel_id  bigint             not null
);

alter table comodidades
    add constraint
        fk_avaliacoes_hotel foreign key (hotel_id) references hoteis (id);