alter table reservas
    add preco float4 not null;

alter table reservas
    add hotel_id bigint not null;

alter table reservas
    add constraint
        fk_reservas_hotel foreign key (hotel_id) references hoteis (id);

alter table reservas
    add quarto_id bigint not null;

alter table reservas
    add constraint
        fk_reservas_quarto foreign key (quarto_id) references quartos (id);

