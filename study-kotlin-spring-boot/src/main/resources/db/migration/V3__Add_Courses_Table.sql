create table courses (
    id bigint not null,
    category varchar(255) not null,
    name varchar(255) not null,
    primary key (id));

create sequence courses_seq start with 1 increment by 50;
