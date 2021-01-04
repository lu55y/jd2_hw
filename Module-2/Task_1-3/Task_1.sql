create database if not exists listExpenses;
use ListExpenses;

create table if not exists expenses(
num int,
paydate date,
receiver int,
value dec
);

insert into expenses (num,paydate,value,receiver) value(1,'2011-5-10',20000.0,1);
insert into expenses (num,paydate,value,receiver) value(2,'2011-5-10',94200.0,2);
insert into expenses (num,paydate,value,receiver) value(3,'2011-5-11',10000.0,3);
insert into expenses (num,paydate,value,receiver) value(4,'2011-5-11',12950.0,2);

create table if not exists receivers(
num int,
name varchar(255)
);
ALTER TABLE receivers modify name varchar(255) CHARACTER SET utf8;
insert into receivers values (1, 'Интернет-провайдер "Соло"');
insert into receivers values (2, 'Гимермаркет "Корона"');
insert into receivers values (3, 'МТС');
