create table answers (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    is_solver boolean not null,
    date timestamp(6) not null,
    topic_id bigint,
    user_id bigint,
    message varchar(255) not null,
    FOREIGN KEY (topic_id) REFERENCES topics (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
    );