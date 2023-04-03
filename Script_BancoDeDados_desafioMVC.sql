-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: desafio_mvc
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `atividade`
--

DROP TABLE IF EXISTS `atividade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `atividade` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_de_entrega_atividade` date DEFAULT NULL,
  `data_de_inicio_atividade` date DEFAULT NULL,
  `nome_atividade` varchar(255) DEFAULT NULL,
  `evento_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4ywqk697o5ix113ein2yjengc` (`evento_id`),
  CONSTRAINT `FK4ywqk697o5ix113ein2yjengc` FOREIGN KEY (`evento_id`) REFERENCES `evento` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atividade`
--

LOCK TABLES `atividade` WRITE;
/*!40000 ALTER TABLE `atividade` DISABLE KEYS */;
INSERT INTO `atividade` VALUES (5,'2022-10-21','2022-10-19','Atividade 2',1),(6,'2022-10-26','2022-10-24','Atividade 3',1),(7,'2022-10-28','2022-10-26','Atividade 4',1),(8,'2022-10-19','2022-10-17','Atividade 1',2),(9,'2022-10-21','2022-10-19','Atividade 2',2),(10,'2022-10-26','2022-10-24','Atividade 3',2),(11,'2022-10-28','2022-10-26','Atividade 4',2),(12,'2022-10-19','2022-10-17','Atividade 1',3),(13,'2022-10-21','2022-10-19','Atividade 2',3),(14,'2022-10-26','2022-10-24','Atividade 3',3),(15,'2022-10-28','2022-10-26','Atividade 4',3);
/*!40000 ALTER TABLE `atividade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrega`
--

DROP TABLE IF EXISTS `entrega`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrega` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `entrega` bit(1) NOT NULL,
  `atividade_id` bigint DEFAULT NULL,
  `participante_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKie24lxg0dl91pf9gsc9k94l0a` (`atividade_id`),
  KEY `FK14tr6ijt1o19d0k3vmn9uibo0` (`participante_id`),
  CONSTRAINT `FK14tr6ijt1o19d0k3vmn9uibo0` FOREIGN KEY (`participante_id`) REFERENCES `participante_do_evento` (`id`),
  CONSTRAINT `FKie24lxg0dl91pf9gsc9k94l0a` FOREIGN KEY (`atividade_id`) REFERENCES `atividade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrega`
--

LOCK TABLES `entrega` WRITE;
/*!40000 ALTER TABLE `entrega` DISABLE KEYS */;
INSERT INTO `entrega` VALUES (2,_binary '',12,2),(3,_binary '',12,1),(4,_binary '',14,1),(6,_binary '',13,1);
/*!40000 ALTER TABLE `entrega` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evento`
--

DROP TABLE IF EXISTS `evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_de_inicio` date DEFAULT NULL,
  `data_final` date DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evento`
--

LOCK TABLES `evento` WRITE;
/*!40000 ALTER TABLE `evento` DISABLE KEYS */;
INSERT INTO `evento` VALUES (1,'2022-09-12','2022-09-23','Desafio POO'),(2,'2022-09-18','2022-09-30','Desafio TDD'),(3,'2022-10-03','2022-10-28','Desafio MVC');
/*!40000 ALTER TABLE `evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome_grupo` varchar(255) DEFAULT NULL,
  `quant_pessoas` bigint NOT NULL,
  `evento_id` bigint DEFAULT NULL,
  `pontuacao_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK10452bsdi0doslhq6nygatl84` (`evento_id`),
  KEY `FK179avyplfefgt5ovqn95vuqnx` (`pontuacao_id`),
  CONSTRAINT `FK10452bsdi0doslhq6nygatl84` FOREIGN KEY (`evento_id`) REFERENCES `evento` (`id`),
  CONSTRAINT `FK179avyplfefgt5ovqn95vuqnx` FOREIGN KEY (`pontuacao_id`) REFERENCES `pontuacao` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo`
--

LOCK TABLES `grupo` WRITE;
/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
INSERT INTO `grupo` VALUES (6,'Grupo1_MVC',4,3,NULL),(7,'Grupo2_MVC',1,3,NULL),(8,'Grupo3_MVC',5,3,NULL),(9,'Grupo4_MVC',7,3,NULL),(10,'Grupo1_TDD',5,2,NULL),(11,'Grupo2_TDD',3,2,NULL),(12,'Grupo3_TDD',4,2,NULL),(13,'Grupo4_TDD',1,2,NULL),(14,'Grupo1_POO',2,1,NULL),(15,'Grupo2_POO',1,1,NULL),(16,'Grupo3_POO',1,1,NULL),(17,'Grupo4_POO',1,1,NULL);
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participante_do_evento`
--

DROP TABLE IF EXISTS `participante_do_evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `participante_do_evento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nivel` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `quatro_letras` varchar(4) DEFAULT NULL,
  `grupo_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKeoe24lcmxo68y5t4smqkopxjl` (`grupo_id`),
  CONSTRAINT `FKeoe24lcmxo68y5t4smqkopxjl` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participante_do_evento`
--

LOCK TABLES `participante_do_evento` WRITE;
/*!40000 ALTER TABLE `participante_do_evento` DISABLE KEYS */;
INSERT INTO `participante_do_evento` VALUES (1,'dase@gft.com','L1','Participante1','dase',6),(2,'sdad@gft.com','L1','Participante2','sdad',6),(3,'dsae@gft.com','L1','Participante3','dsae',6),(4,'khbj@gft.com','L3','Participante4','khbj',6),(18,'ojia@gft.com','L3','Participante3','ojia',8),(19,'klhb@gft.com','L4','Participante4','klhb',8),(20,'huji@gft.com','L5','Participante5','huji',8),(21,'gvoi@gft.com','L6','Participante6','gvoi',8),(22,'fghi@gft.com','L7','Participante7','fghi',8),(23,'gaae@gft.com','L1','Participante1','gaae',9),(24,'gyui@gft.com','L2','Participante2','gyui',9),(25,'uiop@gft.com','L3','Participante3','uiop',9),(26,'huio@gft.com','L4','Participante4','huio',9),(27,'igyl@gft.com','L5','Participante5','igyl',9),(28,'vyit@gft.com','L6','Participante6','vyit',9),(29,'kgyh@gft.com','L7','Participante7','kgyh',9),(30,'uioh@gft.com','L1','Participante1','uioh',10),(31,'klhu@gft.com','L2','Participante2','klhu',10),(32,'oihj@gft.com','L3','Participante3','oihj',10),(34,'uigh@gft.com','L5','Participante4','uigh',10),(35,'ohup@gft.com','L7','Participante5','ohup',10),(36,'gesr@gft.com','L1','Participante1','gesr',11),(37,'ghuj@gft.com','L7','Participante2','ghuj',11),(38,'gaes@gft.com','L7','Participante3','gaes',11),(39,'gaes@gft.com','L6','Participante1','gaes',12),(40,'ikhu@gft.com','L5','Participante2','ikhu',12),(41,'hujs@gft.com','L7','Participante3','hujs',12),(42,'gaea@gft.com','L7','Participante4','gaea',12),(43,'igyu@gft.com','L1','Participante1','igyu',14),(44,'igyu@gft.com','L3','Participante1','igyu',14),(46,'huio@gft.com','L1','Participante1','huio',13),(47,'huis@gft.com','L1','Participante1','huis',15),(48,'sadf@gft.com','L1','Participante1','sadf',16),(49,'ghas@gft.com','L1','Participante1','ghas',17),(51,'hdss@gft.com','L1','Participante1','hdss',7);
/*!40000 ALTER TABLE `participante_do_evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pontuacao`
--

DROP TABLE IF EXISTS `pontuacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pontuacao` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pontos` bigint DEFAULT NULL,
  `grupo_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl9icvmq78wf12s43cjvn7xjk8` (`grupo_id`),
  CONSTRAINT `FKl9icvmq78wf12s43cjvn7xjk8` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pontuacao`
--

LOCK TABLES `pontuacao` WRITE;
/*!40000 ALTER TABLE `pontuacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `pontuacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presenca`
--

DROP TABLE IF EXISTS `presenca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `presenca` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  `presente` bit(1) NOT NULL,
  `participante_do_evento_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7c9kj0cl25t0yrdonmk4pbtjt` (`participante_do_evento_id`),
  CONSTRAINT `FK7c9kj0cl25t0yrdonmk4pbtjt` FOREIGN KEY (`participante_do_evento_id`) REFERENCES `participante_do_evento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presenca`
--

LOCK TABLES `presenca` WRITE;
/*!40000 ALTER TABLE `presenca` DISABLE KEYS */;
/*!40000 ALTER TABLE `presenca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nome_usuario` varchar(255) DEFAULT NULL,
  `quatro_letras` varchar(4) NOT NULL,
  `senha` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_5lf4iboda7ogywgd60esrhhjc` (`quatro_letras`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'aefr@gft.com','Alejunior','aefr','1234'),(3,'aefr@gft.com','Alexandre','alre','org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder@370a804e'),(4,'aefr@gft.com','Alejunior32','aljr','$2a$10$G85fLlmQI3N1iYmkeE3ykeDG.SOH.23z6VZ7cimKrmCD7Bb7bM5fS');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'desafio_mvc'
--

--
-- Dumping routines for database 'desafio_mvc'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-28  9:44:15
