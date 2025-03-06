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
    email VARCHAR(100) NOT NULL,    
    cedula VARCHAR(50) UNIQUE NOT NULL,    
	fecha_nacimiento DATE NOT NULL,
    tipo_usuario tinyint(1) NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    deletedAt TIMESTAMP NULL
);

insert INTO usuario
(contrasena, nombre, direccion, genero, telefono, email, cedula, fecha_nacimiento, tipo_usuario) values 
("m", "Mariana T.", "", "Femenino", "88223366", "mariana@gmail.com" , "101110222","2000-01-01",  1);


/*Detalles del usuario que no son administradores */
CREATE TABLE usuario_detalle (
    id_usuario_detalle INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_usuario INT UNIQUE NOT NULL,
    estado_civil INT NOT NULL,
    profesion VARCHAR(100) NOT NULL,
    salario_bruto DOUBLE NOT NULL,
    empresa VARCHAR(100) NOT NULL,
	createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    deletedAt TIMESTAMP NULL,   
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
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    deletedAt TIMESTAMP NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE aportecredito (
    id_aporte INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_credito INT NOT NULL,
    monto DOUBLE NOT NULL,
    fecha DATE NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    deletedAt TIMESTAMP NULL,
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
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    deletedAt TIMESTAMP NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

insert into ahorro 
(id_usuario, nombre, saldo_actual, cuota, interes_generado, tasa_interes) values
(1, "prueba", 1, 2,3, 4 );


CREATE TABLE retiro (
    id_retiro INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_ahorro INT NOT NULL,
    monto DOUBLE NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    deletedAt TIMESTAMP NULL,
    FOREIGN KEY (id_ahorro) REFERENCES ahorro(id_ahorro)
);

CREATE TABLE aporteahorro (
    id_aporte INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_ahorro INT NOT NULL,
    monto DOUBLE NOT NULL, 
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    deletedAt TIMESTAMP NULL,
    FOREIGN KEY (id_ahorro) REFERENCES ahorro(id_ahorro)
);

CREATE TABLE beneficiario (
    id_beneficiario INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_usuario INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    cedula VARCHAR(50) NOT NULL,
    parentesco VARCHAR(100) NOT NULL,
    porcentaje_beneficiario DOUBLE NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    deletedAt TIMESTAMP NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

