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

insert ignore into Alumnos (nombre, apellido, carnet, correo, genero, fecha_nacimiento, contraseña)
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
