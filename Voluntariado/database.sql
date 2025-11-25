-- Schema for the Volunteering Platform Database

-- Users table
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    contraseña VARCHAR(255) NOT NULL,
    rol ENUM('voluntario', 'coordinador', 'administrador') NOT NULL DEFAULT 'voluntario',
    ciudad VARCHAR(255),
    intereses TEXT, -- JSON array as string
    telefono VARCHAR(50),
    tipo_documento VARCHAR(50),
    numero_documento VARCHAR(100),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado ENUM('activo', 'inactivo') DEFAULT 'activo',
    actividades_realizadas INT DEFAULT 0,
    horas_acumuladas INT DEFAULT 0
);

-- Activities table
CREATE TABLE activities (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descripcion TEXT,
    fecha DATE NOT NULL,
    ubicacion VARCHAR(255),
    cupos INT NOT NULL,
    cupos_disponibles INT NOT NULL,
    tipo ENUM('Virtual', 'Presencial') DEFAULT 'Presencial',
    categoria VARCHAR(100),
    duracion VARCHAR(50),
    estado ENUM('Borrador', 'Convocatoria', 'En ejecución', 'Finalizada', 'Cancelada') DEFAULT 'Borrador',
    coordinador_id INT REFERENCES users(id),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Certificates table
CREATE TABLE certificates (
    id SERIAL PRIMARY KEY,
    usuario_id INT REFERENCES users(id),
    actividad_id INT REFERENCES activities(id),
    titulo VARCHAR(255),
    horas INT,
    fecha_emision DATE,
    estado ENUM('Disponible', 'Descargado') DEFAULT 'Disponible'
);

-- Notifications table
CREATE TABLE notifications (
    id SERIAL PRIMARY KEY,
    usuario_id INT REFERENCES users(id),
    tipo ENUM('actividad_nueva', 'postulacion_aprobada', 'certificado_disponible', 'recordatorio'),
    titulo VARCHAR(255),
    mensaje TEXT,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    leida BOOLEAN DEFAULT FALSE
);

-- Postulantes table (applications)
CREATE TABLE postulantes (
    id SERIAL PRIMARY KEY,
    usuario_id INT REFERENCES users(id),
    actividad_id INT REFERENCES activities(id),
    estado ENUM('Pendiente', 'Aprobado', 'Rechazado') DEFAULT 'Pendiente',
    fecha_postulacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(usuario_id, actividad_id)
);

-- Indexes for better performance
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_activities_fecha ON activities(fecha);
CREATE INDEX idx_activities_estado ON activities(estado);
CREATE INDEX idx_notifications_usuario ON notifications(usuario_id);
CREATE INDEX idx_postulantes_actividad ON postulantes(actividad_id);

-- Sample data
INSERT INTO users (nombre, email, contraseña, rol, ciudad, intereses, fecha_registro, estado, actividades_realizadas, horas_acumuladas) VALUES
('Juan Pérez', 'juan@email.com', '$2b$10$AyEDqdg5tozOzy/td6aPKO5c1xxHD.bYp9l4sE9FYHFeAlHUJReha', 'voluntario', 'Ciudad de México', '["Educación", "Medio Ambiente"]', '2023-06-15 00:00:00', 'activo', 12, 48),
('María García', 'maria@email.com', '$2b$10$AyEDqdg5tozOzy/td6aPKO5c1xxHD.bYp9l4sE9FYHFeAlHUJReha', 'coordinador', 'Bogotá', '[]', '2023-04-20 00:00:00', 'activo', 25, 0),
('Carlos López', 'carlos@email.com', '$2b$10$AyEDqdg5tozOzy/td6aPKO5c1xxHD.bYp9l4sE9FYHFeAlHUJReha', 'administrador', 'Lima', '[]', '2023-01-10 00:00:00', 'activo', 0, 0);

INSERT INTO activities (titulo, descripcion, fecha, ubicacion, cupos, cupos_disponibles, tipo, categoria, duracion, estado, coordinador_id, fecha_creacion) VALUES
('Limpieza de Playas en Cartagena', 'Únete a nosotros para limpiar las playas de Cartagena y proteger la vida marina. Proporcionaremos todos los materiales necesarios.', '2025-03-15', 'Cartagena', 30, 15, 'Presencial', 'Medio Ambiente', '4 horas', 'Convocatoria', 2, '2025-01-01 00:00:00'),
('Taller de Lectura para Niños en Bogotá', 'Ayuda a fomentar el amor por la lectura en niños de 6 a 10 años en bibliotecas de Bogotá.', '2025-04-20', 'Bogotá', 20, 8, 'Presencial', 'Educación', '3 horas', 'Convocatoria', 2, '2025-01-02 00:00:00'),
('Reforestación en Medellín', 'Participa en la plantación de árboles en parques de Medellín para mejorar la calidad del aire.', '2025-05-10', 'Medellín', 25, 10, 'Presencial', 'Medio Ambiente', '5 horas', 'Convocatoria', 2, '2025-01-03 00:00:00'),
('Apoyo Escolar Virtual', 'Ayuda a estudiantes de secundaria con sus tareas escolares a través de sesiones virtuales.', '2025-06-05', 'Virtual', 15, 12, 'Virtual', 'Educación', '2 horas', 'Convocatoria', 2, '2025-01-04 00:00:00'),
('Recolección de Alimentos en Cali', 'Únete a nuestra campaña de recolección de alimentos no perecederos para familias en Cali.', '2025-07-12', 'Cali', 40, 25, 'Presencial', 'Ayuda Social', '3 horas', 'Convocatoria', 2, '2025-01-05 00:00:00'),
('Taller de Primeros Auxilios en Barranquilla', 'Aprende y enseña técnicas básicas de primeros auxilios en comunidades de Barranquilla.', '2025-08-18', 'Barranquilla', 20, 18, 'Presencial', 'Salud', '6 horas', 'Convocatoria', 2, '2025-01-06 00:00:00'),
('Cuidado de Animales en Pereira', 'Ayuda en refugios de animales de Pereira con alimentación y limpieza.', '2025-09-22', 'Pereira', 15, 10, 'Presencial', 'Animales', '4 horas', 'Convocatoria', 2, '2025-01-07 00:00:00'),
('Taller de Programación para Jóvenes', 'Enseña programación básica a jóvenes estudiantes en Bucaramanga.', '2025-10-15', 'Bucaramanga', 12, 8, 'Presencial', 'Tecnología', '3 horas', 'Convocatoria', 2, '2025-01-08 00:00:00'),
('Limpieza de Ríos en Bogotá', 'Participa en la limpieza de ríos y quebradas en la capital para preservar el medio ambiente.', '2026-01-20', 'Bogotá', 35, 20, 'Presencial', 'Medio Ambiente', '5 horas', 'Convocatoria', 2, '2025-01-09 00:00:00'),
('Mentoría Tecnológica Virtual', 'Ofrece mentoría a jóvenes estudiantes interesados en carreras tecnológicas.', '2026-02-14', 'Virtual', 10, 6, 'Virtual', 'Tecnología', '1.5 horas', 'Convocatoria', 2, '2025-01-10 00:00:00'),
('Campaña de Vacunación en Medellín', 'Ayuda en campañas de vacunación y educación sanitaria en barrios de Medellín.', '2026-03-08', 'Medellín', 25, 15, 'Presencial', 'Salud', '4 horas', 'Convocatoria', 2, '2025-01-11 00:00:00'),
('Adopción de Mascotas en Cali', 'Organiza eventos de adopción y educa sobre responsabilidad con mascotas en Cali.', '2026-04-12', 'Cali', 18, 12, 'Presencial', 'Animales', '3 horas', 'Convocatoria', 2, '2025-01-12 00:00:00'),
('Educación Ambiental en Cartagena', 'Realiza talleres de educación ambiental para niños en escuelas de Cartagena.', '2026-05-25', 'Cartagena', 22, 18, 'Presencial', 'Educación', '2.5 horas', 'Convocatoria', 2, '2025-01-13 00:00:00'),
('Ayuda a Ancianos en Barranquilla', 'Visita y brinda compañía a adultos mayores en residencias de Barranquilla.', '2026-06-10', 'Barranquilla', 20, 14, 'Presencial', 'Ayuda Social', '3 horas', 'Convocatoria', 2, '2025-01-14 00:00:00'),
('Desarrollo de Apps Móviles', 'Participa en proyectos de desarrollo de aplicaciones móviles para ONGs.', '2026-07-05', 'Virtual', 8, 5, 'Virtual', 'Tecnología', '4 horas', 'Convocatoria', 2, '2025-01-15 00:00:00');