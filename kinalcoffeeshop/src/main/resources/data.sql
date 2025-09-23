insert ignore into categoria_productos (nombre)
values ('Bebidas Calientes'),
       ('Bebidas Frias'),
       ('Postres'),
       ('Comidas Rapidas');

INSERT IGNORE INTO productos (nombre, descripcion, precio, cantidad, fecha_de_ingreso, fecha_de_expiracion, id_categoria)
VALUES
    ('Café Americano', 'Un café clásico con un sabor robusto y suave.', 2.50, 100, '2026-05-15', '2026-12-15', 1),
    ('Latte de Vainilla', 'Espresso con leche espumada y jarabe de vainilla.', 4.00, 80, '2026-05-15', '2026-12-15', 1),
    ('Té Chai Latte', 'Una mezcla especiada de té negro con leche espumada.', 3.75, 65, '2026-05-15', '2026-12-15', 1),
    ('Mocha', 'Café, chocolate y leche espumada.', 4.50, 70, '2026-05-15', '2026-12-15', 1),
    ('Espresso', 'Un shot concentrado de café.', 2.00, 120, '2026-05-15', '2026-12-15', 1),
    ('Frapuccino de Caramelo', 'Café frío, hielo y leche batidos con caramelo.', 5.50, 90, '2026-05-15', '2026-12-15', 2),
    ('Té Helado de Limón', 'Té negro refrescante con sabor a limón.', 3.00, 150, '2026-05-15', '2026-12-15', 2),
    ('Jugo de Naranja Natural', 'Jugo de naranja recién exprimido.', 4.25, 50, '2025-09-30', '2026-09-30', 2),
    ('Smoothie de Mango', 'Un batido cremoso de mango.', 6.00, 40, '2025-09-28', '2026-09-28', 2),
    ('Pastel de Chocolate', 'Un trozo rico y húmedo de pastel de chocolate.', 3.50, 30, '2026-05-15', '2026-11-15', 3),
    ('Brownie con Nuez', 'Brownie casero con trozos de nuez.', 2.75, 45, '2026-05-15', '2026-11-15', 3),
    ('Tarta de Queso', 'Tarta cremosa con base de galleta.', 4.00, 25, '2026-05-15', '2026-11-15', 3),
    ('Croissant de Jamón y Queso', 'Un croissant hojaldrado relleno de jamón y queso.', 5.00, 20, '2026-05-15', '2026-11-15', 4),
    ('Sándwich de Pavo', 'Un sándwich de pan integral con pavo, lechuga y tomate.', 6.50, 15, '2026-05-15', '2026-11-15', 4),
    ('Ensalada de Pollo a la Parrilla', 'Una ensalada fresca con pollo a la parrilla.', 7.00, 10, '2026-05-15', '2026-11-15', 4);

insert ignore into Alumnos (nombre, apellido, carnet, correo, genero, fecha_nacimiento, contrasena)
values
    ('Carlos', 'Ramírez', '2021158', 'cramirez-2021158@kinal.edu.gt', 'masculino', '2002-05-14', 'pass123'),
    ('José', 'Martínez', '2022199', 'jmartinez-2022199@kinal.edu.gt', 'masculino', '2001-07-20', 'pass123'),
    ('Luis', 'Hernández', '2023245', 'lhernandez-2023245@kinal.edu.gt', 'masculino', '2002-12-25', 'pass123'),
    ('Diego', 'Gómez', '2024210', 'dgomez-2024210@kinal.edu.gt', 'masculino', '2001-03-18', 'pass123'),
    ('Andrés', 'Díaz', '2021187', 'adiaz-2021187@kinal.edu.gt', 'masculino', '2002-09-11', 'pass123'),
    ('Javier', 'Torres', '2022264', 'jtorres-2022264@kinal.edu.gt', 'masculino', '2003-07-22', 'pass123'),
    ('Fernando', 'Castillo', '2023133', 'fcastillo-2023133@kinal.edu.gt', 'masculino', '2001-10-05', 'pass123'),
    ('Manuel', 'Reyes', '2024205', 'mreyes-2024205@kinal.edu.gt', 'masculino', '2004-04-29', 'pass123'),
    ('Alejandro', 'Morales', '2021242', 'amorales-2021242@kinal.edu.gt', 'masculino', '2002-02-17', 'pass123'),
    ('Sergio', 'López', '2023188', 'slopez-2023188@kinal.edu.gt', 'masculino', '2003-09-08', 'pass123');

INSERT IGNORE INTO usuarios_con_credito (nombre, apellido, telefono, correo, genero, fecha_nacimiento, contraseña)
VALUES
    ('Carlos', 'Gómez', '50212345678', 'carlos.gomez@correo.com', 'masculino', '1995-03-14', 'clave123'),
    ('María', 'López', '50287654321', 'maria.lopez@correo.com', 'femenino', '1998-07-22', 'pass456'),
    ('Juan', 'Martínez', '50255566677', 'juan.martinez@correo.com', 'masculino', '1990-01-10', 'contraseña789'),
    ('Ana', 'Hernández', '50211122233', 'ana.hernandez@correo.com', 'femenino', '2000-12-01', 'segura321'),
    ('Pedro', 'Ramírez', '50233344455', 'pedro.ramirez@correo.com', 'masculino', '1987-05-09', 'pass000'),
    ('Sofía', 'Díaz', '50277788899', 'sofia.diaz@correo.com', 'femenino', '1999-08-25', 'mypassword'),
    ('Diego', 'Fernández', 50277788895, 'diego.fernandez@correo.com', 'masculino', '1996-02-17', 'abc123'),
    ('Lucía', 'Castillo', '50222233344', 'lucia.castillo@correo.com', 'femenino', '2001-11-30', 'luciaPass'),
    ('Andrés', 'Morales', '50299900011', 'andres.morales@correo.com', 'masculino', '1994-07-12', 'keypass'),
    ('Valeria', 'Torres', '50244455566', 'valeria.torres@correo.com', 'femenino', '1997-04-06', 'valepass');

INSERT ignore INTO pedidos (estado, fecha, id_alumno, id_usuario_credito, total)
VALUES
    ('pendiente', '2025-09-01', 1, 1, 150.00),
    ('pagado', '2025-09-02', 2, 2, 275.50),
    ('enviado', '2025-09-03', 3, 3, 320.75),
    ('cancelado', '2025-09-04', 4, 4, 99.99),
    ('pendiente', '2025-09-05', 5, 5, 450.00),
    ('pagado', '2025-09-06', 6, 1, 210.25),
    ('enviado', '2025-09-07', 7, 2, 130.00),
    ('pagado', '2025-09-08', 8, 3, 600.00),
    ('pendiente', '2025-09-09', 9, 4, 75.50),
    ('enviado', '2025-09-10', 10, 5, 340.00);


INSERT ignore INTO pagos (fecha, id_pedido, monto, tipo)
VALUES
    ('2025-09-01', 1, 150.00, 'Efectivo'),
    ('2025-09-02', 2, 275.50, 'Tarjeta'),
    ('2025-09-03', 3, 320.75, 'Transferencia'),
    ('2025-09-04', 4, 99.99, 'Efectivo'),
    ('2025-09-05', 5, 450.00, 'Tarjeta'),
    ('2025-09-06', 1, 210.25, 'Transferencia'),
    ('2025-09-07', 2, 130.00, 'Efectivo'),
    ('2025-09-08', 3, 600.00, 'Tarjeta'),
    ('2025-09-09', 4, 75.50, 'Efectivo'),
    ('2025-09-10', 5, 340.00, 'Transferencia');

insert ignore into Facturas (fecha, total, id_pedido)
values
    ('2025-09-01 10:00:00', 150.00, 1),
    ('2025-09-02 11:30:00', 275.50, 2),
    ('2025-09-03 09:45:00', 320.75, 3),
    ('2025-09-04 14:20:00', 99.99, 4),
    ('2025-09-05 12:10:00', 450.00, 5),
    ('2025-09-06 16:05:00', 210.25, 6),
    ('2025-09-07 08:50:00', 130.00, 7),
    ('2025-09-08 13:15:00', 600.00, 8),
    ('2025-09-09 17:40:00', 75.50, 9),
    ('2025-09-10 15:25:00', 340.00, 10);

insert ignore into detalle_pedidos (cantidad, id_pedido, id_producto, subtotal)
values
    (2, 1, 3, 25.50),
    (1, 1, 5, 12.75),
    (3, 2, 2, 45.00),
    (2, 2, 6, 38.40),
    (1, 3, 1, 10.00),
    (4, 3, 4, 60.00),
    (2, 4, 7, 32.80),
    (1, 4, 8, 16.50),
    (3, 5, 9, 49.50),
    (2, 5, 10, 28.00);