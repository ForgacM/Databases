# ************************************************************
# Sequel Pro SQL dump
# Version 4135
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: mariadb55.websupport.sk (MySQL 5.5.34-MariaDB-1~precise-log)
# Database: ikvb6ju5
# Generation Time: 2015-05-03 21:03:42 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table doctor
# ------------------------------------------------------------

DROP TABLE IF EXISTS `doctor`;

CREATE TABLE `doctor` (
  `doctor_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `doctor_type_id` int(11) NOT NULL,
  `login` text NOT NULL,
  PRIMARY KEY (`doctor_id`),
  KEY `fk_doctor_doctor_type1_idx` (`doctor_type_id`),
  CONSTRAINT `doctor_ibfk_1` FOREIGN KEY (`doctor_type_id`) REFERENCES `doctor_type` (`doctor_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;

INSERT INTO `doctor` (`doctor_id`, `name`, `doctor_type_id`, `login`)
VALUES
	(1,'Mudr. Vazny',1,'vaz'),
	(2,'Mudr. Bystra',2,'bys'),
	(3,'Mudr. Zlaty',3,'zla');

/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table doctor_patient
# ------------------------------------------------------------

DROP TABLE IF EXISTS `doctor_patient`;

CREATE TABLE `doctor_patient` (
  `dp_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `doctor_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `registered` text,
  PRIMARY KEY (`dp_id`),
  KEY `doctor_id` (`doctor_id`),
  KEY `patient_id` (`patient_id`),
  CONSTRAINT `doctor_patient_ibfk_1` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`),
  CONSTRAINT `doctor_patient_ibfk_2` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

LOCK TABLES `doctor_patient` WRITE;
/*!40000 ALTER TABLE `doctor_patient` DISABLE KEYS */;

INSERT INTO `doctor_patient` (`dp_id`, `doctor_id`, `patient_id`, `registered`)
VALUES
	(1,1,3,'21.7.1999');

/*!40000 ALTER TABLE `doctor_patient` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table doctor_type
# ------------------------------------------------------------

DROP TABLE IF EXISTS `doctor_type`;

CREATE TABLE `doctor_type` (
  `doctor_type_id` int(11) NOT NULL,
  `zameranie` text,
  PRIMARY KEY (`doctor_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `doctor_type` WRITE;
/*!40000 ALTER TABLE `doctor_type` DISABLE KEYS */;

INSERT INTO `doctor_type` (`doctor_type_id`, `zameranie`)
VALUES
	(1,'vseobecny'),
	(2,'ocny'),
	(3,'gynekolog');

/*!40000 ALTER TABLE `doctor_type` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table drug_type
# ------------------------------------------------------------

DROP TABLE IF EXISTS `drug_type`;

CREATE TABLE `drug_type` (
  `drug_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `typ_lieku` text,
  PRIMARY KEY (`drug_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

LOCK TABLES `drug_type` WRITE;
/*!40000 ALTER TABLE `drug_type` DISABLE KEYS */;

INSERT INTO `drug_type` (`drug_type_id`, `typ_lieku`)
VALUES
	(1,'Tablety'),
	(2,'mast'),
	(3,'kvapky'),
	(4,'doplnok vyzivy');

/*!40000 ALTER TABLE `drug_type` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table drugs
# ------------------------------------------------------------

DROP TABLE IF EXISTS `drugs`;

CREATE TABLE `drugs` (
  `drug_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text,
  `count` int(11) DEFAULT NULL,
  `drug_type_id` int(11) NOT NULL,
  `recept` tinyint(1) DEFAULT NULL,
  `state` int(11) DEFAULT '0',
  PRIMARY KEY (`drug_id`),
  KEY `fk_drugs_drug_type1_idx` (`drug_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

LOCK TABLES `drugs` WRITE;
/*!40000 ALTER TABLE `drugs` DISABLE KEYS */;

INSERT INTO `drugs` (`drug_id`, `name`, `count`, `drug_type_id`, `recept`, `state`)
VALUES
	(1,'Panadol',10,1,0,0),
	(2,'Paralen',5,1,0,0),
	(3,'Ibalgin',10,1,0,0),
	(4,'Opium',3,3,1,0),
	(5,'Fastumgel',8,2,0,0),
	(6,'Sleeper',5,1,1,0),
	(7,'Hroznovy cukor',50,4,0,0),
	(8,'Kvapky do oci',10,3,0,0),
	(9,'Antikoncepcia',7,1,1,0),
	(13,'Exicp',20,2,1,0),
	(19,'Usol',22,3,0,0);

/*!40000 ALTER TABLE `drugs` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table items
# ------------------------------------------------------------

DROP TABLE IF EXISTS `items`;

CREATE TABLE `items` (
  `items_id` int(11) NOT NULL AUTO_INCREMENT,
  `recept_id` int(11) DEFAULT NULL,
  `pocet_kusov` int(11) DEFAULT '1',
  `order_id` int(11) NOT NULL,
  `drug_id` int(11) NOT NULL,
  PRIMARY KEY (`items_id`),
  KEY `fk_drugs_id` (`drug_id`),
  KEY `fk_order_id` (`order_id`),
  KEY `fk_recept_id` (`recept_id`),
  CONSTRAINT `fk_recept_id` FOREIGN KEY (`recept_id`) REFERENCES `recept` (`recept_id`),
  CONSTRAINT `fk_drugs_id` FOREIGN KEY (`drug_id`) REFERENCES `drugs` (`drug_id`),
  CONSTRAINT `fk_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;

INSERT INTO `items` (`items_id`, `recept_id`, `pocet_kusov`, `order_id`, `drug_id`)
VALUES
	(93,NULL,1,52,3),
	(94,NULL,1,53,3),
	(95,NULL,1,53,8),
	(96,NULL,1,54,3),
	(97,NULL,1,54,8),
	(98,NULL,1,55,3),
	(99,NULL,1,55,6);

/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table orders
# ------------------------------------------------------------

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `date` text,
  `serial_number` int(11) DEFAULT NULL,
  `patient_id` int(11) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `fk_order_patient1_idx` (`patient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;

INSERT INTO `orders` (`order_id`, `date`, `serial_number`, `patient_id`)
VALUES
	(52,'2015/05/03 21:09:55',-1144439306,3),
	(53,'2015/05/03 21:09:59',-1144439302,3),
	(54,'2015/05/03 21:10:00',-1143784064,3),
	(55,'2015/05/03 22:06:00',-257025158,2);

/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table patient
# ------------------------------------------------------------

DROP TABLE IF EXISTS `patient`;

CREATE TABLE `patient` (
  `patient_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `poistovna` text NOT NULL,
  `last_name` text NOT NULL,
  PRIMARY KEY (`patient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;

INSERT INTO `patient` (`patient_id`, `name`, `poistovna`, `last_name`)
VALUES
	(1,'Lekar','NA','Lekarne'),
	(2,'Marcel','ssl','Forgac'),
	(3,'Marc','ssa','forg'),
	(4,'Jozef','kls','Stary'),
	(5,'Juraj','ssa','Sedy'),
	(6,'Jan','aas','Klaw');

/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table recept
# ------------------------------------------------------------

DROP TABLE IF EXISTS `recept`;

CREATE TABLE `recept` (
  `recept_id` int(11) NOT NULL AUTO_INCREMENT,
  `date` text,
  `patient_id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  PRIMARY KEY (`recept_id`),
  KEY `fk_recept_patient1_idx` (`patient_id`),
  KEY `fk_recept_doctor1_idx` (`doctor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table session
# ------------------------------------------------------------

DROP TABLE IF EXISTS `session`;

CREATE TABLE `session` (
  `patient_id` int(11) DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `sess_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`sess_id`),
  KEY `doctor_id` (`doctor_id`),
  KEY `pk_sess` (`patient_id`),
  CONSTRAINT `pk_sess` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`),
  CONSTRAINT `session_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`),
  CONSTRAINT `session_ibfk_2` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
