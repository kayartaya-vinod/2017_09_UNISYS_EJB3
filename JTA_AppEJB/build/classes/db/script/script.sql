drop table accounts if exists;
drop table transaction_records if exists;

create table accounts(
account_number int primary key auto_increment,
customer varchar(50),
balance double);

insert into accounts(customer, balance) values('John Doe', 25000);
insert into accounts(customer, balance) values('Jane Doe', 25000);

create table transaction_records(
id int primary key auto_increment,
description varchar(250),
transaction_date datetime,
amount double
);

select * from accounts;
select * from transaction_records;
