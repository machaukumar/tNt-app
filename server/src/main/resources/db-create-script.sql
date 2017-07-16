-- Dumping database structure for concretepage
CREATE DATABASE IF NOT EXISTS `tutorialdb`;
USE `tutorialdb`;
-- Dumping structure for table concretepage.tutorials
CREATE TABLE IF NOT EXISTS `tutorials` (
  `tutorial_id` int(5) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `category` varchar(100) NOT NULL,
  PRIMARY KEY (`tutorial_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
-- Dumping data for table concretepage.tutorials: ~4 rows (approximately)
/*!40000 ALTER TABLE `tutorials` DISABLE KEYS */;
INSERT INTO `tutorials` (`tutorial_id`, `title`, `category`) VALUES
	(1, 'Angular 2 Tutorial using CLI', 'Angular'),
	(2, 'Spring Boot Getting Started', 'Spring Boot'),
	(3, 'Lambda Expressions Java 8 Example', 'Java 8'),
	(4, 'Android AsyncTask Example', 'Android'); 