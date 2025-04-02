create database db_proy;
use db_proy;
-- Crear tabla Usuarios
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    contraseña VARCHAR(100) NOT NULL,
    tipo_usuario ENUM('administrador', 'pago', 'normal') NOT NULL
);

-- Crear tabla Casas
CREATE TABLE casas (
    id_casa INT AUTO_INCREMENT PRIMARY KEY,
    direccion VARCHAR(255) NOT NULL,
    ciudad VARCHAR(100) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    descripcion TEXT
);

-- Crear tabla Caracteristicas
CREATE TABLE caracteristicas (
    id_caracteristica INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(100) NOT NULL
);

-- Crear tabla intermedia Casa_Caracteristicas
CREATE TABLE casa_caracteristicas (
    id_casa INT,
    id_caracteristica INT,
    PRIMARY KEY (id_casa, id_caracteristica),
    FOREIGN KEY (id_casa) REFERENCES casas(id_casa),
    FOREIGN KEY (id_caracteristica) REFERENCES caracteristicas(id_caracteristica)
);

-- Crear tabla Alquileres
CREATE TABLE alquileres (
    id_alquiler INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    id_casa INT,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (id_casa) REFERENCES casas(id_casa)
);
