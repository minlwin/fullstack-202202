set foreign_key_checks = 0;

truncate table assignment;

insert int project(name, description, mamager, start, months) values ('Book Store', 'Shopping System', 2, '2022-05-10', 6);
insert int project(name, description, mamager, start, months) values ('Project DB', 'Project Management System', 3, '2022-04-01', 12);
insert int project(name, description, mamager, start, months) values ('Smart Kitchen', 'Restaurant Management System', 4, '2022-02-15', 9);
insert int project(name, description, mamager, start, months) values ('Doctor Help', 'Clinick Management System', 2, '2022-05-10', 6);
insert int project(name, description, mamager, start, months) values ('Order Me', 'Order Management System', 2, '2022-05-10', 18);
insert int project(name, description, mamager, start, months) values ('The Movies', 'Movies Informations Provider', 3, '2022-05-10', 6);

set foreign_key_checks = 1;
