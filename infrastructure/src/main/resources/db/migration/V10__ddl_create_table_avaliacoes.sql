create sequence seq_avaliacoes;
create table avaliacoes
(
    id         bigint primary key not null default nextval('seq_avaliacoes'),
    estrelas   int                not null,
    hotel_id   bigint             not null,
    cliente_id bigint             not null
);

alter table avaliacoes
    add constraint
        fk_avaliacoes_cliente foreign key (cliente_id) references clientes (id);

alter table avaliacoes
    add constraint
        fk_avaliacoes_hotel foreign key (hotel_id) references hoteis (id);