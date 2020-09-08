insert into user (user_name, first_Name, last_Name) values ('naveen', 'Naveen', 'Bakade');

insert into user (user_Name, first_Name, last_Name) values ('xyz', 'xyz', 'xyz');

insert into seller (user_id) values (select user_id from user where user_name = 'xyz');

--insert into mobile (color, company, in_stock, memory, model, name, ram, os) 
--values ('Red', 'Samsung', 2, 2, 'X', 'X', 4, 'Android')
--
--insert into mobile (color, company, in_stock, memory, model, name, ram, os) 
--values ('Blue', 'Nokia', 2, 2, 'Y', 'Y', 4, 'Android')