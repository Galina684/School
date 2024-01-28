create table persons (
person_id int primary key,
name varchar(255),
age int,
driver_license boolean
);

create table cars (
car_id int primary key,
brand varchar(255),
model varchar(255),
price decimal (10,2)
);

create table ownership (
ownership_id int primary key,
person_id int,
car_id int,
foreign key (person_id) references persons (person_id),
foreign key (car_id) references cars (car_id)
);