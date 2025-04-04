create database db_proy;
use db_proy;
-- Crear tabla Usuarios
CREATE TABLE usuarios (
    idUsuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    contraseña VARCHAR(100) NOT NULL,
    tipo_usuario ENUM('administrador', 'pago', 'normal') NOT NULL
    
);
ALTER TABLE usuarios
ADD COLUMN pago boolean default false;

-- Crear tabla Casas
CREATE TABLE casas (
    idCasa BIGINT AUTO_INCREMENT PRIMARY KEY,
    direccion VARCHAR(255) NOT NULL,
    ciudad VARCHAR(100) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    descripcion TEXT
);
ALTER TABLE casas
ADD COLUMN nombre VARCHAR(100) NOT NULL AFTER idCasa;

CREATE TABLE imagenes_casa(
    idImagenes BIGINT AUTO_INCREMENT PRIMARY KEY,
    casaId BIGINT,
    url_imagen VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255),
    fecha_subida DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (casaId) REFERENCES casas(idCasa) ON DELETE CASCADE
);
ALTER TABLE imagenes_casa DROP COLUMN fecha_subida;


-- Crear tabla Caracteristicas
CREATE TABLE caracteristicas (
    idCaracteristica BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(100) NOT NULL
);

-- Crear tabla intermedia Casa_Caracteristicas
CREATE TABLE casa_caracteristicas (
    casaId BIGINT,
    caracteristicaId BIGINT,
    PRIMARY KEY (casaId, caracteristicaId),
    FOREIGN KEY (casaId) REFERENCES casas(idCasa)ON DELETE CASCADE,
    FOREIGN KEY (caracteristicaId) REFERENCES caracteristicas(idCaracteristica)ON DELETE CASCADE
);
-- Crear tabla Alquileres
CREATE TABLE alquileres (
    idAlquiler BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuarioId BIGINT,
    casaId BIGINT,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    FOREIGN KEY (usuarioId) REFERENCES usuarios(idUsuario),
    FOREIGN KEY (casaId) REFERENCES casas(idCasa)
);
INSERT INTO imagenes_casa (casaId, url_imagen, descripcion)
VALUES (1, 'C:\Users\Alumno\Pictures\imagenesProyecto/salon.jpg', 'prueba');

