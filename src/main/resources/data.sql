INSERT IGNORE INTO categoria_productos (nombre)
VALUES ('Bebidas Calientes'),
       ('Bebidas Frias'),
       ('Postres'),
       ('Comidas Rapidas');

INSERT IGNORE INTO productos (nombre, descripcion, precio, stock, fecha_de_ingreso, fecha_de_expiracion, id_categoria)
VALUES
    ('Café Americano', 'Un café clásico con un sabor robusto y suave.', 2.50, 100, NOW(), '2026-12-15', 1),
    ('Latte de Vainilla', 'Espresso con leche espumada y jarabe de vainilla.', 4.00, 80, NOW(), '2026-12-15', 1),
    ('Té Chai Latte', 'Una mezcla especiada de té negro con leche espumada.', 3.75, 65, NOW(), '2026-12-15', 1),
    ('Mocha', 'Café, chocolate y leche espumada.', 4.50, 70, NOW(), '2026-12-15', 1),
    ('Espresso', 'Un shot concentrado de café.', 2.00, 120, NOW(), '2026-12-15', 1),
    ('Frapuccino de Caramelo', 'Café frío, hielo y leche batidos con caramelo.', 5.50, 90, NOW(), '2026-12-15', 2),
    ('Té Helado de Limón', 'Té negro refrescante con sabor a limón.', 3.00, 150, NOW(), '2026-12-15', 2),
    ('Jugo de Naranja Natural', 'Jugo de naranja recién exprimido.', 4.25, 50, NOW(), '2025-09-30', 2),
    ('Smoothie de Mango', 'Un batido cremoso de mango.', 6.00, 40, NOW(), '2025-09-28', 2),
    ('Pastel de Chocolate', 'Un trozo rico y húmedo de pastel de chocolate.', 3.50, 30, NOW(), '2025-11-15', 3),
    ('Brownie con Nuez', 'Brownie casero con trozos de nuez.', 2.75, 45, NOW(), '2025-11-15', 3),
    ('Tarta de Queso', 'Tarta cremosa con base de galleta.', 4.00, 25, NOW(), '2025-11-15', 3),
    ('Croissant de Jamón y Queso', 'Un croissant hojaldrado relleno de jamón y queso.', 5.00, 20, NOW(), '2025-10-15', 4),
    ('Sándwich de Pavo', 'Un sándwich de pan integral con pavo, lechuga y tomate.', 6.50, 15, NOW(), '2025-10-15', 4),
    ('Ensalada de Pollo a la Parrilla', 'Una ensalada fresca con pollo a la parrilla.', 7.00, 10, NOW(), '2025-10-15', 4);

INSERT IGNORE INTO alumnos (nombre, apellido, carnet, correo, genero, fecha_nacimiento, contrasena)
VALUES
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

INSERT IGNORE INTO usuarios_con_credito (nombre, apellido, telefono, correo, genero, fecha_nacimiento, contrasena)
VALUES
    ('Carlos', 'Gómez', '50212345678', 'carlos.gomez@correo.com', 'masculino', '1995-03-14', 'clave123'),
    ('María', 'López', '50287654321', 'maria.lopez@correo.com', 'femenino', '1998-07-22', 'pass456'),
    ('Juan', 'Martínez', '50255566677', 'juan.martinez@correo.com', 'masculino', '1990-01-10', 'contrasena789'),
    ('Ana', 'Hernández', '50211122233', 'ana.hernandez@correo.com', 'femenino', '2000-12-01', 'segura321'),
    ('Pedro', 'Ramírez', '50233344455', 'pedro.ramirez@correo.com', 'masculino', '1987-05-09', 'pass000'),
    ('Sofía', 'Díaz', '50277788899', 'sofia.diaz@correo.com', 'femenino', '1999-08-25', 'mypassword'),
    ('Diego', 'Fernández', '50277788895', 'diego.fernandez@correo.com', 'masculino', '1996-02-17', 'abc123'),
    ('Lucía', 'Castillo', '50222233344', 'lucia.castillo@correo.com', 'femenino', '2001-11-30', 'luciaPass'),
    ('Andrés', 'Morales', '50299900011', 'andres.morales@correo.com', 'masculino', '1994-07-12', 'keypass'),
    ('Valeria', 'Torres', '50244455566', 'valeria.torres@correo.com', 'femenino', '1997-04-06', 'valepass');

INSERT IGNORE INTO pedidos (estado, fecha, id_alumno, id_usuario_credito, total)
VALUES
    ('Pendiente', '2025-09-01', 1, NULL, 15.25),
    ('Entregado', '2025-09-02', 2, NULL, 7.50),
    ('Entregado', '2025-09-03', NULL, 1, 10.50),
    ('Cancelado', '2025-09-04', 4, NULL, 9.50),
    ('Pendiente', '2025-09-05', NULL, 2, 6.50),
    ('Entregado', '2025-09-06', 6, NULL, 8.00),
    ('Entregado', '2025-09-07', NULL, 3, 12.00),
    ('Entregado', '2025-09-08', 8, NULL, 5.50),
    ('Pendiente', '2025-09-09', NULL, 4, 11.25),
    ('Entregado', '2025-09-10', 10, NULL, 7.00);

INSERT IGNORE INTO pagos (fecha, id_pedido, monto, tipo)
VALUES
    ('2025-09-02', 2, 7.50, 'Efectivo'),
    ('2025-09-03', 3, 10.50, 'Tarjeta'),
    ('2025-09-06', 6, 8.00, 'Tarjeta'),
    ('2025-09-07', 7, 12.00, 'Efectivo'),
    ('2025-09-08', 8, 5.50, 'Tarjeta'),
    ('2025-09-10', 10, 7.00, 'Efectivo');

INSERT IGNORE INTO facturas (fecha, total, id_pedido)
VALUES
    ('2025-09-02 11:30:00', 7.50, 2),
    ('2025-09-03 09:45:00', 10.50, 3),
    ('2025-09-06 16:05:00', 8.00, 6),
    ('2025-09-07 08:50:00', 12.00, 7),
    ('2025-09-08 13:15:00', 5.50, 8),
    ('2025-09-10 15:25:00', 7.00, 10);

INSERT IGNORE INTO detalle_pedidos (cantidad, id_pedido, id_producto, subtotal)
VALUES
    (1, 1, 1, 2.50),  -- Pedido 1
    (1, 1, 14, 6.50), -- Pedido 1
    (1, 1, 11, 2.75), -- Pedido 1
    (2, 2, 5, 4.00),  -- Pedido 2
    (1, 2, 10, 3.50), -- Pedido 2
    (3, 3, 7, 9.00),  -- Pedido 3
    (1, 3, 12, 4.00), -- Pedido 3
    (1, 4, 2, 4.00),  -- Pedido 4 (Cancelado)
    (1, 5, 8, 4.25),  -- Pedido 5
    (1, 6, 4, 4.50),  -- Pedido 6
    (1, 7, 6, 5.50),  -- Pedido 7
    (1, 8, 9, 6.00),  -- Pedido 8
    (2, 9, 13, 10.00),-- Pedido 9
    (1, 10, 15, 7.00);-- Pedido 10

INSERT IGNORE INTO detalle_facturas (cantidad, id_factura, id_producto, subtotal)
SELECT dp.cantidad, f.id_factura, dp.id_producto, dp.subtotal
FROM detalle_pedidos dp
         JOIN facturas f ON dp.id_pedido = f.id_pedido;