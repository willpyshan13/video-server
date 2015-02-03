# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 192.168.2.222 (MySQL 5.5.32-0ubuntu0.12.04.1-log)
# Database: dyvideo
# Generation Time: 2013-10-08 06:11:34 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table businessnewsentity
# ------------------------------------------------------------

DROP TABLE IF EXISTS `businessnewsentity`;

CREATE TABLE `businessnewsentity` (
  `newsId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `newsContent` text NOT NULL,
  `newsPreviewPicUrl` varchar(255) DEFAULT NULL,
  `newsTitle` varchar(255) NOT NULL,
  `newsWheelPicUrl` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `videoId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`newsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `businessnewsentity` WRITE;
/*!40000 ALTER TABLE `businessnewsentity` DISABLE KEYS */;

INSERT INTO `businessnewsentity` (`newsId`, `createTime`, `newsContent`, `newsPreviewPicUrl`, `newsTitle`, `newsWheelPicUrl`, `status`, `videoId`)
VALUES
	(1,'2013-10-08 14:06:27','hello world',NULL,'test hello',NULL,0,1),
	(2,'2013-10-08 14:06:27','你好 世界',NULL,'测试',NULL,0,2);

/*!40000 ALTER TABLE `businessnewsentity` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table collectionentity
# ------------------------------------------------------------

DROP TABLE IF EXISTS `collectionentity`;

CREATE TABLE `collectionentity` (
  `collectionId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `personId` bigint(20) DEFAULT NULL,
  `videoId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`collectionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table commententity
# ------------------------------------------------------------

DROP TABLE IF EXISTS `commententity`;

CREATE TABLE `commententity` (
  `commentId` bigint(20) NOT NULL AUTO_INCREMENT,
  `commentContent` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `personId` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `videoId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table feedbackentity
# ------------------------------------------------------------

DROP TABLE IF EXISTS `feedbackentity`;

CREATE TABLE `feedbackentity` (
  `feedbackId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `feedbackContent` text,
  `personId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`feedbackId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table personentity
# ------------------------------------------------------------

DROP TABLE IF EXISTS `personentity`;

CREATE TABLE `personentity` (
  `personId` bigint(20) NOT NULL AUTO_INCREMENT,
  `birthday` varchar(8) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  `mobileNumber` varchar(11) DEFAULT NULL,
  `mobileSerial` varchar(50) DEFAULT NULL,
  `nickName` varchar(20) DEFAULT NULL,
  `passWord` varchar(50) DEFAULT NULL,
  `personDesc` varchar(200) DEFAULT NULL,
  `photoUrl` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `userName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`personId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table praiseentity
# ------------------------------------------------------------

DROP TABLE IF EXISTS `praiseentity`;

CREATE TABLE `praiseentity` (
  `praiseId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `personId` bigint(20) DEFAULT NULL,
  `videoId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`praiseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table searchentity
# ------------------------------------------------------------

DROP TABLE IF EXISTS `searchentity`;

CREATE TABLE `searchentity` (
  `searchId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `personId` bigint(20) DEFAULT NULL,
  `searchKey` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`searchId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table tokenentity
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tokenentity`;

CREATE TABLE `tokenentity` (
  `tokenId` bigint(20) NOT NULL AUTO_INCREMENT,
  `personId` bigint(20) DEFAULT NULL,
  `token` varchar(50) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`tokenId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table toplineentity
# ------------------------------------------------------------

DROP TABLE IF EXISTS `toplineentity`;

CREATE TABLE `toplineentity` (
  `topId` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataId` bigint(20) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `topType` int(11) DEFAULT NULL,
  PRIMARY KEY (`topId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table videoentity
# ------------------------------------------------------------

DROP TABLE IF EXISTS `videoentity`;

CREATE TABLE `videoentity` (
  `videoId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `videoActor` varchar(255) DEFAULT NULL,
  `videoBrief` varchar(60) DEFAULT NULL,
  `videoDesc` text,
  `videoDirector` varchar(40) DEFAULT NULL,
  `videoPreviewPicUrl` varchar(255) DEFAULT NULL,
  `videoRegion` bigint(20) DEFAULT NULL,
  `videoScriptwriter` varchar(40) DEFAULT NULL,
  `videoTitle` varchar(50) DEFAULT NULL,
  `videoWheelPicUrl` varchar(255) DEFAULT NULL,
  `videoYear` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`videoId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `videoentity` WRITE;
/*!40000 ALTER TABLE `videoentity` DISABLE KEYS */;

INSERT INTO `videoentity` (`videoId`, `createTime`, `status`, `videoActor`, `videoBrief`, `videoDesc`, `videoDirector`, `videoPreviewPicUrl`, `videoRegion`, `videoScriptwriter`, `videoTitle`, `videoWheelPicUrl`, `videoYear`)
VALUES
	(1,'2013-10-08 14:06:27',0,'hello actor','test brief','test desc','test director',NULL,NULL,'test scriptwriter','test title',NULL,NULL),
	(2,'2013-10-08 14:06:27',0,'hello actor xxx','test brief xxx','test desc xxx','test director xxx2','',24,'test scriptwriter xxx','test title xxx','',18);

/*!40000 ALTER TABLE `videoentity` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table videohistoryentity
# ------------------------------------------------------------

DROP TABLE IF EXISTS `videohistoryentity`;

CREATE TABLE `videohistoryentity` (
  `historyId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `personId` bigint(20) DEFAULT NULL,
  `videoId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`historyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table videotypeentity
# ------------------------------------------------------------

DROP TABLE IF EXISTS `videotypeentity`;

CREATE TABLE `videotypeentity` (
  `typeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `typeDesc` varchar(50) DEFAULT NULL,
  `typeName` varchar(40) DEFAULT NULL,
  `typeParent` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`typeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `videotypeentity` WRITE;
/*!40000 ALTER TABLE `videotypeentity` DISABLE KEYS */;

INSERT INTO `videotypeentity` (`typeId`, `typeDesc`, `typeName`, `typeParent`)
VALUES
	(1,'标签','label',NULL),
	(2,'喜剧','label_comedy',1),
	(3,'爱情','label_love',1),
	(4,'动作','label_action',1),
	(5,'科幻','label_science',1),
	(6,'武侠','label_kungfu',1),
	(7,'战争','label_war',1),
	(8,'剧情','label_story',1),
	(9,'热度','hot',NULL),
	(10,'今日最多','hot_today',9),
	(11,'本周最多','hot_weekly',9),
	(12,'本月最多','hot_month',9),
	(13,'历史最多','hot_history',9),
	(14,'最新上映','hot_latest',9),
	(15,'用户好评','hot_praise',9),
	(16,'年份','year',NULL),
	(17,'1990','1990',16),
	(18,'2012','2012',16),
	(19,'2013','2013',16),
	(20,'2014','2014',16),
	(21,'地区','area',NULL),
	(22,'亚洲','area_asia',21),
	(23,'美洲','area_america',21),
	(24,'港台','area_HKtaiwan',21),
	(25,'欧洲','area_euorp',21);

/*!40000 ALTER TABLE `videotypeentity` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table videotyperecordentity
# ------------------------------------------------------------

DROP TABLE IF EXISTS `videotyperecordentity`;

CREATE TABLE `videotyperecordentity` (
  `typeRecordId` bigint(20) NOT NULL AUTO_INCREMENT,
  `typeId` bigint(20) DEFAULT NULL,
  `videoId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`typeRecordId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `videotyperecordentity` WRITE;
/*!40000 ALTER TABLE `videotyperecordentity` DISABLE KEYS */;

INSERT INTO `videotyperecordentity` (`typeRecordId`, `typeId`, `videoId`)
VALUES
	(1,4,2),
	(2,5,2);

/*!40000 ALTER TABLE `videotyperecordentity` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table videourlentity
# ------------------------------------------------------------

DROP TABLE IF EXISTS `videourlentity`;

CREATE TABLE `videourlentity` (
  `videoUrlId` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` int(11) DEFAULT NULL,
  `videoFormat` varchar(10) DEFAULT NULL,
  `videoId` bigint(20) DEFAULT NULL,
  `videoPlayUrl` varchar(255) DEFAULT NULL,
  `videoUrlDesc` varchar(50) DEFAULT NULL,
  `videoUrlIndex` int(11) DEFAULT NULL,
  `videoWebUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`videoUrlId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
