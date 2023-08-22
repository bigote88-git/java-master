insert into students (appe, name) values ('perez', 'pablo');
insert into students (appe, name) values ('morales', 'ernesto');
insert into courses (name, teacher) values ('Hibernate JPA', 'bigote');
insert into courses (name, teacher) values ('Springboot 3', 'andres');
insert into persons(name, age) values('Pepito', 25);
insert into persons(name, age) values('Juanito', 35);
insert into addresses(street, number, person_id) values('Mayoreo', '78563', 1);
insert into addresses(street, number, person_id) values('Zumen', '112233', 1);
insert into courses_students values(1, 1);

insert into clients(name, payment_form) values('Danilo', 'CR');
insert into clients(name, payment_form) values('Jose', 'DR');

insert into invoices(description, total, clientid) values('Factura #1', 5200, 1);
insert into invoices(description, total, clientid) values('Factura #2', 1200, 1);
insert into invoices(description, total, clientid) values('Factura #3', 800, 1);
