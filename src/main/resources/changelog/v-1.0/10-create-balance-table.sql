create table balance (
    id serial primary key,
    balance int,
    transaction_type varchar(100) check (transaction_type in ('SALARY','GRANT'))
)