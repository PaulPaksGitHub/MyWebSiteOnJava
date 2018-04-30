insert into users (login, pass, salt) values ('pa', '12', 'salt');
insert into users (login, pass, salt) values ('ha', '34', 'salt');

insert into res (adress, login , role) values ('A', 'pa', 'READ');
insert into res (adress, login , role) values ('A', 'ha', 'EXECUTE');
insert into res (adress, login , role) values ('A.B.C', 'pa', 'WRITE');
