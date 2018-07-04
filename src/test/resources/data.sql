/*
-- Query: SELECT * FROM parqueadero.tarifa
LIMIT 0, 1000

-- Date: 2018-07-03 12:07
*/
INSERT INTO `tarifa` (`tipo_vehiculo`,`tipo_tarifa`,`unidad_tiempo`,`valor`) VALUES ('CARRO','TIEMPO','DIA',8000.00);
INSERT INTO `tarifa` (`tipo_vehiculo`,`tipo_tarifa`,`unidad_tiempo`,`valor`) VALUES ('CARRO','TIEMPO','HORA',1000.00);
INSERT INTO `tarifa` (`tipo_vehiculo`,`tipo_tarifa`,`unidad_tiempo`,`valor`) VALUES ('MOTO','ADICIONAL','NOAPLICA',2000.00);
INSERT INTO `tarifa` (`tipo_vehiculo`,`tipo_tarifa`,`unidad_tiempo`,`valor`) VALUES ('MOTO','TIEMPO','DIA',4000.00);
INSERT INTO `tarifa` (`tipo_vehiculo`,`tipo_tarifa`,`unidad_tiempo`,`valor`) VALUES ('MOTO','TIEMPO','HORA',500.00);
