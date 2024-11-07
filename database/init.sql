-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: kyrsach
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */
/*!50503 SET NAMES utf8 */
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */
/*!40103 SET TIME_ZONE='+00:00' */
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */

--
-- Table structure for table `Admin`
--

DROP TABLE IF EXISTS `Admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Admin` (
  `idA` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) DEFAULT NULL,
  `Login` varchar(45) DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idA`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Admin`
--

LOCK TABLES `Admin` WRITE;
/*!40000 ALTER TABLE `Admin` DISABLE KEYS */;
INSERT INTO `Admin` VALUES (1,'Anton','123','123'),(2,'Gena','321','321');
/*!40000 ALTER TABLE `Admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Boo_Ser`
--

DROP TABLE IF EXISTS `Boo_Ser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Boo_Ser` (
  `idBS` int NOT NULL AUTO_INCREMENT,
  `idBoo` int DEFAULT NULL,
  `idSer` int DEFAULT NULL,
  PRIMARY KEY (`idBS`),
  KEY `idBoo_idx` (`idBoo`),
  KEY `idSer_idx` (`idSer`),
  CONSTRAINT `idBoo` FOREIGN KEY (`idBoo`) REFERENCES `Booking` (`idB`),
  CONSTRAINT `idSer` FOREIGN KEY (`idSer`) REFERENCES `Service` (`idS`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Boo_Ser`
--

LOCK TABLES `Boo_Ser` WRITE;
/*!40000 ALTER TABLE `Boo_Ser` DISABLE KEYS */;
INSERT INTO `Boo_Ser` VALUES (8,12,1),(9,12,2),(10,13,1),(11,14,1),(12,14,2),(13,14,3),(14,15,1),(15,16,1),(16,17,2),(17,17,3),(18,17,4),(19,19,1),(20,23,2),(21,23,4),(22,24,4),(23,24,5),(24,25,3),(25,25,4),(26,26,4),(27,26,5),(28,27,1),(29,27,4),(30,27,5);
/*!40000 ALTER TABLE `Boo_Ser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Booking`
--

DROP TABLE IF EXISTS `Booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Booking` (
  `idB` int NOT NULL AUTO_INCREMENT,
  `dz` date DEFAULT NULL,
  `dv` date DEFAULT NULL,
  `fullPrice` decimal(10,2) DEFAULT NULL,
  `Klient_idK` int NOT NULL,
  `Room_idR` int NOT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`idB`),
  KEY `fk_Booking_Klients_idx` (`Klient_idK`),
  KEY `fk_Booking_Room1_idx` (`Room_idR`),
  CONSTRAINT `fk_Booking_Klients` FOREIGN KEY (`Klient_idK`) REFERENCES `Klients` (`idK`),
  CONSTRAINT `fk_Booking_Room1` FOREIGN KEY (`Room_idR`) REFERENCES `Room` (`idR`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Booking`
--

LOCK TABLES `Booking` WRITE;
/*!40000 ALTER TABLE `Booking` DISABLE KEYS */;
INSERT INTO `Booking` VALUES (12,'2023-11-01','2023-11-03',1580.00,1,4,0),(13,'2023-11-03','2023-11-04',700.00,1,9,0),(14,'2023-11-30','2024-11-08',259181.00,11,5,0),(15,'2023-11-23','2023-11-27',5450.00,1,4,1),(16,'2023-11-23','2023-11-27',3000.00,3,6,0),(17,'2023-12-29','2024-01-07',19568.00,1,2,0),(18,'2023-11-16','2023-11-21',1800.00,3,4,1),(19,'2023-11-02','2023-11-10',4150.00,1,4,0),(20,'2023-11-02','2023-11-03',681.00,1,6,0),(21,'2023-11-01','2023-11-10',8005.00,1,2,0),(22,'2023-11-26','2023-11-27',900.00,1,4,0),(23,'2024-03-01','2024-03-08',14430.00,12,3,1),(24,'2024-03-01','2024-03-03',4550.00,4,4,0),(25,'2023-05-30','2023-11-19',81450.00,13,4,1),(26,'2023-11-29','2023-11-30',3800.00,1,9,1),(27,'2023-11-25','2023-11-26',6100.00,1,3,1);
/*!40000 ALTER TABLE `Booking` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `room_date` AFTER INSERT ON `booking` FOR EACH ROW UPDATE Room
SET dateZ = NEW.dz, dateV = NEW.dv
WHERE idR = NEW.Room_idR */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `update_fullPrice` BEFORE UPDATE ON `booking` FOR EACH ROW BEGIN
 DECLARE price_change INT;
 DECLARE room_price INT;
 DECLARE days_diff INT;

 IF NEW.dv > OLD.dv THEN
 SELECT priceR INTO room_price
 FROM Room
 WHERE idR = NEW.Room_idR;

 SET days_diff = DATEDIFF(NEW.dv, OLD.dv);
 SET price_change = room_price * days_diff;

 IF (NEW.fullPrice + price_change) >= 0 THEN
  SET NEW.fullPrice = NEW.fullPrice + price_change;
 ELSE
  SET NEW.fullPrice = 0;
 END IF;
 ELSEIF NEW.dv < OLD.dv THEN
 SELECT priceR INTO room_price
 FROM Room
 WHERE idR = NEW.Room_idR;

 SET days_diff = DATEDIFF(OLD.dv, NEW.dv);
 SET price_change = room_price * days_diff;

 IF (NEW.fullPrice - price_change) >= 0 THEN
  SET NEW.fullPrice = NEW.fullPrice - price_change;
 ELSE
  SET NEW.fullPrice = 0;
 END IF;
 END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `clear_dates_in_room` AFTER UPDATE ON `booking` FOR EACH ROW BEGIN
    DECLARE room_idR INT;
    IF NEW.status = 0 THEN
        SELECT idR INTO Room_idR FROM Room WHERE idR = NEW.Room_idR;
        
        IF Room_idR IS NOT NULL THEN
            UPDATE Room
            SET dateZ = NULL, dateV = NULL
            WHERE idR = Room_idR;
        END IF;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `Klients`
--

DROP TABLE IF EXISTS `Klients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Klients` (
  `idK` int NOT NULL AUTO_INCREMENT,
  `nameK` varchar(45) DEFAULT NULL,
  `surnameK` varchar(45) DEFAULT NULL,
  `patronymicK` varchar(45) DEFAULT NULL,
  `numTel` varchar(45) DEFAULT NULL,
  `passport` varchar(45) DEFAULT NULL,
  `parolK` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idK`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Klients`
--

LOCK TABLES `Klients` WRITE;
/*!40000 ALTER TABLE `Klients` DISABLE KEYS */;
INSERT INTO `Klients` VALUES (1,'Иван','Казанов','Евгеньевич','11','2202 309043','123'),(2,'Виктор','Шереметьев','Августович','89300889006','2011 309987','321'),(3,'Илья','Павлов','Дмитреевич','89213347821','9038 678111','234'),(4,'Екатерина','Мишлакова','Ивановна','89100223112','3201 633221','432'),(10,'Алёна','Костякова','Александровна','897876787','2020 478512','1121'),(11,'Юлия','Некрасова','Михайловна','9001','2023 436511','9001'),(12,'Арина','Кошкина','Антеньевна','8910','2201 345122','1212'),(13,'Елена','Калентьева','Вячеславовна','+70000000000','2222 567777','йй');
/*!40000 ALTER TABLE `Klients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Room`
--

DROP TABLE IF EXISTS `Room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Room` (
  `idR` int NOT NULL AUTO_INCREMENT,
  `type` int DEFAULT NULL,
  `descriptionR` varchar(500) DEFAULT NULL,
  `photo` varchar(45) DEFAULT NULL,
  `priceR` decimal(10,2) DEFAULT NULL,
  `dateZ` date DEFAULT NULL,
  `dateV` date DEFAULT NULL,
  `nameR` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idR`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Room`
--

LOCK TABLES `Room` WRITE;
/*!40000 ALTER TABLE `Room` DISABLE KEYS */;
INSERT INTO `Room` VALUES (2,2,'Этот гостиничный номер с захватывающим видом на море идеально подходит для романтического или спокойного отдыха. Рассчитанный на двух гостей, он оснащен удобной мебелью и современными удобствами.','dva_more.jpg',800.50,NULL,NULL,'Двойное море'),(3,3,'Из этого гостиничного номера на троих открывается захватывающий вид на горы. Он со вкусом оформлен, обставлен удобной мебелью и имеет достаточно места, чтобы обеспечить приятное пребывание.','tri_gori.jpg',1400.00,'2023-11-25','2023-11-26','Тройные горы'),(4,1,'Из этого уютного одноместного номера открывается потрясающий вид на горы, что создает спокойную и живописную атмосферу для индивидуальных путешественников!','odin_gori.jpg',450.00,NULL,NULL,'Уютные горы'),(5,2,'Этот гостиничный номер с захватывающим видом на море идеально подходит для пар. Наслаждайтесь успокаивающим шумом волн и потрясающими видами, не выходя из своего номера.','dva_moree.jpg',750.00,NULL,NULL,'Романтичное море'),(6,1,'Из этого одноместного номера нашего отеля открывается потрясающий вид на город, что позволяет гостям любоваться шумным городским пейзажем прямо из окна. Элегантно спроектированный и удобный.','odin_gorod.jpeg',340.50,NULL,NULL,'Шумный город'),(8,1,'Номер с потрясающим видом на море и удобной односпальной кроватью. Наслаждайтесь красотой океана прямо из своего номера, создавая спокойную и безмятежную атмосферу для спокойного отдыха!','odin_more.jpg',480.00,NULL,NULL,'Морской бриз'),(9,1,'Наш небольшой и уютный номер эконом-класса. Номер продуманно спроектирован так, чтобы максимально увеличить пространство и в то же время предоставить все необходимые удобства, необходимые для приятного отдыха.','odin_econom.jpg',300.00,'2023-11-29','2023-11-30','красивые шторы');
/*!40000 ALTER TABLE `Room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Service`
--

DROP TABLE IF EXISTS `Service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Service` (
  `idS` int NOT NULL AUTO_INCREMENT,
  `nameS` varchar(45) DEFAULT NULL,
  `priceS` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idS`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Service`
--

LOCK TABLES `Service` WRITE;
/*!40000 ALTER TABLE `Service` DISABLE KEYS */;
INSERT INTO `Service` VALUES (1,'Доп. постельное бельё',100.00),(2,'Уборка в номере',230.00),(3,'Доставка ужина в номер',150.00),(4,'★all inclusive★',3000.00),(5,'Доп. спальное место',200.00);
/*!40000 ALTER TABLE `Service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'kyrsach'
--

--
-- Dumping routines for database 'kyrsach'
--
/*!50003 DROP PROCEDURE IF EXISTS `add_user` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `add_user`(
    IN idK INT,
    IN nameK VARCHAR(50),
    IN surnameK VARCHAR(50),
    IN patronymicK VARCHAR(50),
    IN numTel VARCHAR(50),
    IN passport VARCHAR(50),
    IN parolK VARCHAR(50)
)
BEGIN
    INSERT INTO Klients (idK, nameK, surnameK, patronymicK, numTel, passport, parolK) 
    VALUES (idK, nameK, surnameK, patronymicK, numTel, passport, parolK);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-24 23:14:48
