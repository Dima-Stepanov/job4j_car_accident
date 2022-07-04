/*Таблица описывает категорию инцидента*/
create table if not exists accident_type(
    at_id serial primary key,
    at_name varchar(1000) not null unique
);