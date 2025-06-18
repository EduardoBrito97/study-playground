create table topics (
    status varchar(255) not null,
    date timestamp(6) not null,
    id bigint not null,
    message varchar(255) not null,
    title varchar(255) not null,
    answers bytea array not null,
    author bytea not null,
    course bytea not null,
    primary key (id));
