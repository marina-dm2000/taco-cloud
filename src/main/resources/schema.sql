create table public."Taco_Order"
(
    id                serial
        constraint "Taco_Order_pk"
            primary key,
    "delivery_Name"   varchar(50) not null,
    "delivery_Street" varchar(50) not null,
    "delivery_City"   varchar(50) not null,
    "delivery_State"  varchar(2)  not null,
    "delivery_Zip"    varchar(10) not null,
    cc_number         varchar(16) not null,
    cc_expiration     varchar(5)  not null,
    cc_cvv            varchar(3)  not null,
    placed_at         timestamp   not null
);

alter table public."Taco_Order"
    owner to marina;

create table public."Taco"
(
    id             serial,
    name           varchar(50) not null,
    taco_order     bigint      not null
        constraint "Taco_Taco_Order_id_fk"
            references public."Taco_Order",
    taco_order_key bigint      not null,
    created_at     timestamp   not null
);

alter table public."Taco"
    owner to marina;

create table public."Ingredient"
(
    id   varchar(4)  not null
        constraint "Ingredient_pk"
            primary key,
    name varchar(25) not null,
    type varchar(10) not null
);

alter table public."Ingredient"
    owner to marina;

create table public."Ingredient_Ref"
(
    ingredient varchar(4) not null
        constraint "Ingredient_Ref_Ingredient_id_fk"
            references public."Ingredient",
    taco       bigint     not null,
    taco_key   bigint     not null
);

alter table public."Ingredient_Ref"
    owner to marina;

