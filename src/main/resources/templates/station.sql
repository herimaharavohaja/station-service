CREATE TABLE station (
id_station SERIAL PRIMARY KEY,
station_name VARCHAR (50),
Location VARCHAR (250),
System VARCHAR (50) CHECK (System IN ('local', 'central'))
);

CREATE TABLE product_template(
id_product_temp SERIAL PRIMARY KEY,
name VARCHAR (100) NOT NULL,
unit_price NUMERIC(10, 2) NOT NULL,
evaporation_rate INTEGER
);

CREATE TABLE product(
id_product SERIAL PRIMARY KEY,
id_product_template INT REFERENCES product_template(id_product_temp),
id_station int REFERENCES station (id_station)
);

CREATE TABLE stock_mouvement(
id_supply SERIAL PRIMARY KEY,
id_station int REFERENCES station(id_station),
id_product int REFERENCES product(id_product),
quantity NUMERIC(10, 2) NOT NULL,
date_supply timestamp NOT NULL,
type VARCHAR (50)
);