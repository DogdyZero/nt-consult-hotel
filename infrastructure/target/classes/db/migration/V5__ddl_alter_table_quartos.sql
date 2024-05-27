alter table quartos
    add hotel_id bigint;
alter table quartos
    add constraint
        fk_quarto_hotel foreign key (hotel_id) references hoteis (id);