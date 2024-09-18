-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: fooder
-- ------------------------------------------------------
-- Server version	8.0.39-0ubuntu0.20.04.1

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
-- Table structure for table `black_list_token`
--

DROP TABLE IF EXISTS `black_list_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `black_list_token` (
  `token_id` int NOT NULL,
  `create_at` varchar(45) DEFAULT NULL,
  `expired_at` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`token_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `black_list_token`
--

LOCK TABLES `black_list_token` WRITE;
/*!40000 ALTER TABLE `black_list_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `black_list_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `user_fkey` int NOT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `fk_cart_user1_idx` (`user_fkey`),
  CONSTRAINT `fk_cart_user1` FOREIGN KEY (`user_fkey`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (2,2);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_item`
--

DROP TABLE IF EXISTS `cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_item` (
  `cart_item_id` int NOT NULL AUTO_INCREMENT,
  `cart_fkey` int NOT NULL,
  `product_fkey` int NOT NULL,
  `qty` int DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`cart_item_id`),
  KEY `fk_cart_detail_cart1_idx` (`cart_fkey`),
  KEY `fk_cart_detail_product1_idx` (`product_fkey`),
  CONSTRAINT `fk_cart_detail_cart1` FOREIGN KEY (`cart_fkey`) REFERENCES `cart` (`cart_id`),
  CONSTRAINT `fk_cart_detail_product1` FOREIGN KEY (`product_fkey`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_item`
--

LOCK TABLES `cart_item` WRITE;
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
INSERT INTO `cart_item` VALUES (20,2,3,2,NULL);
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_modifier`
--

DROP TABLE IF EXISTS `cart_modifier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_modifier` (
  `cart_item_fkey` int NOT NULL,
  `modifier_fkey` int NOT NULL,
  `modifier_group_fkey` int NOT NULL,
  PRIMARY KEY (`cart_item_fkey`,`modifier_fkey`,`modifier_group_fkey`),
  KEY `fk_cart_modifier_cart_detail1_idx` (`cart_item_fkey`),
  KEY `fk_cart_modifier_modifier_option1_idx` (`modifier_fkey`),
  KEY `fk_cart_modifier_1_idx` (`modifier_group_fkey`),
  CONSTRAINT `fk_cart_modifier_1` FOREIGN KEY (`modifier_group_fkey`) REFERENCES `modifier_group` (`modifier_group_id`),
  CONSTRAINT `fk_cart_modifier_cart_detail1` FOREIGN KEY (`cart_item_fkey`) REFERENCES `cart_item` (`cart_item_id`),
  CONSTRAINT `fk_cart_modifier_modifier_option1` FOREIGN KEY (`modifier_fkey`) REFERENCES `modifier` (`modifier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_modifier`
--

LOCK TABLES `cart_modifier` WRITE;
/*!40000 ALTER TABLE `cart_modifier` DISABLE KEYS */;
INSERT INTO `cart_modifier` VALUES (20,3,3),(20,3,4);
/*!40000 ALTER TABLE `cart_modifier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `comment_id` int NOT NULL,
  `user_fkey` int NOT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `lft` int DEFAULT NULL,
  `rgt` int DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `fk_review_user1_idx` (`user_fkey`),
  CONSTRAINT `fk_review_user1` FOREIGN KEY (`user_fkey`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_address`
--

DROP TABLE IF EXISTS `customer_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_address` (
  `customer_address_id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(50) NOT NULL,
  `country` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `user_fkey` int NOT NULL,
  `note` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`customer_address_id`),
  KEY `fk_customer_address_user2_idx` (`user_fkey`),
  CONSTRAINT `fk_customer_address_user2` FOREIGN KEY (`user_fkey`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_address`
--

LOCK TABLES `customer_address` WRITE;
/*!40000 ALTER TABLE `customer_address` DISABLE KEYS */;
INSERT INTO `customer_address` VALUES (2,'123','123','123123','123',2,NULL);
/*!40000 ALTER TABLE `customer_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `menu_id` int NOT NULL AUTO_INCREMENT,
  `restaurant_fkey` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`menu_id`),
  KEY `fk_menu_restaurant1_idx` (`restaurant_fkey`),
  CONSTRAINT `fk_menu_restaurant1` FOREIGN KEY (`restaurant_fkey`) REFERENCES `restaurant` (`restaurant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,6,'sang',NULL);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_category`
--

DROP TABLE IF EXISTS `menu_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu_category` (
  `menu_category_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `restaurant_fkey` int DEFAULT NULL,
  PRIMARY KEY (`menu_category_id`),
  KEY `fk_menu_category_1_idx` (`restaurant_fkey`),
  CONSTRAINT `fk_menu_category_1` FOREIGN KEY (`restaurant_fkey`) REFERENCES `restaurant` (`restaurant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_category`
--

LOCK TABLES `menu_category` WRITE;
/*!40000 ALTER TABLE `menu_category` DISABLE KEYS */;
INSERT INTO `menu_category` VALUES (1,'thuc an',NULL,6),(3,'Menu2 2',NULL,6);
/*!40000 ALTER TABLE `menu_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_category_has_product`
--

DROP TABLE IF EXISTS `menu_category_has_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu_category_has_product` (
  `menu_category_fkey` int NOT NULL,
  `product_fkey` int NOT NULL,
  PRIMARY KEY (`menu_category_fkey`,`product_fkey`),
  KEY `fk_menu_category_has_product_1_idx` (`product_fkey`),
  CONSTRAINT `fk_menu_category_has_product_1` FOREIGN KEY (`product_fkey`) REFERENCES `product` (`product_id`),
  CONSTRAINT `fk_menu_category_has_product_2` FOREIGN KEY (`menu_category_fkey`) REFERENCES `menu_category` (`menu_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_category_has_product`
--

LOCK TABLES `menu_category_has_product` WRITE;
/*!40000 ALTER TABLE `menu_category_has_product` DISABLE KEYS */;
INSERT INTO `menu_category_has_product` VALUES (1,3),(3,3),(1,4),(3,4);
/*!40000 ALTER TABLE `menu_category_has_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_has_category`
--

DROP TABLE IF EXISTS `menu_has_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu_has_category` (
  `menu_fkey` int NOT NULL,
  `menu_category_fkey` int NOT NULL,
  PRIMARY KEY (`menu_fkey`,`menu_category_fkey`),
  KEY `fk_menu_has_category_2_idx` (`menu_category_fkey`),
  CONSTRAINT `fk_menu_has_category_1` FOREIGN KEY (`menu_fkey`) REFERENCES `menu` (`menu_id`),
  CONSTRAINT `fk_menu_has_category_2` FOREIGN KEY (`menu_category_fkey`) REFERENCES `menu_category` (`menu_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_has_category`
--

LOCK TABLES `menu_has_category` WRITE;
/*!40000 ALTER TABLE `menu_has_category` DISABLE KEYS */;
INSERT INTO `menu_has_category` VALUES (1,3);
/*!40000 ALTER TABLE `menu_has_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modifier`
--

DROP TABLE IF EXISTS `modifier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `modifier` (
  `modifier_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `modifier_group_fkey` int NOT NULL,
  PRIMARY KEY (`modifier_id`),
  KEY `fk_modifier_modifier_group1_idx` (`modifier_group_fkey`),
  CONSTRAINT `fk_modifier_modifier_group1` FOREIGN KEY (`modifier_group_fkey`) REFERENCES `modifier_group` (`modifier_group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modifier`
--

LOCK TABLES `modifier` WRITE;
/*!40000 ALTER TABLE `modifier` DISABLE KEYS */;
INSERT INTO `modifier` VALUES (3,'100%',1000,1,3),(4,'50%',50,1,3),(5,'100%',12,1,4),(6,'50%',12,1,4);
/*!40000 ALTER TABLE `modifier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modifier_group`
--

DROP TABLE IF EXISTS `modifier_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `modifier_group` (
  `modifier_group_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`modifier_group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modifier_group`
--

LOCK TABLES `modifier_group` WRITE;
/*!40000 ALTER TABLE `modifier_group` DISABLE KEYS */;
INSERT INTO `modifier_group` VALUES (3,'da'),(4,'duong');
/*!40000 ALTER TABLE `modifier_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification_subscription`
--

DROP TABLE IF EXISTS `notification_subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification_subscription` (
  `user_fkey` int NOT NULL,
  `restaurant_fkey` int NOT NULL,
  `subscription_date` datetime DEFAULT NULL,
  PRIMARY KEY (`user_fkey`,`restaurant_fkey`),
  KEY `fk_notification_subscription_user2_idx` (`user_fkey`),
  KEY `fk_notification_subscription_restaurant2_idx` (`restaurant_fkey`),
  CONSTRAINT `fk_notification_subscription_restaurant2` FOREIGN KEY (`restaurant_fkey`) REFERENCES `restaurant` (`restaurant_id`),
  CONSTRAINT `fk_notification_subscription_user2` FOREIGN KEY (`user_fkey`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification_subscription`
--

LOCK TABLES `notification_subscription` WRITE;
/*!40000 ALTER TABLE `notification_subscription` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification_subscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_cancel`
--

DROP TABLE IF EXISTS `order_cancel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_cancel` (
  `order_id` int NOT NULL,
  `reason` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  CONSTRAINT `fk_order_cancel_1` FOREIGN KEY (`order_id`) REFERENCES `order_tb` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_cancel`
--

LOCK TABLES `order_cancel` WRITE;
/*!40000 ALTER TABLE `order_cancel` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_cancel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `order_item_id` int NOT NULL AUTO_INCREMENT,
  `order_fkey` int NOT NULL,
  `product_fkey` int NOT NULL,
  `qty` int DEFAULT NULL,
  `price` float DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`order_item_id`),
  KEY `fk_order_detail_order1_idx` (`order_fkey`),
  KEY `fk_order_detail_product1_idx` (`product_fkey`),
  CONSTRAINT `fk_order_detail_order1` FOREIGN KEY (`order_fkey`) REFERENCES `order_tb` (`order_id`),
  CONSTRAINT `fk_order_detail_product1` FOREIGN KEY (`product_fkey`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (3,3,3,12,321,NULL,'123123');
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_modifier`
--

DROP TABLE IF EXISTS `order_modifier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_modifier` (
  `order_item_fkey` int NOT NULL,
  `modifier_fkey` int NOT NULL,
  `modifier_group_fkey` int NOT NULL,
  PRIMARY KEY (`order_item_fkey`,`modifier_fkey`,`modifier_group_fkey`),
  KEY `fk_order_modifier_order_item1_idx` (`order_item_fkey`),
  KEY `fk_order_modifier_modifier_option1_idx` (`modifier_fkey`),
  KEY `fk_order_modifier_1_idx` (`modifier_group_fkey`),
  CONSTRAINT `fk_order_modifier_1` FOREIGN KEY (`modifier_group_fkey`) REFERENCES `modifier_group` (`modifier_group_id`),
  CONSTRAINT `fk_order_modifier_modifier_option1` FOREIGN KEY (`modifier_fkey`) REFERENCES `modifier` (`modifier_id`),
  CONSTRAINT `fk_order_modifier_order_item1` FOREIGN KEY (`order_item_fkey`) REFERENCES `order_item` (`order_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_modifier`
--

LOCK TABLES `order_modifier` WRITE;
/*!40000 ALTER TABLE `order_modifier` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_modifier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_status`
--

DROP TABLE IF EXISTS `order_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_status` (
  `order_status_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`order_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_status`
--

LOCK TABLES `order_status` WRITE;
/*!40000 ALTER TABLE `order_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_tb`
--

DROP TABLE IF EXISTS `order_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_tb` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `shipping_fee` varchar(45) DEFAULT NULL,
  `submit_time` datetime DEFAULT NULL,
  `status` enum('PENDING','CONFIRMED','DELIVERING','DELIVERED') DEFAULT NULL,
  `customer_address_fkey` int NOT NULL,
  `payment_method_fkey` int DEFAULT NULL,
  `restaurant_fkey` int NOT NULL,
  `user_fkey` int NOT NULL,
  `complete_time` datetime DEFAULT NULL,
  `note` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `fk_order_customer_address1_idx` (`customer_address_fkey`),
  KEY `fk_order_payment_method1_idx` (`payment_method_fkey`),
  KEY `fk_order_restaurant1_idx` (`restaurant_fkey`),
  KEY `fk_order_tb_1_idx` (`user_fkey`),
  CONSTRAINT `fk_order_customer_address1` FOREIGN KEY (`customer_address_fkey`) REFERENCES `customer_address` (`customer_address_id`),
  CONSTRAINT `fk_order_payment_method1` FOREIGN KEY (`payment_method_fkey`) REFERENCES `payment_method` (`payment_method_id`),
  CONSTRAINT `fk_order_restaurant1` FOREIGN KEY (`restaurant_fkey`) REFERENCES `restaurant` (`restaurant_id`),
  CONSTRAINT `fk_order_tb_1` FOREIGN KEY (`user_fkey`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_tb`
--

LOCK TABLES `order_tb` WRITE;
/*!40000 ALTER TABLE `order_tb` DISABLE KEYS */;
INSERT INTO `order_tb` VALUES (3,'12',NULL,'PENDING',2,NULL,6,2,NULL,NULL);
/*!40000 ALTER TABLE `order_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_method`
--

DROP TABLE IF EXISTS `payment_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_method` (
  `payment_method_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`payment_method_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_method`
--

LOCK TABLES `payment_method` WRITE;
/*!40000 ALTER TABLE `payment_method` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `period_time`
--

DROP TABLE IF EXISTS `period_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `period_time` (
  `period_time_id` int NOT NULL AUTO_INCREMENT,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `selling_time_fkey` int NOT NULL,
  PRIMARY KEY (`period_time_id`),
  KEY `fk_period_time_1_idx` (`selling_time_fkey`),
  CONSTRAINT `fk_period_time_1` FOREIGN KEY (`selling_time_fkey`) REFERENCES `selling_time` (`selling_time_id`)
) ENGINE=InnoDB AUTO_INCREMENT=204 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `period_time`
--

LOCK TABLES `period_time` WRITE;
/*!40000 ALTER TABLE `period_time` DISABLE KEYS */;
INSERT INTO `period_time` VALUES (197,'06:00:00','06:00:00',254),(198,'06:00:00','06:00:00',255),(199,'06:00:00','06:00:00',256),(200,'06:00:00','06:00:00',257),(201,'06:00:00','06:00:00',258),(202,'06:00:00','06:00:00',259),(203,'06:00:00','07:00:00',260);
/*!40000 ALTER TABLE `period_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `thumbnail` varchar(45) DEFAULT NULL,
  `product_category_fkey` int NOT NULL,
  `restaurant_fkey` int NOT NULL,
  PRIMARY KEY (`product_id`),
  KEY `fk_product_category1_idx` (`product_category_fkey`),
  KEY `fk_product_restaurant1_idx` (`restaurant_fkey`),
  CONSTRAINT `fk_product_1` FOREIGN KEY (`product_category_fkey`) REFERENCES `product_category` (`product_category_id`),
  CONSTRAINT `fk_product_2` FOREIGN KEY (`restaurant_fkey`) REFERENCES `restaurant` (`restaurant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (3,'Nodle',1,'wow so beautiful',1233,'123',5,6),(4,'banh uot',1,NULL,23,'23',6,6);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_category` (
  `product_category_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`product_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` VALUES (5,'cafe'),(6,'banh');
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_comment`
--

DROP TABLE IF EXISTS `product_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_comment` (
  `comment_fkey` int NOT NULL,
  `product_fkey` int NOT NULL,
  `star_count` int DEFAULT NULL,
  PRIMARY KEY (`comment_fkey`,`product_fkey`),
  KEY `fk_product_review_product1_idx` (`product_fkey`),
  KEY `fk_product_review_review1_idx` (`comment_fkey`),
  CONSTRAINT `fk_product_review_product1` FOREIGN KEY (`product_fkey`) REFERENCES `product` (`product_id`),
  CONSTRAINT `fk_product_review_review1` FOREIGN KEY (`comment_fkey`) REFERENCES `comment` (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_comment`
--

LOCK TABLES `product_comment` WRITE;
/*!40000 ALTER TABLE `product_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_image`
--

DROP TABLE IF EXISTS `product_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_image` (
  `product_fkey` int NOT NULL,
  `image_path` varchar(70) NOT NULL,
  PRIMARY KEY (`image_path`),
  KEY `fk_product_image_product2_idx` (`product_fkey`),
  CONSTRAINT `fk_product_image_product2` FOREIGN KEY (`product_fkey`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_image`
--

LOCK TABLES `product_image` WRITE;
/*!40000 ALTER TABLE `product_image` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_modifier_group`
--

DROP TABLE IF EXISTS `product_modifier_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_modifier_group` (
  `modifier_group_fkey` int NOT NULL,
  `product_fkey` int NOT NULL,
  PRIMARY KEY (`modifier_group_fkey`,`product_fkey`),
  KEY `fk_product_modifier_group_modifier_group1_idx` (`modifier_group_fkey`),
  KEY `fk_product_modifier_group_product1_idx` (`product_fkey`),
  CONSTRAINT `fk_product_modifier_group_modifier_group1` FOREIGN KEY (`modifier_group_fkey`) REFERENCES `modifier_group` (`modifier_group_id`),
  CONSTRAINT `fk_product_modifier_group_product1` FOREIGN KEY (`product_fkey`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_modifier_group`
--

LOCK TABLES `product_modifier_group` WRITE;
/*!40000 ALTER TABLE `product_modifier_group` DISABLE KEYS */;
INSERT INTO `product_modifier_group` VALUES (3,3),(3,4),(4,3);
/*!40000 ALTER TABLE `product_modifier_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurant` (
  `restaurant_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `avatar_image` varchar(45) DEFAULT NULL,
  `background_image` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `user_fkey` int NOT NULL,
  PRIMARY KEY (`restaurant_id`),
  KEY `fk_restaurant_user1_idx` (`user_fkey`),
  CONSTRAINT `fk_restaurant_user1` FOREIGN KEY (`user_fkey`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant`
--

LOCK TABLES `restaurant` WRITE;
/*!40000 ALTER TABLE `restaurant` DISABLE KEYS */;
INSERT INTO `restaurant` VALUES (6,'thd',NULL,NULL,NULL,NULL,NULL,2);
/*!40000 ALTER TABLE `restaurant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant_comment`
--

DROP TABLE IF EXISTS `restaurant_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurant_comment` (
  `comment_fkey` int NOT NULL,
  `restaurant_fkey` int NOT NULL,
  `star_count` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`comment_fkey`,`restaurant_fkey`),
  KEY `fk_restaurant_review_review1_idx` (`comment_fkey`),
  KEY `fk_restaurant_review_restaurant1_idx` (`restaurant_fkey`),
  CONSTRAINT `fk_restaurant_review_restaurant1` FOREIGN KEY (`restaurant_fkey`) REFERENCES `restaurant` (`restaurant_id`),
  CONSTRAINT `fk_restaurant_review_review1` FOREIGN KEY (`comment_fkey`) REFERENCES `comment` (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_comment`
--

LOCK TABLES `restaurant_comment` WRITE;
/*!40000 ALTER TABLE `restaurant_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `restaurant_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `selling_time`
--

DROP TABLE IF EXISTS `selling_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `selling_time` (
  `selling_time_id` int NOT NULL AUTO_INCREMENT,
  `date_of_week` int DEFAULT NULL,
  `valid_from` date DEFAULT NULL,
  `valid_through` date DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `menu_fkey` int NOT NULL,
  PRIMARY KEY (`selling_time_id`),
  KEY `fk_selling_time_1_idx` (`menu_fkey`),
  CONSTRAINT `fk_selling_time_1` FOREIGN KEY (`menu_fkey`) REFERENCES `menu` (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=261 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `selling_time`
--

LOCK TABLES `selling_time` WRITE;
/*!40000 ALTER TABLE `selling_time` DISABLE KEYS */;
INSERT INTO `selling_time` VALUES (254,0,NULL,NULL,_binary '',1),(255,1,NULL,NULL,_binary '',1),(256,2,NULL,NULL,_binary '',1),(257,3,NULL,NULL,_binary '',1),(258,4,NULL,NULL,_binary '',1),(259,5,NULL,NULL,_binary '',1),(260,6,NULL,NULL,_binary '',1);
/*!40000 ALTER TABLE `selling_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `gender` tinyint DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `birthdate` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `avatar` varchar(45) DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `id_UNIQUE` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'12',NULL,NULL,NULL,NULL,NULL,'123','$2a$12$UfB93uFqTYLpyBJe/GIH.O4RGvqSQhFVVGwNizoBN7ERFQZGQLh2y',NULL,NULL,NULL,NULL),(3,'1234',NULL,NULL,NULL,NULL,NULL,'123','aha',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_fkey` int NOT NULL,
  `role_fkey` int NOT NULL,
  PRIMARY KEY (`role_fkey`,`user_fkey`),
  KEY `fk_table1_User_idx` (`user_fkey`),
  KEY `fk_table1_User_Role1_idx` (`role_fkey`),
  CONSTRAINT `fk_table1_User` FOREIGN KEY (`user_fkey`) REFERENCES `user` (`user_id`),
  CONSTRAINT `fk_table1_User_Role1` FOREIGN KEY (`role_fkey`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-19  5:36:00
