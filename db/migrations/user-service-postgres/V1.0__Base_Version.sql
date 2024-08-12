CREATE TABLE IF NOT EXISTS users
(
    id bigint PRIMARY KEY NOT NULL,
    created_at timestamp(6) with time zone NOT NULL,
    email varchar(255),
    name varchar(255),
    updated_at timestamp(6) with time zone NOT NULL,
    CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email)
);