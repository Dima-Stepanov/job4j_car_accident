/*Модель списка статей нарушений*/
create table if not exists rule (
    r_id   serial primary key,
    r_name varchar(200) not null unique
);