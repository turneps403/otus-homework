drop database homeworkdb;
drop user homeuser;
create user homeuser with password 'password';
create database homeworkdb with owner=homeuser CONNECTION LIMIT = 3;
\connect homeworkdb;
alter default privileges grant all on tables to homeuser;
alter default privileges grant all on sequences to homeuser;

create table my_users(
    user_id integer primary key not null,
    first_name varchar(256) not null,
    last_name varchar(256) not null,
    email varchar(200) not null,
    phone varchar(14) not null
);
create sequence my_users_seq increment 1 start 1;