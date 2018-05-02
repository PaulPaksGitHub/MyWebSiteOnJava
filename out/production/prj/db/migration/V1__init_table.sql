
create table users
(
  id integer not null primary key ,
  login varchar(50) not null unique ,
  pass varchar(64) not null,
  salt varchar(64) not null
);

create table res (
  id integer not null ,
  adress varchar(50) not null ,
  login varchar(50) not null references users(login),
  id_user integer not null references users(id) ,
  role varchar(20) not null,
  constraint res_pk primary key (login , role)
);