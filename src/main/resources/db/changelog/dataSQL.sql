--liquibase formatted sql
--changeset TestUsers_sql:1

INSERT INTO tax_band
VALUES (1, 'Tax Band A', 0, 5000, 0, 1),
       (2, 'Tax Band B', 5000, 20000, 20, 1),
       (3, 'Tax Band C', 20000, null, 40, 1);

--changeset TestUsers_sql:2

INSERT INTO band_set
VALUES (1, 1, now());
