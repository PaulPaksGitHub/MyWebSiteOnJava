insert into users (login, pass, salt) values ('pa', '12', 'salt');
insert into users (login, pass, salt) values ('ha', '34', 'salt');
insert into users (login, pass, salt) values ('jdoe', 'sup3rpaZZ', 'salt');
insert into users (login, pass, salt) values ('jrow', 'Qweqrty12', 'salt');
insert into users (login, pass, salt) values ('xxx', 'yyy', 'salt');

insert into res (adress, login , role) values ('A', 'pa', 'READ');
insert into res (adress, login , role) values ('A', 'ha', 'EXECUTE');
insert into res (adress, login , role) values ('A.B', 'pa', 'EXECUTE');
insert into res (adress, login , role) values ('A.B.C', 'pa', 'WRITE');
insert into res (adress, login , role) values ('a', 'jdoe', 'READ');
insert into res (adress, login , role) values ('a.b', 'jdoe', 'WRITE');
insert into res (adress, login , role) values ('a.b.c', 'jrow', 'EXECUTE');
insert into res (adress, login , role) values ('a.bc', 'jdoe', 'EXECUTE');