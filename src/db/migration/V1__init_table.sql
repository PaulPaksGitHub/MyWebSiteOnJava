drop table if exists users;
drop table if exists res;

create table users (login varchar(50) , pass varchar(64) , salt varchar(64));
create table res (adress varchar(50), login varchar(50), role varchar(20));