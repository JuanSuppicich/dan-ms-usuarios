-- Database: dan-ms

-- DROP DATABASE "dan-ms";

DROP SCHEMA IF EXISTS ms_usuarios CASCADE;
DROP SCHEMA IF EXISTS ms_pedidos CASCADE;
DROP SCHEMA IF EXISTS ms_materiales CASCADE;
DROP SCHEMA IF EXISTS ms_pagos CASCADE;

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

CREATE SCHEMA ms_usuarios;
CREATE SCHEMA ms_pedidos;
CREATE SCHEMA ms_materiales;
CREATE SCHEMA ms_pagos;

create table ms_usuarios.customer (id_cliente  serial not null, cuit varchar(11) not null, fecha_baja timestamp, habilitado_online boolean default false not null, mail varchar(255) not null, max_cuenta_corriente float8, razon_social varchar(32) not null, id_usuario int4, primary key (id_cliente));
create table ms_usuarios.employee (id_empleado  serial not null, mail varchar(255) not null, nombre varchar(32) not null, id_usuario int4, primary key (id_empleado));
create table ms_usuarios.construction (id_obra  serial not null, descripcion varchar(128), direccion varchar(32) not null, latitud float4 not null, longitud float4 not null, superficie int4, id_cliente int4, id_tipo_obra int4, primary key (id_obra));
create table ms_usuarios.tipo_obra (id_tipo_obra  serial not null, descripcion varchar(255) not null, primary key (id_tipo_obra));
create table ms_usuarios.tipo_usuario (id_tipo_usuario  serial not null, tipo varchar(255) not null, primary key (id_tipo_usuario));
create table ms_usuarios.user (id_usuario  serial not null, clave varchar(32) not null, user varchar(20) not null, id_tipo_usuario int4, primary key (id_usuario));
alter table ms_usuarios.customer add constraint UK_as537scmvws7al2fgsv91u4aj unique (cuit);
alter table ms_usuarios.user add constraint UK_i02kr8ui5pqddyd7pkm3v4jbt unique (user);
alter table ms_usuarios.customer add constraint FKetx0tojxf5yevxcyt6qb526x5 foreign key (id_usuario) references ms_usuarios.user;
alter table ms_usuarios.employee add constraint FKt7vdal63o7rdoojoy7ywhjesh foreign key (id_usuario) references ms_usuarios.user;
alter table ms_usuarios.construction add constraint FKe6pqauh2107yp091rdhq78th1 foreign key (id_cliente) references ms_usuarios.customer;
alter table ms_usuarios.construction add constraint FKjtg36cfq5imcy9u57c677w4lt foreign key (id_tipo_obra) references ms_usuarios.tipo_obra;
alter table ms_usuarios.user add constraint FKr9xk0brid147iaydo8j47o9p2 foreign key (id_tipo_usuario) references ms_usuarios.tipo_usuario;

create table ms_materiales.material (id_material  serial not null, descripcion varchar(64), nombre varchar(32) not null, precio float8 not null, stock_actual int4 not null, stock_minimo int4 not null, id_unidad int4, primary key (id_material));
create table ms_materiales.unidad (id_unidad  serial not null, descripcion varchar(255) not null, primary key (id_unidad));

create table ms_pedidos.detalle_pedido (id_detalle_pedido  serial not null, cantidad int4 not null, precio float8 not null, id_producto int4, id_pedido int4, primary key (id_detalle_pedido));
create table ms_pedidos.estado_pedido (id_estado_pedido  serial not null, estado varchar(255) not null, primary key (id_estado_pedido));
create table ms_pedidos.pedido (id_pedido  serial not null, fecha timestamp not null, id_estado_pedido int4, id_obra int4, primary key (id_pedido));
alter table ms_pedidos.estado_pedido add constraint UK_t8g811wyvwpx5ey28ykgjvbw3 unique (estado);
alter table ms_usuarios.construction add constraint UK_3wa1bw3rpmbkbc5evlt9d2pc3 unique (descripcion);
alter table ms_pedidos.detalle_pedido add constraint FK3fy5h1ld134b2nj2v1fetd0w6 foreign key (id_producto) references ms_materiales.material;
alter table ms_pedidos.detalle_pedido add constraint FK7n9hdifr08joboojejveby1vr foreign key (id_pedido) references ms_pedidos.pedido;
alter table ms_pedidos.pedido add constraint FKpwts0xmsajvn0pjcukb14hpih foreign key (id_estado_pedido) references ms_pedidos.estado_pedido;
alter table ms_pedidos.pedido add constraint FKmtnlpv8yx33wfgetcgeuatap0 foreign key (id_obra) references ms_usuarios.construction;

create table ms_materiales.detalle_provision (id_detalle_provision  serial not null, cantidad int4 not null, id_material int4, id_provision int4, primary key (id_detalle_provision));
create table ms_materiales.movimiento_stock (id_movimiento_stock  serial not null, cantidad_entrada int4, cantidad_salida int4, fecha timestamp not null, id_detalle_pedido int4, id_detalle_provision int4, id_material int4, primary key (id_movimiento_stock));
create table ms_materiales.provision (id_provision  serial not null, fecha_provision timestamp, primary key (id_provision));
alter table ms_materiales.detalle_provision add constraint FKce9pc6uglcdgfg98lyl24uxcx foreign key (id_material) references ms_materiales.material;
alter table ms_materiales.detalle_provision add constraint FKd6expep8fs2dj77dy8ydxy3t9 foreign key (id_provision) references ms_materiales.provision;
alter table ms_materiales.material add constraint FKo8g938bng4o86c6mu9h6d88rs foreign key (id_unidad) references ms_materiales.unidad;
alter table ms_materiales.movimiento_stock add constraint FK71jtr34n08xogo05tqandnufw foreign key (id_detalle_pedido) references ms_pedidos.detalle_pedido;
alter table ms_materiales.movimiento_stock add constraint FKn1hw4lrjryc0asb5c0agf698r foreign key (id_detalle_provision) references ms_materiales.detalle_provision;
alter table ms_materiales.movimiento_stock add constraint FK6h2qerhiq00akyixo4mlelc6q foreign key (id_material) references ms_materiales.material;

create table ms_pagos.cheque (banco varchar(32) not null, fecha_cobro timestamp, numero int4 not null, id_medio_pago int4 not null, primary key (id_medio_pago));
create table ms_pagos.efectivo (nro_recibo int4 not null, id_medio_pago int4 not null, primary key (id_medio_pago));
create table ms_pagos.medio_pago (id_medio_pago  serial not null, observacion varchar(128), primary key (id_medio_pago));
create table ms_pagos.pago (id_pago  serial not null, fecha timestamp, id_cliente int4, id_medio_pago int4, primary key (id_pago));
create table ms_pagos.transferencia (cbu_destino varchar(22) not null, cbu_origen varchar(22) not null, codigo int8 not null, id_medio_pago int4 not null, primary key (id_medio_pago));
alter table ms_pagos.cheque add constraint UK_gj1823wy0nawq3hdyr0slmvui unique (numero);
alter table ms_pagos.efectivo add constraint UK_jkadd269ddlvmvvtc02qfvpga unique (nro_recibo);
alter table ms_pagos.transferencia add constraint UK_s29qomaohbvh94yoog8uqoynv unique (codigo);
alter table ms_pagos.cheque add constraint FKinvj1500npssdnrolfgb52rim foreign key (id_medio_pago) references ms_pagos.medio_pago;
alter table ms_pagos.efectivo add constraint FKrv9amm1ujwxbxqnmtb73op11g foreign key (id_medio_pago) references ms_pagos.medio_pago;
alter table ms_pagos.pago add constraint FK2fmwlqws6jmrycdhkn2bl8m0p foreign key (id_cliente) references ms_usuarios.customer;
alter table ms_pagos.pago add constraint FKenb53msdugorbaoaiabvisg99 foreign key (id_medio_pago) references ms_pagos.medio_pago;
alter table ms_pagos.transferencia add constraint FK774ufj0m3r80egh7rkls4i4sr foreign key (id_medio_pago) references ms_pagos.medio_pago;

INSERT INTO MS_USUARIOS.TIPO_USUARIO (tipo) VALUES 
	('Customer'),
	('Vendedor');

INSERT INTO MS_USUARIOS.TIPO_OBRA (descripcion) VALUES 
	('Reforma'),
	('Casa'),
	('Edificio'),
	('Vial');

INSERT into ms_pedidos.estado_pedido (estado) VALUES 
	('Nuevo'),
	('Confirmado'),
	('Pendiente'),
	('Cancelado'),
	('Aceptado'),
	('Rechazado'),
	('En preparacion'),
	('Entregado');

INSERT INTO ms_materiales.unidad (descripcion) VALUES 
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



















