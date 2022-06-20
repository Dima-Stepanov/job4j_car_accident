/*таблица описывает связь ManyToMany между accident<->rule*/
create table if not exists accident_rule(
    ac_id int not null references accident(ac_id),
    r_id int not null references rule(r_id),
    UNIQUE (ac_id, r_id)
);