CREATE TABLE IF NOT EXISTS stat
(
    id        INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    app       varchar,
    uri       varchar,
    ip        varchar,
    timestamp timestamp,
    CONSTRAINT pk_stat PRIMARY KEY (id)
);