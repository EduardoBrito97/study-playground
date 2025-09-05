create table topics (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    status varchar(255) not null,
    date timestamp(6) not null,
    message varchar(255) not null,
    title varchar(255) not null,
    author_id bigint,
    course_id bigint,
    FOREIGN KEY (author_id) REFERENCES users (id),
    FOREIGN KEY (course_id) REFERENCES courses (id)
    );

