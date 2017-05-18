-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-04-2017 a las 23:04:06
-- Versión del servidor: 5.6.26-log
-- Versión de PHP: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `prestamos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `devices`
--

CREATE TABLE `devices` (
  `code` varchar(15) NOT NULL,
  `copy` varchar(5) NOT NULL,
  `name` varchar(50) NOT NULL,
  `type` varchar(15) NOT NULL,
  `status` varchar(15) NOT NULL DEFAULT 'DISPONIBLE',
  `details` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `devices`
--

INSERT INTO `devices` (`code`, `copy`, `name`, `type`, `status`, `details`) VALUES
('0001', '1', 'Portátil Samsumg', 'portatil', 'DISPONIBLE', NULL),
('0001', '2', 'Portatil Lenovo', 'portatil', 'DISPONIBLE', NULL),
('0002', '1', 'Microscopio A', 'microscopio', 'DISPONIBLE', 'Dispone de 40 x, 100 x y 400 aumentos de x\r\nÓ,ptica de vidrio real\r\ndiafragma de disco de 6 agujeros\r\nBaterí,a recargable');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `loans`
--

CREATE TABLE `loans` (
  `startDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `endDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `returnDate` timestamp NULL DEFAULT NULL,
  `status` varchar(15) NOT NULL,
  `username` varchar(20) NOT NULL,
  `code` varchar(15) NOT NULL,
  `copy` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `loans`
--

INSERT INTO `loans` (`startDate`, `endDate`, `returnDate`, `status`, `username`, `code`, `copy`) VALUES
('2017-04-11 20:00:00', '2017-04-12 00:00:00', NULL, 'PRESTADO', 'raulio', '0002', '1'),
('2017-04-12 15:00:00', '2017-04-12 17:00:00', NULL, 'RESERVADO', 'juan.goez', '0001', '1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `username` varchar(20) NOT NULL,
  `typeId` varchar(3) NOT NULL,
  `numberId` varchar(12) NOT NULL,
  `name` varchar(50) NOT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(30) NOT NULL,
  `role` varchar(1) NOT NULL,
  `manager` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`username`, `typeId`, `numberId`, `name`, `lastName`, `email`, `password`, `role`, `manager`) VALUES
('admin', 'CC', '123', 'Administrador', NULL, NULL, 'admin', 'A', NULL),
('juan.goez', 'CC', '12345', 'Juan', NULL, NULL, '123', 'I', NULL),
('raulio', 'CC', '1117', 'Raul', NULL, NULL, 'root', 'I', NULL),
('root', 'CC', '789', 'Administrador', 'Segundo', NULL, '123', 'A', 'admin');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `devices`
--
ALTER TABLE `devices`
  ADD PRIMARY KEY (`code`,`copy`);

--
-- Indices de la tabla `loans`
--
ALTER TABLE `loans`
  ADD PRIMARY KEY (`startDate`,`username`,`code`,`copy`),
  ADD KEY `username` (`username`),
  ADD KEY `code` (`code`,`copy`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `typeId` (`typeId`,`numberId`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `manager` (`manager`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `loans`
--
ALTER TABLE `loans`
  ADD CONSTRAINT `loans_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
  ADD CONSTRAINT `loans_ibfk_2` FOREIGN KEY (`code`,`copy`) REFERENCES `devices` (`code`, `copy`);

--
-- Filtros para la tabla `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`manager`) REFERENCES `users` (`username`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
