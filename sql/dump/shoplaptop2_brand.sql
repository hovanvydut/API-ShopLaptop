-- MySQL dump 10.13  Distrib 8.0.25, for Linux (x86_64)
--
-- Host: localhost    Database: shoplaptop2
-- ------------------------------------------------------
-- Server version	8.0.25-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brand` (
  `id` int NOT NULL AUTO_INCREMENT,
  `logo` varchar(128) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rdxh7tq2xs66r485cc8dkxt77` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'Canon.png','Canon'),(2,'Fujifilm.png','Fujifilm'),(3,'Sony.png','Sony'),(4,'HP.png','HP'),(5,'SanDisk.png','SanDisk'),(6,'Western Digital.png','Western Digital'),(7,'Panasonic.png','Panasonic'),(8,'Pelican.png','Pelican'),(9,'Apple.png','Apple'),(10,'Samsung.png','Samsung'),(11,'Olympus.png','Olympus'),(12,'Caden.png','CADeN'),(13,'amazonbasics.png','AmazonBasics'),(14,'Nikon.png','Nikon'),(15,'Neewer.png','Neewer'),(16,'Sigma.png','Sigma'),(17,'Bosch.png','Bosch'),(18,'Joby.png','Joby'),(19,'GoPro.png','GoPro'),(20,'Manfrotto.png','Manfrotto'),(21,'Google.png','Google'),(22,'LG.png','LG'),(23,'Motorola.png','Motorola'),(24,'Pantech.png','Pantech'),(25,'Huawei.png','Huawei'),(26,'Xiaomi.png','Xiaomi'),(27,'Hovamp.png','HOVAMP'),(28,'Aioneus.png','Aioneus'),(29,'XIAE.png','XIAE'),(30,'Everyworth.png','Everyworth'),(31,'Lexar.png','Lexar'),(32,'Nulaxy.png','Nulaxy'),(33,'Fitfort.png','Fitfort'),(34,'PopSocket.png','PopSockets'),(35,'SHAWE.png','SHAWE'),(36,'Lenovo.png','Lenovo'),(37,'Acer.png','Acer'),(38,'Dell.png','Dell'),(39,'Intel.png','Intel'),(40,'ASUS.png','ASUS'),(41,'Microsoft.png','Microsoft'),(42,'DragonTouch.png','DragonTouch'),(43,'AMD.png','AMD'),(44,'XFX.png','XFX'),(45,'MSI.png','MSI'),(46,'Seagate.png','Seagate'),(47,'Corsair.png','Cosair'),(48,'Thermaltake.png','Thermaltake'),(49,'Kingston.png','Kingston'),(50,'Creative.png','Creative'),(51,'Crucial.png','Crucial'),(52,'HyperX.png','HyperX'),(53,'Gigabyte.png','Gigabyte'),(54,'TP-Link.png','TP-Link');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-09 10:36:22
