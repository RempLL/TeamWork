--liquibase formatted sql

--changeset ash:1

CREATE TABLE users
(
    id              BIGINT PRIMARY KEY,
    chat_id         BIGINT,
    full_name       TEXT NOT NULL,
    access_level    TEXT NOT NULL,
    current_shelter TEXT,
    current_state   TEXT
);