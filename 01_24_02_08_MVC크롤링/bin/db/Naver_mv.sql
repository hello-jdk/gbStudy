insert into users values ('admin','1111','관리자',99,9999999,1);

create table users(
   uPk varchar(100) primary key,
   uPw varchar(100) not null,
   uName varchar(100) not null,
   uAge int not null,
   uPoint int default 0,
   uAdmin int default 0
);

create table MV(
   mNum int primary key,
   mName varchar(100) not null,
   onAir varchar(100),
   genre varchar(100),
   nation varchar(100),
   showTime varchar(100),
   release varchar(200),
   dName varchar(100),
   actors varchar(150),
   rAge varchar(100),
   rAgeInt int,
   rank int,
   story varchar(1000),
   stock int default 10,
   price int default 10000
);


select * from user_tables;

select * from MV;
select * from users;
select * from MV order by mname asc; 

drop table MV;
drop table users;

