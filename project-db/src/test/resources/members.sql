set foreign_key_checks = 0;

truncate table assignment;
truncate table task;
truncate table project;
truncate table member;

insert into member(name, login_id, password, role, active) values ('Ko Ko Oo', 'kokooo', 'kokooo', 'Admin', 1);
insert into member(name, login_id, password, role, active) values ('Aung Aung', 'aungaung', 'aungaung', 'Member', 1);
insert into member(name, login_id, password, role, active) values ('Aung Naing', 'aungnaing', 'aunaing', 'Member', 1);
insert into member(name, login_id, password, role, active) values ('Thiha', 'thiha', 'thiha', 'Member', 0);
insert into member(name, login_id, password, role, active) values ('San Lwin', 'sanlwin', 'sanlwin', 'Member', 0);
insert into member(name, login_id, password, role, active) values ('Moe Moe', 'moemoe', 'moemoe', 'Member', 0);

set foreign_key_checks = 1;
