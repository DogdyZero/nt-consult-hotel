alter table reservas
    add cliente_id bigint not null;

alter table reservas
    add constraint
        fk_reservas_cliente foreign key (cliente_id) references clientes (id);

alter table reservas
    add contato varchar(50) not null;
alter table reservas
    add detalhes_pagamento varchar(50) not null;