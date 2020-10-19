INSERT INTO `cliente` (`id_cliente`, `nombres`, `apellidos`, `correo`, `usuario`, `clave`, `estado`) VALUES
(0, 'Jonathan', 'Lopez', 'lopezlopez1760@gmail.com', 'jrlopez', '123', '1'),
(0, 'Cristian', 'Ramirez', '1768880@gmail.com', 'cris', '123', '1');

INSERT INTO `estado_reserva` (`id_estado_reserva`, `descripcion`) VALUES
(0, 'Reservada'),
(0, 'Disponible');

INSERT INTO `tipo_habitacion` (`id_tipo_habitacion`, `titulo`, `descripcion`, `precio`) VALUES
(0, 'Categoria A', 'Posee los lujos mas caros con vista panoramica al horizonte', '75.00'),
(0, 'Categoria B', 'Posee solamente dos camas y el tiempo es limitado', '50.00'),
(0, 'Categoria C', 'Posee solamente una camas y el tiempo es limitado', '40.00'),
(0, 'Categoria D', 'Posee solamente una camas y se aqluila solo por horas', '25.00');

INSERT INTO `promocion` (`id_promocion`, `nombre_promocion`, `descuento`, `fecha_inicio_prom`, `fecha_fin_prom`) VALUES
(0, 'Promocion Navideña', '10.00', '2020-12-01', '2020-12-05'),
(0, 'Promocion San Valentin', '10.00', '2020-02-01', '2020-02-14');

INSERT INTO `habitacion` (`id_habitacion`, `estado`, `tipo_habitacion`, `nombre_habitacion`) VALUES
(0, 1, 1, 'Habitacion 1'),
(0, 1, 1, 'Habitacion 2'),
(0, 1, 1, 'Habitacion 3'),
(0, 1, 1, 'Habitacion 4'),
(0, 1, 2, 'Habitacion 5'),
(0, 1, 2, 'Habitacion 6'),
(0, 1, 2, 'Habitacion 7'),
(0, 1, 2, 'Habitacion 8'),
(0, 1, 2, 'Habitacion 9'),
(0, 1, 3, 'Habitacion 10'),
(0, 1, 3, 'Habitacion 11'),
(0, 1, 3, 'Habitacion 12'),
(0, 1, 4, 'Habitacion 13'),
(0, 1, 4, 'Habitacion 14');

INSERT INTO `prom_hab` (`id_prom_hab`, `promocion`, `habitacion`) VALUES
(0, 1, 1),
(0, 2, 3);

INSERT INTO `servicio` (`id_servicio`, `titulo`, `descripcion`, `precio`) VALUES
(0, 'Acceso al Bar', 'Acceso a tomar todas las bebidas al bar', '20.00'),
(0, 'Entrada a Discoteca', 'Tiene acceso a la discoteca', '25.00'),
(0, 'Acceso a Piscina', 'Acceso a Piscina', '15.00');

INSERT INTO `reserva` (`id_reserva`, `fecha_inicio`, `fecha_fin`, `total`, `id_cliente`, `id_habitacion`, `id_promocion`, `id_estado`) VALUES
(0, '2020-09-08', '2020-09-30', NULL, 2, 1, 1, 1);

INSERT INTO `cuenta` (`id_cuenta`, `id_servicio`, `id_reserva`) VALUES
(0, 1, 1),
(0, 1, 1),
(0, 3, 1),
(0, 2, 1);


INSERT INTO `menu` (`id_menu`, `nombre`, `descripcion`) VALUES
(0, 'Almuerzo parrillero', 'Parrillada ');

INSERT INTO `menu_dia` (`id_menu_dia`, `fecha`, `menu`) VALUES
(0, '2020-09-08', 1);

INSERT INTO `rol` (`id_rol`, `nombre_rol`) VALUES
(0, 'Administrador'),
(0, 'Recepcionista'),
(0, 'Administrador'),
(0, 'Recepcionista'),
(0, 'Administrador'),
(0, 'Recepcionista');

INSERT INTO `personal` (`id_personal`, `nombres`, `apellidos`, `rol`) VALUES
(0, 'Erik', 'Ramirez', 1),
(0, 'Amilcar', 'Perez', 2);

INSERT INTO `usuario_empleado` (`id_usuario_empleado`, `usuario`, `clave`, `personal`) VALUES
(0, 'rml', '123',1),
(0, 'amilcar', '123',2);