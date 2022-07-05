/*Схема таблицы авторизации*/
CREATE TABLE if not exists authorities
(
    id serial primary key,
    authority VARCHAR(50) NOT NULL unique
);

CREATE TABLE if not exists users
(
    id serial primary key,
    username VARCHAR(50)  NOT NULL unique,
    password VARCHAR(100) NOT NULL,
    enabled  boolean default true,
    authority_id int not null references authorities(id)
);

insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, password, enabled, authority_id)
values ('root', '$2a$10$PyZlyP39bhauGWAgSz.2Su9BoK53NYivaufjn2vMpAZH0m8R2mcum',
        true, (select id from authorities where authority = 'ROLE_ADMIN'));