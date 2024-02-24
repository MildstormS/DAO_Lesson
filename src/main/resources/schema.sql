create table if not exists customers
(
    id           serial primary key,
    name         character varying(30) not null,
    surname      character varying(30) not null,
    age          int                   not null,
    phone_number character varying(10) not null
);

create table if not exists orders
(
    id           serial primary key,
    date         date                  not null,
    customer_id  int                   not null unique,
    product_name character varying(50) not null,
    amount       int                   not null,
    foreign key (customer_id) references customers (id)
);

