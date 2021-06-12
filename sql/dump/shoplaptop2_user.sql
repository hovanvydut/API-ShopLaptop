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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(128) NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `password` varchar(64) NOT NULL,
  `photos` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin@gmail.com',_binary '','Admin','Admin','$2a$12$WOiLDYiihlHRbgCv7WzrrODhisC/TFkqTSrTu9ZipZZjj5MhP7v86','1.png'),(2,'salesperson1@gmail.com',_binary '','One','Sales','$2a$12$0VSOs8at6ynSR76Pw3z5WekkO9Evo68XjCbEsUHZZahHyQZyNgio6','2.png'),(3,'salesperson2@gmail.com',_binary '','Two','Sales','$2a$12$ocBoIvPigh5hK9QlkTuOI.myvJTMlw0p.WEgb75EuQj65zzGOkZda','3.png'),(4,'salesperson3@gmail.com',_binary '','Three','Sales','$2a$12$ZMA8.ykrdSAz692i2aTmIOJslXmUZr/FJhWF.3l3d.TvMv/CyOeF2','4.png'),(5,'editor1@gmail.com',_binary '','One','Editor','$2a$12$Fs46wr97L8QQpwDJ0P3Rp.NEh7Qa3eYd3UrwWfGB/93zjBC8XieJi','5.png'),(6,'editor2@gmail.com',_binary '','Two','Editor','$2a$12$VpcieFVnaFrCJi1lhqq1DO9Bsogsw9wKmL/9RBL3Rx.ie7BOJhkUC','6.png'),(7,'editor3@gmail.com',_binary '','Three','Editor','$2a$12$VqloRY0sr5gFzLpNtnjkh.K0p4Xqa4LsuhREqb44r9qdjD2aODN5.','7.png'),(8,'shipper1@gmail.com',_binary '','One','Shipper','$2a$12$w2fKwYigwrLdqyyoeJvFP.kE3ORTXXJZmcfJXMQKgW.m.pVuu8Cuu','8.png'),(9,'shipper2@gmail.com',_binary '','Two','Shipper','$2a$12$O9XjgU2d2Z9sJ7j6lD43Zu2mpbpJ9Y8Z3.j73pHsmf34aWpNM3.pS','9.png'),(10,'shipper3@gmail.com',_binary '','Three','Shipper','$2a$12$b4sZ7/f7408TWHl6kPBIyOHrW5u8VFr/Rdfp07UJKW/QYdcxw.Nee','10.png'),(11,'assistant1@gmail.com',_binary '','One','Assistant','$2a$12$k9zfDmvzNH.Oc9hLCyLc2.So/2iumpI3BS7Xhs.qMoY0O6Hnosfta','11.png'),(12,'assistant2@gmail.com',_binary '','Two','Assistant','$2a$12$5flpQ3jAVucX3MP.kAKw7Ovqyg75c/08F1ZZKTCApaS0oscgJ8a16','12.png'),(13,'assistant3@gmail.com',_binary '','Three','Assistant','$2a$12$nZqDHh6hteiMeIUtBMc9nOmTVTC9VOor4BXhXCojWKAMBeJkTVr1y','13.png'),(14,'salesperson.editor@gmail.com',_binary '','Salesperson','Editor','$2a$12$ToM2wceZWHxNxcj82C5zH.yp8Vmu17M0vFbsmw26VM4/su/n9eb4e','14.png'),(15,'salesperson.shipper@gmail.com',_binary '','Salesperson','Shipper','$2a$12$SR1YZcPhaVso.2lbKzHLbeScnaRok6NwwlTvGjN7BnryCX4UNGfaG','15.png'),(16,'salesperson.assistant@gmail.com',_binary '','Salesperson','Assistant','$2a$12$tc/yHHVItqUexda7ldsrQ.MUAqKtuLUgufZw3dARtaH/p/hPqSmFa','16.png'),(17,'editor.shipper@gmail.com',_binary '','Editor','Shipper','$2a$12$c9VDjZkp2uXAELy8fFP4AuUq0NKEarTIIzPRSo6.uR0r0HMPymYg6','17.png'),(18,'editor.assistant@gmail.com',_binary '','Editor','Assistant','$2a$12$9DZOEpjyrUS9qqOQOq.xAO2rgvorLAhIO8NB4N3zMg5SGkQdE6x/.','18.png'),(19,'shipper.assistant@gmail.com',_binary '','Shipper','Assistant','$2a$12$dSkKz0oUYtq7HpXaTvA8V.RPC9Pv21CVD4AkxyrF6xRwx8Vy5p1my','19.png');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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
