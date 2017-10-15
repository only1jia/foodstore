CREATE DATABASE `foodstore`;
USE `foodstore`;

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `accountid` int(11) NOT NULL auto_increment,
  `balance` float default NULL,
  PRIMARY KEY  (`accountid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gb2312;


insert  into `account`(`accountid`,`balance`) values (1,9740);


DROP TABLE IF EXISTS myfoods;

CREATE TABLE `myfoods` (
 `Id` int(11) NOT NULL auto_increment,
  `Title` varchar(30) NOT NULL,
  `Price` float NOT NULL,
  `Salesamount` int(11) NOT NULL,
  `Storenumber` int(11) NOT NULL,
  `Remark` text NOT NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=gb2312;


insert  into `myfoods`(`Id`,`Title`,`Price`, `Salesamount`,`Storenumber`,`Remark`) values (1,'德克士辣味脆皮炸鸡',11,17,83,'德克士全新辣味脆皮炸鸡全新上市，辣味！新馋品！激发你的灵感！辣味脆皮炸鸡味力十足，价格11元起'),(2,'德克士辣味脆皮手枪腿',22,12,88,'德克士全新辣味炸鸡系列,辣味脆皮手枪腿,22元/份。外皮酥脆，里面香嫩，辣味新馋品，德克士就馋你！'),(3,'德克士鸡排大亨',18,12,88,'鸡排大亨采用独家配方腌制，不仅香味浓郁、外皮酥脆，加上其超大分量，让你每一口都过瘾 '),(4,'德克士照烧鸡肉米汉堡',51,0,100,'照烧鸡肉米汉堡营养表：能量 1910 kj 蛋白质 13.5 g 脂肪 12.0 g  碳水化合物 72.6 g'),(5,'德克士XO鲜虾米汉堡',16,0,100,'XO鲜虾米汉堡营养表：能量 1910 kj 蛋白质 13.5 g 脂肪 12.0 g 碳水化合物 72.6 g'),(6,'德克士咖喱脆皮鸡腿饭',23,0,100,'德克士咖喱脆皮鸡腿饭，金黄酥香，浓郁诱人，售价23元。香糯松软的米饭，脆皮鸡腿肉，再浇上浓浓的咖喱汁，香气四溢，给予嗅觉和味觉的全方位体验！'),(7,'德克士溏心奇洛滋',4,2,12,'溏心奇洛滋，酥脆的美味，却能柔软你的心田！'),(8,'德克士哈密瓜雪恋华夫',14,2,13,'德克士哈密瓜雪恋华夫，14元/份。正价购买哈密瓜系列新品，享受同款第二份半价优惠'),(9,'德克士欢乐桶餐',75,0,16,'德克士欢乐桶餐包含：脆皮炸鸡（3块）+辣翅（4块）+德克士鸡块（4块）+和风香鸡堡+双鸡堡+百事可乐中（3杯）'),(10,'德克士百事可乐',8,0,17,'德克士百事可乐小杯6.5元，中杯8元，大杯9元'),(11,'德克士黄金Q虾堡套餐',28,0,18,'套餐含：黄金Q虾堡+中薯条+中可乐');




DROP TABLE IF EXISTS `userinfo`;

CREATE TABLE `userinfo` (
  `userid` int(11) NOT NULL auto_increment,
  `username` varchar(50) default NULL,
  `password` varchar(50) default NULL,
  `telephone` varchar(50) default NULL,
  `accountid` int(11) default NULL,
  PRIMARY KEY  (`userid`),
  KEY `account_id_fk` (`accountid`),
  CONSTRAINT `account_id_fk` FOREIGN KEY (`accountid`) REFERENCES `account` (`accountid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gb2312;


insert  into `userinfo`(`userid`,`username`,`password`,`telephone`,`accountid`) values (1,'admin','123456','10086','1');


DROP TABLE IF EXISTS `trade`;

CREATE TABLE `trade` (
  `tradeid` int(11) NOT NULL auto_increment,
  `userid` int(11) NOT NULL,
  `tradetime` datetime NOT NULL,
  PRIMARY KEY  (`tradeid`),
  KEY `user_id_fk` (`userid`),
  CONSTRAINT `user_id_fk` FOREIGN KEY (`userid`) REFERENCES `userinfo` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=gb2312;


insert  into `trade`(`tradeid`,`userid`,`tradetime`) values (12,1,'2012-11-01 00:00:00'),(13,1,'2012-11-01 00:00:00'),(14,1,'2012-11-01 00:00:00'),(15,1,'2012-12-20 00:00:00'),(16,1,'2012-12-20 00:00:00');

DROP TABLE IF EXISTS `tradeitem`;

CREATE TABLE `tradeitem` (
  `itemid` int(11) NOT NULL auto_increment,
  `foodid` int(11) default NULL,
  `quantity` int(11) default NULL,
  `tradeid` int(11) default NULL,
  PRIMARY KEY  (`itemid`),
  KEY `food_id_fk` (`foodid`),
  KEY `trade_id_fk` (`tradeid`),
  CONSTRAINT `food_id_fk` FOREIGN KEY (`foodid`) REFERENCES `myfoods` (`Id`),
  CONSTRAINT `trade_id_fk` FOREIGN KEY (`tradeid`) REFERENCES `trade` (`tradeid`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=gb2312;



insert  into `tradeitem`(`itemid`,`foodid`,`quantity`,`tradeid`) values (22,1,10,12),(23,2,10,12),(24,3,10,12),(25,4,1,13),(26,5,3,13),(27,6,4,13),(28,7,1,14);


