create database CafeteriaDB;
use CafeteriaDB;

create table UsuariosConCredito(
    idUsuariosCredito int auto_increment,
    nombre varchar(64) not null,
    apellido varchar(64) not null,
    telefono varchar(16) not null,
    correo varchar(128) not null,
    genero enum ('masculino','femenino','no') not null,
    edad integer not null,
    codigoDeCliente varchar(32) unique not null,
    constraint pk_cliente primary key (idUsuariosCredito)
);

create table Productos(
    idProducto int auto_increment,
    nombre varchar(64) not null,
    descripcion text not null,
    precio decimal(10,2) not null,
    stock int not null,
    constraint pk_producto primary key (idProducto)
);

create table Pedidos(
    idPedido int auto_increment,
    fecha datetime default current_timestamp,
    total decimal(10,2) not null,
    estado enum('Pendiente','Preparando','Entregado','Cancelado') default 'Pendiente',
    idUsuariosCredito int,
    constraint fk_pedido_cliente foreign key (idUsuariosCredito)
        references UsuariosConCredito(idUsuariosCredito),
    constraint pk_pedido primary key (idPedido)
);

create table DetallePedidos(
    idDetalle int auto_increment,
    cantidad int not null,
    subtotal decimal(10,2) not null,
    idPedido int not null,
    idProducto int not null,
    constraint fk_detalle_pedido foreign key (idPedido)
        references Pedidos(idPedido),
    constraint fk_detalle_producto foreign key (idProducto)
        references Productos(idProducto),
    constraint pk_detalle primary key (idDetalle)
);

create table MetodosPago(
    idMetodo int auto_increment,
    tipo enum('Efectivo', 'Tarjeta', 'Crédito') not null,
    constraint pk_metodo primary key (idMetodo)
);

create table Pagos(
    idPago int auto_increment,
    monto decimal(10,2) not null,
    fecha datetime default current_timestamp,
    idPedido int not null,
    idMetodo int not null,
    constraint fk_pago_pedido foreign key (idPedido)
        references Pedidos(idPedido),
    constraint fk_pago_metodo foreign key (idMetodo)
        references MetodosPago(idMetodo),
    constraint pk_pago primary key (idPago)
);

create table CuentasDeCredito(
    idCuenta int auto_increment,
    limite decimal(10,2) not null,
    saldo decimal(10,2) not null,
    idUsuariosCredito int not null,
    constraint fk_cuenta_cliente foreign key (idUsuariosCredito)
        references UsuariosConCredito(idUsuariosCredito),
    constraint pk_cuenta primary key (idCuenta)
);