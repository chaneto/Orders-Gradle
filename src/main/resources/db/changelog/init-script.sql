liquibase formatted sql
changeset chan:1

DROP USER IF EXISTS chaneto;
DROP DATABASE IF EXISTS orders_db;

CREATE USER chaneto with PASSWORD '12345';
CREATE DATABASE orders_db with OWNER chaneto;
GRANT ALL PRIVILEGES ON DATABASE orders_db TO chaneto;