--liquibase formatted sql
--changeset TestUsers_sql:1

CREATE TABLE tax_band
(
    id LONG UNIQUE NOT NULL PRIMARY KEY,
    name VARCHAR NOT NULL,
    lower_limit INT NOT NULL,
    upper_limit INT,
    tax_rate_value INT NOT NULL,
    rate_number LONG NOT NULL
);

--changeset TestUsers_sql:2

CREATE TABLE band_set
(
    id LONG UNIQUE NOT NULL PRIMARY KEY,
    rate_number LONG NOT NULL,
    timestamp TIMESTAMP WITH TIME ZONE
);