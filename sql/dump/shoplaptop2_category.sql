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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `enabled` bit(1) DEFAULT NULL,
  `image` varchar(128) NOT NULL,
  `name` varchar(128) NOT NULL,
  `slug` varchar(64) NOT NULL,
  `parent_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_46ccwnsi9409t36lurvtyljak` (`name`),
  UNIQUE KEY `UK_hqknmjh5423vchi4xkyhxlhg2` (`slug`),
  KEY `FK2y94svpmqttx80mshyny85wqr` (`parent_id`),
  CONSTRAINT `FK2y94svpmqttx80mshyny85wqr` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,_binary '','electronics.png','Electronics','electronics',NULL),(2,_binary '','camera.jpg','Camera & Photo','camera',1),(3,_binary '','computers.png','Computers','computers',NULL),(4,_binary '','cellphones.png','Cell Phones & Accessories','cellphones',1),(5,_binary '','desktop computers.png','Desktops','desktop_computers',3),(6,_binary '','laptop computers.png','Laptops','laptop_computers',3),(7,_binary '','tablets.png','Tablets','tablet_computers',3),(8,_binary '','computer components.png','Computer Components','computer_components',3),(9,_binary '','bags_cases.png','Bags & Cases','camera_bags_cases',2),(10,_binary '','digital cameras.png','Digital Cameras','digital_cameras',2),(11,_binary '','flashes.png','Flashes','camera_flashes',2),(12,_binary '','lenses.png','Lenses','camera_lenses',2),(13,_binary '','tripods_monopods.png','Tripods & Monopods','camera_tripods_monopods',2),(14,_binary '','carrier cellphones.png','Carrier Cell Phones','carrier_cellphones',4),(15,_binary '','unlocked cellphones.png','Unlocked Cell Phones','unlocked_cellphones',4),(16,_binary '','cellphone accessories.png','Accessories','cellphone_accessories',4),(17,_binary '','cables and adapters.png','Cables & Adapters','cellphone_cables_adapters',16),(18,_binary '','microsd cards.png','MicroSD Cards','microsd_cards',16),(19,_binary '','cellphone_stands.png','Stands','cellphone_stands',16),(20,_binary '','cellphone cases.png','Cases','cellphone_cases',16),(21,_binary '','headphones.png','Headphones','headphones',16),(22,_binary '','computer processors.png','CPU Processors Unit','computer_processors',8),(23,_binary '','graphic cards.png','Graphic Cards','computer_graphic_cards',8),(24,_binary '','internal hard drive.png','Internal Hard Drives','hard_drive',8),(25,_binary '','internal optical drives.png','Internal Optical Drives','computer_optical_drives',8),(26,_binary '','power supplies.png','Power Supplies','computer_power_supplies',8),(27,_binary '','solid state drives.png','Solid State Drives','solid_state_drives',8),(28,_binary '','sound cards.png','Sound Cards','computer_sound_cards',8),(29,_binary '','computer memory.png','Memory','computer_memory',8),(30,_binary '','motherboards.png','Motherboard','computer_motherboard',8),(31,_binary '','network cards.png','Network Cards','computer_network_cards',8);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
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
