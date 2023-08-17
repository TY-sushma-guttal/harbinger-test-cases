-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: rent-book-web
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `notification_info`
--

DROP TABLE IF EXISTS `notification_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification_info` (
  `notification_id` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `id` int DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`notification_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification_info`
--

LOCK TABLES `notification_info` WRITE;
/*!40000 ALTER TABLE `notification_info` DISABLE KEYS */;
INSERT INTO `notification_info` VALUES (1,'You have successfully rented the book Book Name',1,'USER'),(2,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(3,'You have successfully rented the book Book Name',1,'USER'),(4,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(5,'You have successfully rented the book Book Name',1,'USER'),(6,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(7,'You have successfully rented the book Book Name',1,'USER'),(8,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(9,'You have successfully rented the book Book Name',1,'USER'),(10,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(11,'You have successfully rented the book Book Name',1,'USER'),(12,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(13,'You have successfully rented the book Book Name',1,'USER'),(14,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(15,'You have successfully rented the book Book Name',1,'USER'),(16,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(17,'You have successfully rented the book Book Name',1,'USER'),(18,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(19,'You have successfully rented the book Book Name',1,'USER'),(20,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(21,'You have successfully rented the book Book Name',1,'USER'),(22,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(23,'You have successfully rented the book Book Name',1,'USER'),(24,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(25,'You have successfully rented the book Book Name',1,'USER'),(26,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(27,'You have successfully rented the book Book Name',1,'USER'),(28,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(29,'You have successfully rented the book Book Name',1,'USER'),(30,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(31,'You have successfully rented the book Book Name',1,'USER'),(32,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(33,'You have successfully rented the book Book Name',1,'USER'),(34,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(35,'You have successfully rented the book Book Name',1,'USER'),(36,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(37,'You have successfully rented the book Book Name',1,'USER'),(38,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(39,'You have successfully rented the book Book Name',1,'USER'),(40,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(41,'You have successfully rented the book Book Name',1,'USER'),(42,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(43,'You have successfully rented the book Book Name',1,'USER'),(44,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(45,'You have successfully rented the book Book Name',1,'USER'),(46,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(47,'You have successfully rented the book Book Name',1,'USER'),(48,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(49,'You have successfully rented the book Book Name',1,'USER'),(50,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(51,'You have successfully rented the book Book Name',1,'USER'),(52,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(53,'You have successfully rented the book Book Name',1,'USER'),(54,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(55,'You have successfully rented the book Book Name',1,'USER'),(56,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(57,'You have successfully rented the book Book Name',1,'USER'),(58,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(59,'You have successfully rented the book Book Name',1,'USER'),(60,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(61,'You have successfully rented the book Book Name',1,'USER'),(62,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(63,'You have successfully rented the book Book Name',1,'USER'),(64,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(65,'You have successfully rented the book Book Name',1,'USER'),(66,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(67,'You have successfully rented the book Book Name',1,'USER'),(68,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(69,'You have successfully rented the book Book Name',1,'USER'),(70,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(71,'You have successfully rented the book Book Name',1,'USER'),(72,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(73,'You have successfully rented the book Book Name',1,'USER'),(74,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(75,'You have successfully rented the book Book Name',1,'USER'),(76,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(77,'You have successfully rented the book Book Name',1,'USER'),(78,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(79,'You have successfully rented the book Book Name',1,'USER'),(80,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(81,'You have successfully rented the book Book Name',1,'USER'),(82,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(83,'You have successfully rented the book Book Name',1,'USER'),(84,'Veenal have successfully rented the book Book Name',1,'ADMIN'),(85,'You have successfully rented the book One Hundread Year of Solitude',1,'USER'),(86,'Veenal have successfully rented the book One Hundread Year of Solitude',1,'ADMIN'),(87,'You have successfully rented the book Advanced Java',1,'USER'),(88,'Veenal have successfully rented the book Advanced Java',1,'ADMIN'),(89,'You have successfully rented the book Advanced Java',1,'USER'),(90,'Veenal have successfully rented the book Advanced Java',1,'ADMIN'),(91,'You have successfully rented the book Advanced Java',1,'USER'),(92,'Veenal have successfully rented the book Advanced Java',1,'ADMIN');
/*!40000 ALTER TABLE `notification_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-30 14:59:15
