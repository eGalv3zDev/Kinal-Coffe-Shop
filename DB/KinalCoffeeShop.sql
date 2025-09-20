drop database if exists KinalCoffeeShop;
create database KinalCoffeeShop;
use KinalCoffeeShop;

create table Alumnos(
    idAlumno int auto_increment,
    nombre varchar(64) not null,
    apellido varchar(64) not null,
    carnet varchar(16) unique not null,
    correo varchar(128),
    genero enum('masculino','femenino','no') not null,
    fechaNacimiento date,
    contraseña varchar(128) not null,
    constraint pk_alumno primary key (idAlumno)
);

create table UsuariosConCredito(
    idUsuarioCredito int auto_increment,
    nombre varchar(64) not null,
    apellido varchar(64) not null,
    telefono varchar(16),
    correo varchar(128) unique,
    genero enum('masculino','femenino','no') not null,
    fechaNacimiento date,
    contraseña varchar(128) not null,
    constraint pk_usuario_credito primary key (idUsuarioCredito)
);

create table CategoriaProductos(
    idCategoria int auto_increment,
    nombre varchar(64) not null,
    constraint pk_categoria primary key (idCategoria)
);

create table Alumnos(
    idAlumno int auto_increment,
    nombre varchar(64) not null,
    apellido varchar(64) not null,
    carnet varchar(8) unique not null,      
    constraint pk_alumno primary key (idAlumno)
);

create table Productos(
    idProducto int auto_increment,
    nombre varchar(64) not null,
    descripcion text,
    precio decimal(10,2) not null,
    stock int not null,
    fechaDeIngreso datetime default current_timestamp,
    fechaDeExpiracion date,
    idCategoria int not null,
    constraint pk_producto primary key (idProducto),
    constraint fk_producto_categoria foreign key (idCategoria)
        references CategoriaProductos(idCategoria)
);

create table Pedidos(
    idPedido int auto_increment,
    fecha datetime default current_timestamp,
    total decimal(10,2) not null,
    estado enum('Pendiente','Preparando','Entregado','Cancelado') default 'Pendiente',
    idUsuariosCredito int,
    constraint fk_pedido_usuariosConCredito foreign key (idUsuariosCredito)
        references UsuariosConCredito(idUsuariosCredito),
    constraint fk_pedido_alumno foreign key (idAlumno)
        references Alumnos(idAlumno),
    constraint pk_pedido primary key (idPedido)
    idAlumno int,
    idUsuarioCredito int,
    constraint pk_pedido primary key (idPedido),
    constraint fk_pedido_alumno foreign key (idAlumno)
        references Alumnos(idAlumno),
    constraint fk_pedido_usuario_credito foreign key (idUsuarioCredito)
        references UsuariosConCredito(idUsuarioCredito)
);

create table DetallePedidos(
    idDetalle int auto_increment,
    cantidad int not null,
    subtotal decimal(10,2) not null,
    idPedido int not null,
    idProducto int not null,
    constraint pk_detalle primary key (idDetalle),
    constraint fk_detalle_pedido foreign key (idPedido)
        references Pedidos(idPedido),
    constraint fk_detalle_producto foreign key (idProducto)
        references Productos(idProducto)
);

create table Pagos(
    idPago int auto_increment,
    monto decimal(10,2) not null,
    fecha datetime default current_timestamp,
    tipo enum('Efectivo', 'Tarjeta') not null,
    idPedido int not null,
    constraint pk_pago primary key (idPago),
    constraint fk_pago_pedido foreign key (idPedido)
        references Pedidos(idPedido)
);

create table CuentasDeCredito(
    idCuenta int auto_increment,
    saldo decimal(10,2) not null default 0,
    idUsuarioCredito int not null,
    constraint pk_cuenta primary key (idCuenta),
    constraint fk_cuenta_usuario foreign key (idUsuarioCredito)
        references UsuariosConCredito(idUsuarioCredito)
);

create table LineasDeCredito(
    idConsumo int auto_increment,
    fecha datetime default current_timestamp,
    cantidad int not null,
    subtotal decimal(10,2) not null,
    idProducto int not null,
    idCuenta int not null,
    constraint pk_consumo primary key (idConsumo),
    constraint fk_consumo_producto foreign key (idProducto)
        references Productos(idProducto),
    constraint fk_consumo_cuenta foreign key (idCuenta)
        references CuentasDeCredito(idCuenta)
);

create table HistorialCreditos(
    idHistorial int auto_increment,
    idCuenta int not null,
    fecha datetime default current_timestamp,
    tipoMovimiento enum('Consumo','Pago') not null,
    monto decimal(10,2) not null,
    idConsumo int,
    idPago int,
    constraint pk_historial primary key (idHistorial),
    constraint fk_historial_cuenta foreign key (idCuenta)
        references CuentasDeCredito(idCuenta),
    constraint fk_historial_consumo foreign key (idConsumo)
        references LineasDeCredito(idConsumo),
    constraint fk_historial_pago foreign key (idPago)
        references Pagos(idPago)
);

create table Facturas(
    idFactura int auto_increment,
    fecha datetime default current_timestamp,
    total decimal(10,2) not null,
    idPedido int not null,
    constraint pk_factura primary key (idFactura),
    constraint fk_factura_pedido foreign key (idPedido)
        references Pedidos(idPedido)
);

create table DetalleFacturas(
    idDetalleFactura int auto_increment,
    cantidad int not null,
    subtotal decimal(10,2) not null,
    idFactura int not null,
    idProducto int not null,
    constraint pk_detalle_factura primary key (idDetalleFactura),
    constraint fk_detalle_factura_factura foreign key (idFactura)
        references Facturas(idFactura),
    constraint fk_detalle_factura_producto foreign key (idProducto)
        references Productos(idProducto)
);