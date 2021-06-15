-- MySQL dump 10.18  Distrib 10.3.27-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: eleccionesDB
-- ------------------------------------------------------
-- Server version	10.3.27-MariaDB-0+deb10u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

DROP DATABASE IF EXISTS eleccionesDB;
CREATE DATABASE eleccionesDB CHARACTER SET utf8mb4;
USE eleccionesDB;

--
-- Table structure for table `cargos`
--


DROP TABLE IF EXISTS `cargos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cargos` (
  `id_cargo` int(1) NOT NULL AUTO_INCREMENT,
  `cargo` varchar(30) NOT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id_cargo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargos`
--

LOCK TABLES `cargos` WRITE;
/*!40000 ALTER TABLE `cargos` DISABLE KEYS */;
INSERT INTO `cargos` VALUES (1,'presidente','Maxima autoridad en un estado soverano'),(2,'alcalde','Autoridad a nivel municipal'),(3,'diputado','Autoridad a nivel departamenta');
/*!40000 ALTER TABLE `cargos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departamentos`
--

DROP TABLE IF EXISTS `departamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departamentos` (
  `id_departamento` int(2) NOT NULL,
  `departamento` varchar(60) NOT NULL,
  `cantidad_diputados` int(2) NOT NULL,
  PRIMARY KEY (`id_departamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamentos`
--

LOCK TABLES `departamentos` WRITE;
/*!40000 ALTER TABLE `departamentos` DISABLE KEYS */;
INSERT INTO `departamentos` VALUES (1,'Santa Ana',7),(2,'Sonsonate',6),(3,'San Salvador',24),(4,'Ahuachapan',4),(5,'La Libertad',10),(6,'Chalatenango',3),(7,'Cuscatlan',3),(8,'Cabañas',3),(9,'La Paz',4),(10,'San Vicente',3),(11,'Usulutan',5),(12,'Jan Miguel',6),(13,'Morazan',3),(14,'La Union',3);
/*!40000 ALTER TABLE `departamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `municipios`
--

DROP TABLE IF EXISTS `municipios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `municipios` (
  `id_municipio` int(3) NOT NULL AUTO_INCREMENT,
  `id_departamento` int(2) NOT NULL,
  `municipio` varchar(70) NOT NULL,
  PRIMARY KEY (`id_municipio`),
  KEY `municipios_FK` (`id_departamento`),
  CONSTRAINT `municipios_FK` FOREIGN KEY (`id_departamento`) REFERENCES `departamentos` (`id_departamento`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `municipios`
--

LOCK TABLES `municipios` WRITE;
/*!40000 ALTER TABLE `municipios` DISABLE KEYS */;
INSERT INTO `municipios` VALUES (1,1,'Santa Ana'),(2,1,'Metapan'),(3,2,'Izalco'),(4,3,'Soyapango'),(5,3,'Mejicanos'),(6,2,'Acajutla'),(7,3,'Apopa'),(8,4,'Atiquizaya'),(9,4,'Apaneca'),(10,5,'Santa Tecla'),(11,5,'Sacacoyo'),(12,6,'Comalapa'),(13,6,'Tejutla'),(14,7,'El Rosario'),(15,7,'Suchitoto'),(16,8,'Dolores'),(17,8,'Ilobasco'),(18,9,'San Luis Talpa'),(19,9,'Olocuilta'),(20,10,'San Lorenzo'),(21,10,'Guadalupe'),(22,11,'Berlin'),(23,11,'Jucuapa'),(24,12,'Jan Jorge'),(25,12,'Quelepa'),(26,13,'Jocoro'),(27,13,'Torola'),(28,14,'El Sauce'),(29,14,'Lislique');
/*!40000 ALTER TABLE `municipios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partidos`
--

DROP TABLE IF EXISTS `partidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partidos` (
  `id_partido` int(2) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(60) NOT NULL,
  `descripcion` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id_partido`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partidos`
--

LOCK TABLES `partidos` WRITE;
/*!40000 ALTER TABLE `partidos` DISABLE KEYS */;
INSERT INTO `partidos` VALUES (1,'NUEVAS IDEAS','PARTIDO CON LA N DE NAYIB DICTADOR'),(2,'ARENA','LOS MAÑOSITOS DEVUELVAN LO ROBADO'),(3,'FRENTE','SON ARENA 2.0'),(4,'GANA','LOS OPORTUNISTAS'),(5,'PDC','APENAS VIVOS');
/*!40000 ALTER TABLE `partidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol` (
  `id_rol` int(1) NOT NULL AUTO_INCREMENT,
  `rol` varchar(50) NOT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'administrador'),(2,'usuario');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(50) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `dui` int(9) NOT NULL,
  `sexo` char(1) NOT NULL,
  `contrasenia` varchar(20) NOT NULL,
  `id_rol` int(1) NOT NULL,
  `estado_voto` tinyint(1) NOT NULL DEFAULT 0,
  `id_municipio` int(3) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `usuarios_FK_1` (`id_municipio`),
  KEY `usuarios_FK` (`id_rol`),
  CONSTRAINT `usuarios_FK` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`),
  CONSTRAINT `usuarios_FK_1` FOREIGN KEY (`id_municipio`) REFERENCES `municipios` (`id_municipio`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'VALERI ALESSANDRA','DUEÑAS AVALOS',123456789,'F','12345',2,0,1),(2,'GABRIELA ALEXANDRA','DUEÑAS ALAS',987654321,'F','12345',2,0,1),(3,'JOSE ALEJANDRO','ARAGON RUGAMAS',111111111,'M','54321',1,0,2),(4,'CHRISTIAN VLADIMIR','AVALOS SERANOI',222222222,'M','98765',1,0,3),(6,'PEDRO','PEREZ',333333333,'M','12345',2,0,1),(7,'MARIA','CARTAGENA',333333331,'M','12345',2,0,1),(8,'PEPE','PEPINO',333333332,'M','12345',2,0,1),(9,'CARO','CARITO',333333334,'M','12345',2,0,1),(10,'MEME','MEMON',333333335,'M','12345',2,0,1),(11,'JAIME','CARTEO',333333336,'M','12345',2,0,1),(12,'TUPAC','CHACUR',333333337,'M','12345',2,0,1),(13,'VCTOR','GARCÍA',123456780,'M','12345',2,0,6),(14,'MARIA','DE LOS ANGELES',789123456,'F','12345',2,0,3),(15,'MONICA','CENTENO',123789417,'F','12345',2,0,6),(16,'ALBERTO','FUENTES',852369741,'M','12345',2,0,6),(17,'CARMEN','ORELLANA',546123798,'F','12345',2,0,3),(18,'GERALDINE','RAMIREZ',111111456,'F','12345',2,0,3),(19,'ADOLFO','ALFARO',888555444,'M','12345',2,0,6),(20,'MANUEL','GOMEZ',898787456,'M','12345',2,0,9),(21,'ALBA','RUGAMAS',555444111,'F','12345',2,0,8),(22,'DANILO','CASTILLO',111222897,'M','12345',2,0,8),(23,'DARWIN','AREVALO',555465546,'M','12345',2,0,9),(24,'DORA','CEREN',123123123,'F','12345',2,0,9),(25,'GIOVANI','ARGUMEDO',546456654,'M','12345',2,0,8),(26,'DAVID','CABRERA',885552641,'M','12345',2,0,2),(27,'MARVIN','ACEVEDO',111414251,'M','12345',2,0,2),(28,'ALMA','RIVAS',154879369,'F','12345',2,0,1),(29,'FERMIN','GODOY',154838769,'M','12345',2,0,3);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `votos_candidato`
--

DROP TABLE IF EXISTS `votos_candidato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `votos_candidato` (
  `id_voto_candidato` int(4) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `id_cargo` int(1) NOT NULL,
  `id_partido` int(2) NOT NULL,
  `votos` int(9) DEFAULT 0,
  PRIMARY KEY (`id_voto_candidato`),
  KEY `votos_candidato_FK` (`id_cargo`),
  KEY `votos_candidato_FK_1` (`id_usuario`),
  KEY `votos_candidato_FK_2` (`id_partido`),
  CONSTRAINT `votos_candidato_FK` FOREIGN KEY (`id_cargo`) REFERENCES `cargos` (`id_cargo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `votos_candidato_FK_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`),
  CONSTRAINT `votos_candidato_FK_2` FOREIGN KEY (`id_partido`) REFERENCES `partidos` (`id_partido`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `votos_candidato`
--

LOCK TABLES `votos_candidato` WRITE;
/*!40000 ALTER TABLE `votos_candidato` DISABLE KEYS */;
INSERT INTO `votos_candidato` VALUES (2,4,2,1,0),(38,1,3,1,0),(39,2,3,1,0),(41,6,3,1,0),(42,7,3,1,0),(43,8,3,1,0),(44,9,3,1,0),(45,10,3,1,0),(46,11,1,1,0),(47,3,1,2,0);
/*!40000 ALTER TABLE `votos_candidato` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-14 17:00:19
