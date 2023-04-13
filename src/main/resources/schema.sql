create table public.taco_order
(
    id              bigint generated always as identity
        constraint taco_order_pk
            primary key,
    delivery_name   varchar(50) not null,
    delivery_street varchar(50) not null,
    delivery_city   varchar(50) not null,
    delivery_state  varchar(2)  not null,
    delivery_zip    varchar(10) not null,
    cc_number       varchar(16) not null,
    cc_expiration   varchar(5)  not null,
    cc_cvv          varchar(3)  not null,
    placed_at       timestamp   not null
);

alter table public.taco_order
    owner to marina;

create table public.taco
(
    id             bigint generated always as identity
        constraint taco_pk
            primary key,
    name           varchar(50) not null,
    taco_order     bigint      not null
        constraint "taco_Taco_Order_id_fk"
            references public.taco_order,
    taco_order_key bigint      not null,
    created_at     timestamp   not null
);

alter table public.taco
    owner to marina;

create table public.ingredient
(
    id   varchar(4)  not null
        constraint ingredient_pk
            primary key,
    name varchar(25) not null,
    type varchar(10) not null
);

alter table public.ingredient
    owner to marina;

create table public.ingredient_ref
(
    ingredient varchar(4) not null
        constraint "Ingredient_Ref_Ingredient_id_fk"
            references public.ingredient,
    taco       bigint     not null,
    taco_key   bigint     not null
);

alter table public.ingredient_ref
    owner to marina;

create table public."user"
(
    id           bigint generated always as identity
        constraint user_pk
            primary key,
    username     varchar(50)  not null,
    password     varchar(255) not null,
    fullname     varchar(50)  not null,
    street       varchar(50)  not null,
    city         varchar(50)  not null,
    state        varchar(2)   not null,
    zip          varchar(10)  not null,
    phone_number varchar(20)  not null
);

alter table public."user"
    owner to marina;

