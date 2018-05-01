insert into users (login, pass, salt) values ('pa', '1c597a01f767c2a9de609927a87946cc', '6B3BP7O4C8PJRMAA1VHAJD3YSEI0LITT');
insert into users (login, pass, salt) values ('ha', '7e9e025a501c75777682dd2405f4b231', 'ITSBQVZN8AJNL7E080XY6RX6S3PNSQ6X');
insert into users (login, pass, salt) values ('jdoe', 'a52833e3b81d7ffd3272f37dcb84fe20', '2K45ZH3G3WYK86RYDVGCG6ML4KPY2I9Y');
insert into users (login, pass, salt) values ('jrow', '1878356a24ead84df12e2afa97a4d0b9', '7X1PFTWGNZ7DO0RIINYQPWITMBPLLJ7J');
insert into users (login, pass, salt) values ('xxx', 'a051f8237087d0c4dd768505cf2952f9', 'VST7AR0FYAJ8KIK6QFQ6O58X82I9SRIU');

insert into res (adress, login , role) values ('A', 'pa', 'READ');
insert into res (adress, login , role) values ('A', 'ha', 'EXECUTE');
insert into res (adress, login , role) values ('A.B', 'pa', 'EXECUTE');
insert into res (adress, login , role) values ('A.B.C', 'pa', 'WRITE');
insert into res (adress, login , role) values ('a', 'jdoe', 'READ');
insert into res (adress, login , role) values ('a.b', 'jdoe', 'WRITE');
insert into res (adress, login , role) values ('a.b.c', 'jrow', 'EXECUTE');
insert into res (adress, login , role) values ('a.bc', 'jdoe', 'EXECUTE');