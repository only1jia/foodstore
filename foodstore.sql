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


insert  into `myfoods`(`Id`,`Title`,`Price`, `Salesamount`,`Storenumber`,`Remark`) values (1,'�¿�ʿ��ζ��Ƥը��',11,17,83,'�¿�ʿȫ����ζ��Ƥը��ȫ�����У���ζ���²�Ʒ�����������У���ζ��Ƥը��ζ��ʮ�㣬�۸�11Ԫ��'),(2,'�¿�ʿ��ζ��Ƥ��ǹ��',22,12,88,'�¿�ʿȫ����ζը��ϵ��,��ζ��Ƥ��ǹ��,22Ԫ/�ݡ���Ƥ�ִ࣬�������ۣ���ζ�²�Ʒ���¿�ʿ�Ͳ��㣡'),(3,'�¿�ʿ���Ŵ��',18,12,88,'���Ŵ����ö����䷽���ƣ�������ζŨ������Ƥ�ִ࣬�����䳬�����������ÿһ�ڶ���� '),(4,'�¿�ʿ���ռ����׺���',51,0,100,'���ռ����׺���Ӫ�������� 1910 kj ������ 13.5 g ֬�� 12.0 g  ̼ˮ������ 72.6 g'),(5,'�¿�ʿXO��Ϻ�׺���',16,0,100,'XO��Ϻ�׺���Ӫ�������� 1910 kj ������ 13.5 g ֬�� 12.0 g ̼ˮ������ 72.6 g'),(6,'�¿�ʿ��଴�Ƥ���ȷ�',23,0,100,'�¿�ʿ��଴�Ƥ���ȷ���������㣬Ũ�����ˣ��ۼ�23Ԫ����Ŵ������׷�����Ƥ�����⣬�ٽ���ŨŨ�Ŀ��֭���������磬���������ζ����ȫ��λ���飡'),(7,'�¿�ʿ����������',4,2,12,'���������̣��ִ����ζ��ȴ������������'),(8,'�¿�ʿ���ܹ�ѩ������',14,2,13,'�¿�ʿ���ܹ�ѩ������14Ԫ/�ݡ����۹�����ܹ�ϵ����Ʒ������ͬ��ڶ��ݰ���Ż�'),(9,'�¿�ʿ����Ͱ��',75,0,16,'�¿�ʿ����Ͱ�Ͱ�������Ƥը����3�飩+���ᣨ4�飩+�¿�ʿ���飨4�飩+�ͷ��㼦��+˫����+���¿����У�3����'),(10,'�¿�ʿ���¿���',8,0,17,'�¿�ʿ���¿���С��6.5Ԫ���б�8Ԫ����9Ԫ'),(11,'�¿�ʿ�ƽ�QϺ���ײ�',28,0,18,'�ײͺ����ƽ�QϺ��+������+�п���');




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


