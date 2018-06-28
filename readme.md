##Problema

Consiste en un sistema que simula el comportamiento del vigilante de un parqueadero, las reglas de negocio son las siguientes:

El parqueadero recibe carros y motos 
El parqueadero solo puede tener 20 carros y 10 motos simultaneamente
Las placas que inician por la letra "A" solo pueden ingresar al parqueadero los días Domingo y Lunes, de lo contrario debe mostrar un mensaje de que no esta autorizado a ingresar.
La tabla de precios es la siguiente:

Valor hora carro = 1000
Valor hora moto = 500
valor día carro = 8000
valor día moto = 4000

Las motos que tengan un cilindraje mayor a 500 CC paga 2000 de mas al valor total.
Cuando sale un carro del parqueadero se cobra por horas si permaneció menos de 9 horas en el parqueadero, de lo contrario se cobra por días.
El valor del día comienza entre las 9 horas de parqueo y finaliza pasadas 24 horas de parqueo.
El parqueadero funciona 24 horas, los 7 días de la semana.
EJEMPLOS: 
*Si el carro permaneció un día y 3 horas se debe cobrar 11.000
*Si la moto permaneció un 10 horas y es de 650CC se cobra 6.000

##Requerimientos no funcionales

La aplicación debe ser construida para la Web
La interfaz gráfica Web se debe adaptar a los celulares
Los servicios REST deben aceptar mínimo 100 peticiones concurrentes por minuto con un tiempo de respuesta cada una no mayor a 1,5 seg
Todo el software se debe construir con pruebas automatizadas, para validar cada una de las capas. Por ejemplo, pruebas unitarias, integración, carga y funcionales.
Todas las pruebas y el proceso debe quedar automatizado en Jenkins
El servidor de producción sobre el que se instalará la aplicación tiene Linux (Ubuntu 12) con 2 Gb de memoria RAM y 4 cores de procesamiento