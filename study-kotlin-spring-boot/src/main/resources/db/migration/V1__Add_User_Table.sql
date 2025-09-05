create table users (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    name varchar(255) not null,
    password varchar(255) not null,
    username varchar(255) not null unique
    );
