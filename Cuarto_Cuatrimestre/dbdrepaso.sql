/*6. Listar nombre, apellido, DNI, teléfono y dirección de clientes que compraron los
productos con nombre ‘prod1’ y ‘prod2’ pero nunca compraron el producto con nombre
‘prod3’*/
SELECT DISTINCT c.nombre, c.apellido, c.dni, c.telefono, c.dirección
FROM cliente c NATURAL JOIN factura f NATURAL JOIN detalle d NATURAL JOIN producto p
WHERE p.nombreP=prod1
UNION
SELECT DISTINCT c.nombre, c.apellido, c.dni, c.telefono, c.dirección
FROM cliente c NATURAL JOIN factura f NATURAL JOIN detalle d NATURAL JOIN producto p
WHERE p.nombreP=prod2
EXCEPT 
SELECT c.nombre, c.apellido, c.dni, c.telefono, c.dirección
FROM cliente c NATURAL JOIN factura f NATURAL JOIN detalle d NATURAL JOIN producto p
WHERE p.nombreP=prod3

/*8. Agregar un cliente con los siguientes datos: nombre:’Jorge Luis’, apellido:’Castor’,
DNI:40578999, teléfono:221-4400789, dirección:’11 entre 500 y 501 nro:2587’ y el id de
cliente: 500002. Se supone que el idCliente 500002 no existe.*/
INSERT INTO cliente (nombre,apellido,telefono,dirección,id) VALUES (jorge luis, castor, 44, bla)
cliente<- cliente u {(jorge luis, castor, 44, etc)}

/*10. Listar DNI, apellido y nombre de clientes donde el monto total comprado, teniendo en
cuenta todas sus facturas, supere $10.000.000.
*/
SELECT c.dni, c.apellido, c.nombre
FROM cliente c NATURAL JOIN factura f
GROUP BY c.dni, c.apellido, c.nombre
HAVING SUM(f.total)>100000

222222222222222222222
/*6. Listar nombre, apellido, dirección y teléfono de clientes que viajaron con todas las agencias.*/

SELECT c.nombre, c.apellido, c.dirección, c.teléfono
FROM cliente c 
WHERE NOT EXISTS(
  SELECT *
  FROM agencia a
  WHERE NOT EXISTS(
    SELECT *
    FROM VIAJE v
    WHERE a.razonsocial=v.razonsocial AND c.dni=v.dni
  )
)

pi nombre,apellido,dirección,telefono(cliente|x|viaje)%pi codigo(agencia)
/*7. Modificar el cliente con DNI: 38495444 actualizando el teléfono a: 221-4400897.*/

UPDATE cliente
set telefono=numero
WHERE dni=389444

(s rara) tel <-tel=221 (omega dni=38 cliente)


parcial4, ej4
SELECT DISTINCT j.nombre, j.apellido, j.edad, COUNT(*)
FROM jugador j natural join clubJugador cj 
GROUP BY j.nombre, j.apellido, j.edad
edit 

/*Listar Nombre, Descripción del curso que posea más alumnos inscriptos y del que posea
menos alumnos inscriptos durante 2019.*/

SELECT c.nombre, c.Descripción
FROM curso c
WHERE c.cod=(SELECT max(count(*))
FROM curso c1 NATURAL JOIN alumno-curso ac
GROUP BY c1.cod 
)

/*Listar el DNI, Apellido, Nombre, Cantidad de horas y Promedio de horas que dicta cada
profesor. La cantidad de horas se calcula como la suma de la duración de todos los
cursos que dicta*/
SELECT p.dni, p.apellido, p.nombre, SUM(c.duracion), avg(c.duracion)
FROM profesor p NATURAL JOIN profesor-curso NATURAL JOIN curso c
GROUP BY p.dni, p.apellido, p.nombre

/*11. Dar de baja el alumno con DNI 30568989. Realizar todas las bajas necesarias para no
dejar el conjunto de relaciones en estado inconsistente.
*/
DELETE FROM alumno
WHERE dni=30568989
DELETE FROM alumno-curso
WHERE dni=30568989
¿DELETE FROM persona
WHERE dni=30568989?

alumno<= alumno-(signo dni=30568989)
alumno-curso<= alumno-curso - (signo dni=30568989)

/*6. Listar nombre, apellido, DNI, teléfono y dirección de clientes que compraron los
productos con nombre ‘prod1’ y ‘prod2’ pero nunca compraron el producto con nombre
‘prod3’*/