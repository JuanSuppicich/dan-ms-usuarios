-- Database: dan-ms

-- DROP DATABASE "dan-ms";

DROP SCHEMA IF EXISTS ms_users CASCADE;
DROP SCHEMA IF EXISTS ms_orders CASCADE;
DROP SCHEMA IF EXISTS ms_products CASCADE;
DROP SCHEMA IF EXISTS ms_accounting CASCADE;

/*
CREATE DATABASE "dan-ms"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
*/

CREATE SCHEMA ms_users;
CREATE SCHEMA ms_orders;
CREATE SCHEMA ms_products;
CREATE SCHEMA ms_accounting;

create table ms_users.customer (customer_id  serial not null, cuit varchar(11) not null, post_date timestamp not null, put_date timestamp, delete_date timestamp, allowed_online boolean default false not null, email varchar(255) not null, max_current_account float8, business_name varchar(32) not null, user_id int4, primary key (customer_id));
create table ms_users.employee (employee_id  serial not null, email varchar(255) not null, name varchar(32) not null, post_date timestamp not null, put_date timestamp, user_id int4, primary key (employee_id));
create table ms_users.construction (construction_id  serial not null, description varchar(128), address varchar(32) not null, latitude float4 not null, longitude float4 not null, area int4, post_date timestamp not null, put_date timestamp, customer_id int4, construction_type_id int4, primary key (construction_id));
create table ms_users.construction_type (construction_type_id  serial not null, description varchar(255) not null, primary key (construction_type_id));
create table ms_users.user_type (user_type_id  serial not null, description varchar(255) not null, primary key (user_type_id));
create table ms_users.user (user_id serial not null, password varchar(32) not null, username varchar(20) not null, user_type_id int4, primary key (user_id));
alter table ms_users.customer add constraint uk_cuit unique (cuit);
alter table ms_users.user add constraint uk_username unique (username);
alter table ms_users.customer add constraint fk_user foreign key (user_id) references ms_users.user;
alter table ms_users.employee add constraint fk_user foreign key (user_id) references ms_users.user;
alter table ms_users.construction add constraint fk_customer foreign key (customer_id) references ms_users.customer;
alter table ms_users.construction add constraint fk_construction_type foreign key (construction_type_id) references ms_users.construction_type;
alter table ms_users.user add constraint fk_user_type foreign key (user_type_id) references ms_users.user_type;

create table ms_products.product (product_id  serial not null, description varchar(64), name varchar(32) not null, price float8 not null, current_stock int4 not null, minimum_stock int4 not null, unit_id int4, primary key (product_id));
create table ms_products.unit (unit_id  serial not null, description varchar(255) not null, primary key (unit_id));

create table ms_orders.order_item (order_item_id  serial not null, quantity int4 not null, price float8 not null, product_id int4, order_id int4, primary key (order_item_id));
create table ms_orders.order_state (order_state_id  serial not null, description varchar(255) not null, primary key (order_state_id));
create table ms_orders.order (order_id  serial not null, shipping_date timestamp not null, order_state_id int4, construction_id int4, primary key (order_id));
alter table ms_orders.order_state add constraint uk_description unique (description);
alter table ms_users.construction add constraint uk_description unique (description);
alter table ms_orders.order_item add constraint uk_product foreign key (product_id) references ms_products.product;
alter table ms_orders.order_item add constraint fk_order foreign key (order_id) references ms_orders.order;
alter table ms_orders.order add constraint fk_order_state foreign key (order_state_id) references ms_orders.order_state;
alter table ms_orders.order add constraint fk_construction foreign key (construction_id) references ms_users.construction;

create table ms_products.provision_item (provision_item_id  serial not null, quantity int4 not null, product_id int4, provision_id int4, primary key (provision_item_id));
create table ms_products.stock_movement (stock_movement_id  serial not null, quantity int4, movement_date timestamp not null, order_item_id int4, provision_item_id int4, product_id int4, primary key (stock_movement_id));
create table ms_products.provision (provision_id serial not null, provision_date timestamp, primary key (provision_id));
alter table ms_products.provision_item add constraint fk_product foreign key (product_id) references ms_products.product;
alter table ms_products.provision_item add constraint fk_provision foreign key (provision_id) references ms_products.provision;
alter table ms_products.product add constraint fk_unit foreign key (unit_id) references ms_products.unit;
alter table ms_products.stock_movement add constraint fk_order_item foreign key (order_item_id) references ms_orders.order_item;
alter table ms_products.stock_movement add constraint fk_provision_item foreign key (provision_item_id) references ms_products.provision_item;
alter table ms_products.stock_movement add constraint fk_product foreign key (product_id) references ms_products.product;

create table ms_accounting.check (bank varchar(32) not null, payment_date timestamp, number int4 not null, payment_method_id int4 not null, primary key (payment_method_id));
create table ms_accounting.cash (bill_code int4 not null, payment_method_id int4 not null, primary key (payment_method_id));
create table ms_accounting.payment_method (payment_method_id  serial not null, comment varchar(128), primary key (payment_method_id));
create table ms_accounting.payment (payment_id  serial not null, shipping_date timestamp, customer_id int4, payment_method_id int4, primary key (payment_id));
create table ms_accounting.transfer (cbu_destination varchar(22) not null, cbu_origin varchar(22) not null, code int8 not null, payment_method_id int4 not null, primary key (payment_method_id));
alter table ms_accounting.check add constraint uk_number unique (number);
alter table ms_accounting.cash add constraint uk_bill_code unique (bill_code);
alter table ms_accounting.transfer add constraint uk_code unique (code);
alter table ms_accounting.check add constraint fk_payment_method foreign key (payment_method_id) references ms_accounting.payment_method;
alter table ms_accounting.cash add constraint fk_payment_method foreign key (payment_method_id) references ms_accounting.payment_method;
alter table ms_accounting.payment add constraint fk_customer foreign key (customer_id) references ms_users.customer;
alter table ms_accounting.payment add constraint fk_payment_method foreign key (payment_method_id) references ms_accounting.payment_method;
alter table ms_accounting.transfer add constraint fk_payment_method foreign key (payment_method_id) references ms_accounting.payment_method;

INSERT INTO ms_users.user_type (description) VALUES
	('Customer'),
	('Vendedor');

INSERT INTO ms_users.construction_type (description) VALUES
	('Reforma'),
	('Casa'),
	('Edificio'),
	('Vial');

INSERT into ms_orders.order_state (description) VALUES
	('Nuevo'),
	('Confirmado'),
	('Pendiente'),
	('Cancelado'),
	('Aceptado'),
	('Rechazado'),
	('En preparacion'),
	('Entregado');

INSERT INTO ms_products.unit (description) VALUES
	('UN'),
	('M'),
	('CM'),
	('MM'),
	('INCH'),
	('KG'),
	('G'),
	('MG'),
	('M2'),
	('M3'),
	('CC'),
	('L'),
	('ML'),
	('PPM'),
	('PSI');



















