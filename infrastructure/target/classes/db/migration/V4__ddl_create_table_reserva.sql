create sequence seq_reservas;

create table reservas
(
    id              bigint primary key default nextval('seq_reservas'),
    codigo_reserva  varchar(10) not null,
    checkin         date        not null,
    checkout        date        not null,
    diarias         int         not null,
    desconto        float4             default 0,
    total_reserva   float4      not null,
    numero_hospedes int         not null
);