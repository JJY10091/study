use koreaitdb;

create table pos (
pos_code char(3) not null,
pos_name varchar(20) not null,
dept_code char(3) not null,

primary key(pos_code),
foreign key (dept_code) references dept(dept_code)
ON UPDATE CASCADE ON DELETE RESTRICT
);


INSERT INTO pos VALUES('101', '인사부장', '100');
INSERT INTO pos VALUES('202', '자재부장', '200');
INSERT INTO pos VALUES('303', '비서원', '300');
INSERT INTO pos VALUES('901', '비서원', '900');