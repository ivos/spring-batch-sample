drop table customer if exists;

create table customer (
	id bigint not null primary key,
	name varchar2(100) not null
);
