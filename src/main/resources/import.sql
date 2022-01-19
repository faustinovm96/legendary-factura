/* Populate tables */
INSERT INTO clientes (cli_nombres, cli_apellidos, cli_direccion, cli_email, fecha_creado, foto) VALUES('Andres', 'Guzman', '', 'profesor@bolsadeideas.com', '2017-08-01', '');
INSERT INTO clientes (cli_nombres, cli_apellidos, cli_direccion, cli_email, fecha_creado, foto) VALUES('John', 'Doe', '', 'john.doe@gmail.com', '2017-08-02', '');
INSERT INTO clientes (cli_nombres, cli_apellidos, cli_direccion, cli_email, fecha_creado, foto) VALUES('Linus', 'Torvalds', '', 'linus.torvalds@gmail.com', '2017-08-03', '');
INSERT INTO clientes (cli_nombres, cli_apellidos, cli_direccion, cli_email, fecha_creado, foto) VALUES('Jane', 'Doe', '', 'jane.doe@gmail.com', '2017-08-04', '');
INSERT INTO clientes (cli_nombres, cli_apellidos, cli_direccion, cli_email, fecha_creado, foto) VALUES('Rasmus', 'Lerdorf', '', 'rasmus.lerdorf@gmail.com', '2017-08-05', '');

/* Populate tabla productos */
INSERT INTO productos (prod_nombre, prod_precio, create_at) VALUES('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO productos (prod_nombre, prod_precio, create_at) VALUES('Sony Camara digital DSC-W320B', 123490, NOW());
INSERT INTO productos (prod_nombre, prod_precio, create_at) VALUES('Apple iPod shuffle', 1499990, NOW());
INSERT INTO productos (prod_nombre, prod_precio, create_at) VALUES('Sony Notebook Z110', 37990, NOW());
INSERT INTO productos (prod_nombre, prod_precio, create_at) VALUES('Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO productos (prod_nombre, prod_precio, create_at) VALUES('Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO productos (prod_nombre, prod_precio, create_at) VALUES('Mica Comoda 5 Cajones', 299990, NOW());

/* Creamos algunas facturas */
INSERT INTO facturas (fac_descripcion, fac_observacion, cliente_id, create_at) VALUES('Factura equipos de oficina', null, 1, NOW());
INSERT INTO detalles_facturas (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO detalles_facturas (cantidad, factura_id, producto_id) VALUES(2, 1, 4);
INSERT INTO detalles_facturas (cantidad, factura_id, producto_id) VALUES(1, 1, 5);
INSERT INTO detalles_facturas (cantidad, factura_id, producto_id) VALUES(1, 1, 7);

INSERT INTO facturas (fac_descripcion, fac_observacion, cliente_id, create_at) VALUES('Factura Bicicleta', 'Alguna nota importante!', 1, NOW());
INSERT INTO detalles_facturas (cantidad, factura_id, producto_id) VALUES(3, 2, 6);