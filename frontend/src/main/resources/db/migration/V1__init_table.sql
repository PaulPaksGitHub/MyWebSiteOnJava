create table users
(
  id bigserial primary key ,
  login varchar(50) not null unique ,
  pass varchar(64) not null,
  salt varchar(64) not null
);

create table res (
  id bigserial primary key,
  adress varchar(50) not null ,
  login varchar(50) not null references users(login),
  userid integer not null references users(id),
  role varchar(20) not null
);

create table acc (
  id bigserial primary key ,
  login varchar(50) not null references users(login),
  autorityid varchar(50) not null,
  ds varchar(15) not null ,
  de varchar(15) not null ,
  vol integer not null
);