create table answers (
    is_solver boolean not null,
    date timestamp(6) not null,
    id bigint not null,
    topic_id bigint,
    user_id bigint,
    message varchar(255) not null,
    primary key (id));

create sequence answers_seq start with 1 increment by 50;

alter table if exists answers
    add constraint answers__users foreign key (user_id) references users;
alter table if exists answers
    add constraint answers__topic foreign key (topic_id) references topics;
