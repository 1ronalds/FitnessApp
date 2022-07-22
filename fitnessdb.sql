-- MySQL dump 10.13  Distrib 8.0.29, for Linux (x86_64)
--
-- Host: localhost    Database: fitnessdb
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
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
  `id` bigint NOT NULL,
  `description` varchar(355) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (19,'The Biceps are large muscle that lies on the front of the upper arm between the shoulder and the elbow. Building bicep strength helps you perform everyday tasks such as carrying and lifting.','Biceps'),(20,'Abdominal exercises are a type of strength exercise that affect the abdominal muscles globally known as abs. There are multiple ways to work on our abdominals but here are various abdominal exercises someone can do that are effective.','Abs'),(21,'Chest muscle exercises are performed to help these muscles develop and grow, along with maintaining a healthy and fit body. Most of the time, it involves pushing the arms away from the body or the body away from the arms.','Chest'),(22,'Shoulder training has several benefits beyond the aesthetic; it can promote healthy movement patterns and improve posture and strengthens the muscles surrounding the shoulder joint, creating more stability and an overall sturdier body structure.','Shoulder'),(23,'The triceps have three different heads: the long head, lateral head, and the medial head which contract during triceps exercises. The best way to build strong, firm triceps is to choose the exercises that hit all those muscle fibres from every angle.','Triceps'),(26,'Forearm exercises stretch and strengthen the muscles crossing your hands, wrists, and elbows. Strengthening your forearms also increases grip strength, which is related to upper body strength and eventually helps you carry, hold, and lift items in your everyday life and during athletic activity.','Forearms'),(27,'Back exercises are used in strength training to strengthen all the muscles of the back, or to target certain muscles within the back that are causing pain or various problems. When back exercises are performed properly, they help to add flexibility and strengthening to the back muscles, which helps to avoid injury.','Back'),(28,'The two legs comprise the lower extremity of the human body. The leg muscles are used for standing, walking, running, jumping, and other physical activities. Because the legs are so important to the human body, they need to be exercised on a regular basis to stay fit and healthy.','Legs');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exercise`
--

DROP TABLE IF EXISTS `exercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercise` (
  `id` bigint NOT NULL,
  `category_id` bigint NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise`
--

LOCK TABLES `exercise` WRITE;
/*!40000 ALTER TABLE `exercise` DISABLE KEYS */;
INSERT INTO `exercise` VALUES (55,0,'1.) Place the bar on the rack of a preacher bench and load with appropriate weight. 2.) Adjust height of seating and bench until the bench reaches a comfortable position under across the chest and under the armpits, while allowing full flexion and extension of the upper arms and forearms. 3.) Grasp bar with both hands and contract biceps to bring the bar towards the face. 4.) Once at full contraction, with a controlled motion reverse the bar’s direction and back until the biceps are extended the repetition.','Barbell Preacher Curl','/images/first.png'),(56,0,'Keep your back straight and take an underhand grip on the bar with your hands slightly more than shoulder-width apart: 1. Inhale, the curl the barbell. 2. Contract the gluteal, abdominal and back muscles isometrically to avoid torso swing. 3. Exhale as you complete the movement.','Barbell Bicep Curl','/images/third.jpg'),(57,0,'1. Take 2 dumbbells and stand with your legs shoulder apart, bend your knees slightly. Let your arms to hang down with your palms facing each other. 2. Moving only your forearms, slowly curl the dumbbells up to shoulder level. Turn your palms up when they pass your thighs. 3. Hold this position for a moment and slowly lower the dumbbell.','Dumbbell Bicep Curl','/images/fourth.jpg'),(58,0,'1.) Lie on the floor face up, with your knees bent and your feet on the floor. Place your hands behind your head. 2.) Slowly curl your torso and raise your shoulder and back from the floor, with your shoulders moving towards your thighs. 3.) Hold this position for a moment, contracting your abs and slowly return to the starting position.','Crunches','/images/fifth.png');
/*!40000 ALTER TABLE `exercise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (60);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-22  9:54:00
