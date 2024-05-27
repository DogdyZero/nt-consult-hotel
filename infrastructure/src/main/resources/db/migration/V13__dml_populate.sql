insert into clientes (nome, cpf, email) values ('Gene G Mack','187.481.220-92', 'gene@yahoo.com');
insert into clientes (nome, cpf, email) values ('Frank I Moore','401.898.230-54', 'frank@gmail.com');
insert into clientes (nome, cpf, email) values ('Michael T Nance','577.792.940-05', 'michale@gmail.com');
insert into clientes (nome, cpf, email) values ('Myrtle J Gerdes','836.249.100-00', 'gerdes@uol.com');

insert into hoteis(codigo, nome, localizacao, media_avaliacao) values ('XT12D4', 'Confort Suits', 'Vitória', 4);
insert into hoteis(codigo, nome, localizacao, media_avaliacao) values ('XT14F5', 'Praia Dourada', 'Maragogi', 5);
insert into hoteis(codigo, nome, localizacao, media_avaliacao) values ('TJ12D4', 'Delmar', 'Aracaju', 4);

insert into quartos (numero, preco, tipo_quarto, hotel_id, hospedes_quarto)
select '01', 240.0, 'standart', id, 2 from hoteis where codigo = 'XT12D4';

insert into quartos (numero, preco, tipo_quarto, hotel_id, hospedes_quarto)
select '02', 240.0, 'standart', id, 2 from hoteis where codigo = 'XT12D4';

insert into quartos (numero, preco, tipo_quarto, hotel_id, hospedes_quarto)
select '03', 240.0, 'standart', id, 2 from hoteis where codigo = 'XT12D4';

insert into quartos (numero, preco, tipo_quarto, hotel_id, hospedes_quarto)
select '11', 300.0, 'standart triplo', id, 3 from hoteis where codigo = 'XT12D4';

insert into quartos (numero, preco, tipo_quarto, hotel_id, hospedes_quarto)
select '12', 300.0, 'standart triplo', id, 3 from hoteis where codigo = 'XT12D4';

insert into quartos (numero, preco, tipo_quarto, hotel_id, hospedes_quarto)
select '13', 300.0, 'standart triplo', id, 3 from hoteis where codigo = 'XT12D4';

insert into quartos (numero, preco, tipo_quarto, hotel_id, hospedes_quarto)
select '01', 240.0, 'standart', id, 3 from hoteis where codigo = 'XT14F5';

insert into quartos (numero, preco, tipo_quarto, hotel_id, hospedes_quarto)
select '02', 240.0, 'standart', id, 3 from hoteis where codigo = 'XT14F5';

insert into quartos (numero, preco, tipo_quarto, hotel_id, hospedes_quarto)
select '03', 240.0, 'standart', id, 3 from hoteis where codigo = 'XT14F5';

insert into quartos (numero, preco, tipo_quarto, hotel_id, hospedes_quarto)
select '11', 480.0, 'luxo', id, 3 from hoteis where codigo = 'XT14F5';

insert into quartos (numero, preco, tipo_quarto, hotel_id, hospedes_quarto)
select '12', 480.0, 'luxo', id, 3 from hoteis where codigo = 'XT14F5';

insert into quartos (numero, preco, tipo_quarto, hotel_id, hospedes_quarto)
    select '13', 480.0, 'luxo', id, 3 from hoteis where codigo = 'XT14F5';

insert into quartos (numero, preco, tipo_quarto, hotel_id, hospedes_quarto)
select '01', 110.0, 'individual', id, 1 from hoteis where codigo = 'TJ12D4';

insert into quartos (numero, preco, tipo_quarto, hotel_id, hospedes_quarto)
select '02', 110.0, 'individual', id, 1 from hoteis where codigo = 'TJ12D4';

insert into quartos (numero, preco, tipo_quarto, hotel_id, hospedes_quarto)
select '03', 110.0, 'individual', id, 1 from hoteis where codigo = 'TJ12D4';

insert into quartos (numero, preco, tipo_quarto, hotel_id, hospedes_quarto)
select '11', 180.0, 'duplo', id, 2 from hoteis where codigo = 'TJ12D4';

insert into quartos (numero, preco, tipo_quarto, hotel_id, hospedes_quarto)
select '12', 180.0, 'duplo', id, 2 from hoteis where codigo = 'TJ12D4';

insert into quartos (numero, preco, tipo_quarto, hotel_id, hospedes_quarto)
select '13', 180.0, 'duplo', id, 2 from hoteis where codigo = 'TJ12D4';


insert into comodidades (nome, hotel_id)
values ('spa', (select id from hoteis where codigo = 'TJ12D4')),
       ('banheira hidromassagem', (select id from hoteis where codigo = 'TJ12D4')),
       ('café da manhã', (select id from hoteis where codigo = 'TJ12D4')),
       ('pensão completa', (select id from hoteis where codigo = 'TJ12D4')),
       ('chá noturno', (select id from hoteis where codigo = 'TJ12D4')),
       ('pet friendly', (select id from hoteis where codigo = 'TJ12D4')),
       ('lavanderia', (select id from hoteis where codigo = 'TJ12D4')),
       ('spa', (select id from hoteis where codigo = 'XT14F5')),
       ('banheira hidromassagem', (select id from hoteis where codigo = 'XT14F5')),
       ('café da manhã', (select id from hoteis where codigo = 'XT14F5')),
       ('pensão completa', (select id from hoteis where codigo = 'XT14F5')),
       ('chá noturno', (select id from hoteis where codigo = 'XT14F5')),
       ('lavanderia', (select id from hoteis where codigo = 'XT14F5')),
       ('café da manhã', (select id from hoteis where codigo = 'XT12D4'));
