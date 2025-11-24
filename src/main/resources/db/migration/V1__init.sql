-- Crear tabla de artistas
CREATE TABLE IF NOT EXISTS artistas (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  apellido VARCHAR(100),
  nombre_artistico VARCHAR(100) NOT NULL UNIQUE,
  email VARCHAR(100) NOT NULL UNIQUE,
  telefono VARCHAR(15),
  genero_musical VARCHAR(50),
  pais VARCHAR(100),
  fecha_debut DATETIME,
  fecha_registro DATETIME,
  activo TINYINT(1) DEFAULT 1
);

-- Crear tabla de álbumes
CREATE TABLE IF NOT EXISTS albums (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  titulo VARCHAR(150) NOT NULL,
  fecha_lanzamiento DATE,
  genero VARCHAR(50),
  numero_canciones INT,
  duracion_total INT,
  precio DECIMAL(10,2),
  descripcion VARCHAR(500),
  url_portada VARCHAR(255),
  estado VARCHAR(20) NOT NULL DEFAULT 'ACTIVO',
  fecha_registro DATETIME,
  artista_id BIGINT NOT NULL,
  CONSTRAINT fk_albums_artista FOREIGN KEY (artista_id) REFERENCES artistas(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_albums_artista ON albums (artista_id);

-- Crear tabla de canciones
CREATE TABLE IF NOT EXISTS canciones (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  titulo VARCHAR(150) NOT NULL,
  duracion_segundos INT NOT NULL,
  numero_pista INT,
  genero VARCHAR(50),
  precio DECIMAL(10,2),
  numero_reproducciones BIGINT DEFAULT 0,
  letra VARCHAR(1000),
  url_archivo VARCHAR(255),
  estado VARCHAR(20) NOT NULL DEFAULT 'ACTIVA',
  fecha_grabacion DATETIME,
  fecha_registro DATETIME,
  artista_id BIGINT NOT NULL,
  album_id BIGINT,
  CONSTRAINT fk_canciones_artista FOREIGN KEY (artista_id) REFERENCES artistas(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_canciones_album FOREIGN KEY (album_id) REFERENCES albums(id)
    ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_canciones_artista ON canciones (artista_id);
CREATE INDEX IF NOT EXISTS idx_canciones_album ON canciones (album_id);

-- Crear tabla de usuarios
CREATE TABLE IF NOT EXISTS users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL
);

-- Crear tabla de roles de usuario
CREATE TABLE IF NOT EXISTS user_roles (
  user_id BIGINT NOT NULL,
  role VARCHAR(255) NOT NULL,
  PRIMARY KEY (user_id, role),
  CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES users(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);
