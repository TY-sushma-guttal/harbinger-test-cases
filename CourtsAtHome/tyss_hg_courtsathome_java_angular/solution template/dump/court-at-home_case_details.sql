-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: court-at-home
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
-- Table structure for table `case_details`
--

DROP TABLE IF EXISTS `case_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `case_details` (
  `case_id` int NOT NULL AUTO_INCREMENT,
  `case_name` varchar(255) DEFAULT NULL,
  `datetime` datetime DEFAULT NULL,
  `fine` double DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_details_user_id` int DEFAULT NULL,
  `vehicle_details_vehicle_no` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`case_id`),
  KEY `FKhoctx11b0hqqbuwkhita5ulkd` (`user_details_user_id`),
  KEY `FK4fqmvtxcjq0lwp8vjodw2mwqo` (`vehicle_details_vehicle_no`),
  CONSTRAINT `FK4fqmvtxcjq0lwp8vjodw2mwqo` FOREIGN KEY (`vehicle_details_vehicle_no`) REFERENCES `vehicle_details` (`vehicle_no`),
  CONSTRAINT `FKhoctx11b0hqqbuwkhita5ulkd` FOREIGN KEY (`user_details_user_id`) REFERENCES `user_details` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `case_details`
--

LOCK TABLES `case_details` WRITE;
/*!40000 ALTER TABLE `case_details` DISABLE KEYS */;
INSERT INTO `case_details` VALUES (1,'Reckless Driving','2023-01-01 07:42:12',2000,'Active',1,'HR 26 DQ 5551'),(2,'Reckless Driving','2023-01-01 08:00:12',2000,'Closed',1,'HR 26 DQ 5551'),(3,'Running a Red Light','2023-02-01 08:00:12',2000,'Closed',1,'KA 19 EQ 1316'),(4,'Running a Red Light','2023-02-01 06:00:12',2000,'Active',1,'KA 19 EQ 1316'),(5,'Distracted Driving','2023-02-01 06:00:12',3000,'Active',2,'KA 32 AB 1234'),(6,'Distracted Driving','2023-02-01 06:30:12',3000,'Active',2,'KA 32 AB 1234'),(7,'Driving Under the Influence','2023-02-01 06:30:12',3000,'Active',2,'KA 32 AB 1234'),(8,'Driving Under the Influence','2023-01-01 05:30:12',3000,'Open',2,'KA 32 AB 1234');
/*!40000 ALTER TABLE `case_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-31 13:21:22
