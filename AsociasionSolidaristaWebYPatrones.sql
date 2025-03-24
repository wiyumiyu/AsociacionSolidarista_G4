CREATE SCHEMA asociacionsolidarista;
USE asociacionsolidarista;

CREATE USER 'asociacion'@'%' IDENTIFIED BY 'asociacion123';

GRANT ALL PRIVILEGES ON asociacionsolidarista.* TO 'asociacion'@'%';
FLUSH PRIVILEGES;

CREATE TABLE usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    contrasena VARCHAR(255) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    genero VARCHAR(10) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    username VARCHAR(100) NOT NULL,    
    cedula VARCHAR(50) UNIQUE NOT NULL,    
	fecha_nacimiento DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL
);
#drop table usuario;
#drop table rol;
#drop table ahorro;
#drop table aporteahorro;
#drop table retiro;
#drop table beneficiario;
#drop table usuario_detalle;
#drop table credito;
#drop table aportecredito;

insert INTO usuario
(contrasena, nombre, direccion, genero, telefono, username, cedula, fecha_nacimiento) values 
("$2a$10$P1.w58XvnaYQUQgZUCk4aO/RTRl8EValluCqB3S2VMLTbRt.tlre.", "Mariana T.", "", "Femenino", "88223366", "mariana@gmail.com" , "101110222","2000-01-01");
insert INTO usuario
(contrasena, nombre, direccion, genero, telefono, username, cedula, fecha_nacimiento) values 
("$2a$10$P1.w58XvnaYQUQgZUCk4aO/RTRl8EValluCqB3S2VMLTbRt.tlre.", "Julio Rodríguez", "", "Masculino", "88223366", "vjuliorc@gmail.com" , "1111111","2000-01-01");

insert INTO usuario
(contrasena, nombre, direccion, genero, telefono, username, cedula, fecha_nacimiento) values 
("$2a$10$P1.w58XvnaYQUQgZUCk4aO/RTRl8EValluCqB3S2VMLTbRt.tlre.", "Isaac Ruiz", "Cartago", "Masculino", "88222266", "isaac@gmail.com" , "3333333","2004-03-02");

CREATE TABLE `rol` (
  `id_rol` int PRIMARY KEY AUTO_INCREMENT,
  `nombre` varchar(20) DEFAULT NULL,
  `id_usuario` int DEFAULT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
	deleted_at TIMESTAMP NULL,  
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)  
  
) ;

insert into rol (nombre, id_usuario) values ("ROLE_ADMIN", 1);
insert into rol (nombre, id_usuario) values ("ROLE_ADMIN", 2);
insert into rol (nombre, id_usuario) values ("ROLE_CLIENT", 3);


/*Detalles del usuario que no son administradores */
CREATE TABLE usuario_detalle (
    id_usuario_detalle INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_usuario INT UNIQUE NOT NULL,
    estado_civil INT NOT NULL,
    profesion VARCHAR(100) NOT NULL,
    salario_bruto DOUBLE NOT NULL,
    empresa VARCHAR(100) NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,   
	FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)    
);

CREATE TABLE credito (
    id_credito INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_usuario INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    monto_solicitado DOUBLE NOT NULL,
    plazo INT NOT NULL,
    monto_actual DOUBLE NOT NULL,
    cuota DOUBLE NOT NULL,
    tasa DOUBLE NOT NULL,
    estado INT NOT NULL, 
    fecha_aprobacion DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE aportecredito (
    id_aporte INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_credito INT NOT NULL,
    monto DOUBLE NOT NULL,
    fecha DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,
    FOREIGN KEY (id_credito) REFERENCES credito(id_credito)
);

CREATE TABLE ahorro (
    id_ahorro INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_usuario INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    saldo_actual DOUBLE NOT NULL,
    cuota DOUBLE NOT NULL,
    interes_generado DOUBLE NOT NULL,
    tasa_interes DOUBLE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

insert into ahorro 
(id_usuario, nombre, saldo_actual, cuota, interes_generado, tasa_interes) values
(1, "prueba", 1, 2,3, 4 );


CREATE TABLE retiro (
    id_retiro INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_ahorro INT NOT NULL,
    monto DOUBLE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,
    FOREIGN KEY (id_ahorro) REFERENCES ahorro(id_ahorro)
);

CREATE TABLE aporteahorro (
    id_aporte INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_ahorro INT NOT NULL,
    monto DOUBLE NOT NULL, 
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,
    FOREIGN KEY (id_ahorro) REFERENCES ahorro(id_ahorro)
);

CREATE TABLE beneficiario (
    id_beneficiario INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_usuario INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    cedula VARCHAR(50) NOT NULL,
    parentesco VARCHAR(100) NOT NULL,
    porcentaje_beneficiario DOUBLE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

INSERT INTO credito (id_usuario, nombre, monto_solicitado, plazo, monto_actual, cuota, tasa, estado, fecha_aprobacion)
VALUES
    (1, 'Crédito Hipotecario', 150000.00, 240, 148500.00, 625.50, 5.5, 1, '2023-08-15'),
    (1, 'Crédito de Consumo', 3000.00, 18, 2900.00, 180.00, 14.0, 1, '2023-09-25'),
    (2, 'Crédito Personal', 5000.00, 24, 4800.00, 220.83, 12.0, 1, '2023-09-10'),
    (2, 'Crédito Estudiantil', 8000.00, 36, 7800.00, 250.00, 7.5, 1, '2023-10-18');
