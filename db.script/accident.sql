/*Таблица описывает инцидент Правонорушение*/
create table if not exists accident(
    ac_id serial primary key,
    ac_name varchar(2000),
    ac_text varchar(2000),
    ac_address varchar(2000),
    at_id int references accident_type(at_id)
);