--insertar el juego de nombre Megaman, con descripción: robot humanoide pelea con el Dr. Wily, y género Plataforma
INSERT INTO juego (nombre, descripcion, nombre_genero_juego)
VALUES ('Megaman', 'robot humanoide pelea con el Dr. Wily', 'Plataforma');

--insertar el juego de nombre Contra, con descripción: soldados pelean en una base alienígena, y género FPS; con detalles de juego serial 555648, numero_jugadores: 2 y edad mínima de 6
INSERT INTO juego (nombre, descripcion, nombre_genero_juego)
VALUES ('Contra', 'soldados pelean en una base alienígena', 'FPS');
INSERT INTO detalles_juego (nombre, serial, numero_jugadores, edad_minima)
VALUES  ('Contra', '555648', 2, 6);

--insertar el juego de nombre LOL, con descripción: defensa de nexo en combate 5 contra 5, y género MOBA con detalles de juego serial 555333, numero_jugadores: 10 y edad mínima de 7
INSERT INTO genero_juego (nombre, descripcion) VALUES ('MOBA', 'defensa de nexo en combate 5 contra 5');

INSERT INTO juego (nombre, descripcion, nombre_genero_juego)
VALUES ('LOL', 'defensa de nexo en combate 5 contra 5', 'MOBA');
INSERT INTO detalles_juego (nombre, serial, numero_jugadores, edad_minima)
VALUES  ('Contra', '555333', 10, 7);

--asociar el juego LOL a la tienda La ponderosa
INSERT INTO juego_tienda (nombre, rut)
VALUES ((SELECT nombre FROM juego WHERE nombre = 'LOL'),
	   (SELECT rut FROM tienda WHERE nombre = 'La ponderosa'));

--asociar el juego Contra a la tienda MercaBato
INSERT INTO juego_tienda (nombre, rut)
VALUES ((SELECT nombre FROM juego WHERE nombre = 'Contra'),
	   (SELECT rut FROM tienda WHERE nombre = 'MercaBato'));

--asociar el juego Megaman a la tienda Las tres Marías
INSERT INTO juego_tienda (nombre, rut)
VALUES ((SELECT nombre FROM juego WHERE nombre = 'Megaman'),
	   (SELECT rut FROM tienda WHERE nombre = 'Las tres Marías'));

--actualizar la descripción del juego Tomb Raider por: Una mujer explora tumbas resolviendo acertijos
UPDATE juego 
SET descripcion = 'Una mujer explora tumbas resolviendo acertijos' 
WHERE nombre = 'Tomb Raider';

--actualizar la fecha de creación de las tiendas MercaBato y Rokutux por 1995-10-10 (usar un solo query)
UPDATE tienda
SET fecha_creacion = '1995-10-10' 
WHERE nombre IN ('MercaBato', 'Rokutux');

--eliminar el juego Mario Bross
DELETE FROM detalles_juego 
WHERE nombre = 'Mario Bross';

DELETE FROM juego_tienda 
WHERE nombre = 'Mario Bross';

DELETE FROM juego
WHERE nombre = 'Mario Bross';

--eliminar la tienda Las tres marías
DELETE FROM tienda
WHERE nombre = 'Las tres marías';

--eliminar la tienda Rokutx
DELETE FROM tienda 
WHERE nombre = 'Rokutux';