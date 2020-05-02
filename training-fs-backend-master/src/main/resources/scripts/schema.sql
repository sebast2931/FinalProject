CREATE TABLE USERS(
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    USERNAME VARCHAR NOT NULL,
    PASSWORD VARCHAR NOT NULL
);

CREATE TABLE PRODUCTS(
    ID                      BIGINT PRIMARY KEY AUTO_INCREMENT,
    Nombre                  VARCHAR NOT NULL,
    Descripcion             VARCHAR NOT NULL,
    Precio_base             DECIMAL NOT NULL,
    Tasa_de_impuestos       DECIMAL NOT NULL,
    Estado                  VARCHAR NOT NULL,
    Inventario              INT NOT NULL
);