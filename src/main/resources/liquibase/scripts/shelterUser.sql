-- liquibase formatted sql

-- changeset shintar:1

CREATE TABLE guest
(
    id           BIGINT PRIMARY KEY,
    chat_id      BIGINT,
    full_name    TEXT NOT NULL,
    access_level TEXT NOT NULL
);