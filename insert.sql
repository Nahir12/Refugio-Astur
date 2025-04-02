INSERT INTO usuarios (nombre, email, contraseña, tipo_usuario) 
VALUES ('Administrador', 'administrador@gmail.com', 'admin', 'administrador');

INSERT INTO casas (direccion, ciudad, precio, descripcion)
VALUES ('Calle prueba', 'Prueba', 150, 'Esta casa es para hacer pruebas de db');

INSERT INTO caracteristicas (nombre, descripcion) 
VALUES ('Adaptada minusvalía', 'Casa equipada para personas con discapacidad');

INSERT INTO casa_caracteristicas (casa_id,caracteristica_id)
VALUES (1,1);

INSERT INTO alquileres (usuario_id,casa_id, fecha_inicio,fecha_fin)
VALUES (1,1,'2025-04-01', '2025-04-15');

imagenes