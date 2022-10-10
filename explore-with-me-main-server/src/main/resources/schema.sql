CREATE TABLE IF NOT EXISTS users
(
    id    INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name  VARCHAR(32)                              NOT NULL,
    email VARCHAR(254),
    CONSTRAINT pk_user PRIMARY KEY (id),
    CONSTRAINT UQ_USER_EMAIL UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS category
(
    id   INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name varchar(25),
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS event
(
    id                 INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    title              VARCHAR(120),
    description        VARCHAR(7000),
    category_id        INTEGER references category (id),
    annotation         varchar(2000),
    initiator          integer references users (id),
    date_create        timestamp WITHOUT TIME ZONE              NOT NULL,
    date_published     timestamp WITHOUT TIME ZONE              NOT NULL,
    date_event         timestamp WITHOUT TIME ZONE              NOT NULL,
    lat                decimal                                  not null,
    lon                decimal                                  not null,
    paid               boolean,
    participant_limit  integer,
    state              varchar,
    request_moderation boolean,
    CONSTRAINT pk_event PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS request
(
    id           INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    date_create  timestamp WITHOUT TIME ZONE              NOT NULL,
    event_id     integer references event (id),
    requestor_id integer references users (id),
    status       varchar(50),
    CONSTRAINT pk_booking PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS compilations
(
    id     INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    pinned boolean,
    title  varchar(120),
    CONSTRAINT pk_compilations PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS event_to_compilation
(
    event_id       integer references event (id),
    compilation_id integer references compilations (id)
);

CREATE TABLE if not exists comments
(
    id           integer generated by default as identity not null,
    comment      varchar(500),
    event_id     integer references event (id),
    author_id    integer references users (id),
    created_date timestamp WITHOUT TIME ZONE              NOT NULL,
    is_published  varchar,
    constraint pk_comment primary key (id)
);