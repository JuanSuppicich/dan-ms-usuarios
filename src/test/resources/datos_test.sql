INSERT INTO MS_USUARIOS.TIPO_USUARIO (tipo) VALUES 
    ('Cliente'),
    ('Vendedor');

INSERT INTO MS_USUARIOS.TIPO_OBRA (descripcion) VALUES 
    ('Reforma'),
    ('Casa'),
    ('Edificio'),
    ('Vial');

INSERT INTO MS_USUARIOS.USUARIO (usuario, clave, id_tipo_usuario) VALUES
    ('usuario1', 'clave1', 1),
    ('usuario2', 'clave2', 1),
    ('usuario3', 'clave3', 2),
    ('usuario4', 'clave4', 2);

INSERT INTO MS_USUARIOS.CLIENTE (razon_social, cuit, mail, max_cuenta_corriente, id_usuario) VALUES
    ('cliente1', '1111', 'cliente1@example.com', 1000.0, 1),
    ('cliente2', '2222', 'cliente2@example.com', 2000.0, 2);

INSERT INTO MS_USUARIOS.EMPLEADO (mail, nombre, id_usuario) VALUES
    ('empleado1@example.com', 'empleado1', 3),
    ('empleado2@example.com', 'empleado2', 4);

INSERT INTO MS_USUARIOS.OBRA (descripcion, latitud, longitud, direccion, superficie, id_tipo_obra, id_cliente) VALUES
    ('desc1', 1.00, 1.00, 'direc1', 10, 1, 1),
    ('desc2', 2.00, 2.00, 'direc2', 20, 2, 2),
    ('desc3', 3.00, 3.00, 'direc3', 30, 3, 1);