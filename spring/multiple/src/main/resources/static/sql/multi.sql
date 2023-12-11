use koreaitdb;

-- config : 게시판 관리
create table config(
config_id int not null auto_increment,
config_code varchar(10) unique,
config_title varchar(255) not null,
config_comment char(1), -- Y, N
config_color char(7), -- colors
config_date date,
primary key(config_id)
);

-- borad..코드값 : 게시판
-- 코드로 구분 board_${configCode}
-- 프로그램으로 디비 생성한다

create table board_${configCode}(
id int not null auto_increment,
subject varchar(255) not null,
writer varchar(20) not null,
content text,
visit int,
regdate date,
grp int,
seq int,
depth int,
primary key(id)
);

-- files..코드값 : 다중파일업로드
create table files_${configCode}(
id int not null,
orgName varchar(255),
savedFileName varchar(255),
savedPathFileName varchar(255),
savedFileSize bigint,
folderName varchar(10),
ext varchar(20)
);

-- comment..코드값 : 댓글
create table comment_${configCode}(
c_id int not null auto_increment,
c_subject varchar(50),
c_writer varchar(20),
c_comment varchar(100),
c_visit int,
c_regdate date,
primary key(c_id)
);


select *, DATE_FORMAT(config_date, '%Y.%m.%d') as configDate FROM config;
