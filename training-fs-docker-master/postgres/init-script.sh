#!/bin/bash
set -e

POSTGRES="psql --username ${POSTGRES_USER} -v ON_ERROR_STOP=1"

$POSTGRES --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE DATABASE orders;
EOSQL

$POSTGRES --dbname "orders" <<-EOSQL
    CREATE TABLE PRODUCTS(
    ID                      SERIAL PRIMARY KEY,
    Nombre                  VARCHAR NOT NULL,
    Descripcion             VARCHAR NOT NULL,
    Precio_base             DECIMAL NOT NULL,
    Tasa_de_impuestos       DECIMAL NOT NULL,
    Estado                  VARCHAR NOT NULL,
    Inventario              INT NOT NULL
);


    CREATE TABLE USERS(
    ID SERIAL PRIMARY KEY ,
    USERNAME VARCHAR NOT NULL,
    PASSWORD VARCHAR NOT NULL
);


EOSQL