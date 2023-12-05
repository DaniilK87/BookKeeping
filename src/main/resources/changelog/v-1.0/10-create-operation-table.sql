create table operation (
    id serial primary key,
    date varchar(30),
    sum int,
    type varchar(256),
    balance_id serial,
    foreign key (balance_id) references balance(id))