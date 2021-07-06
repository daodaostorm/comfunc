Create Database If Not Exists comfunmanagerdb Character Set UTF8;
USE comfunmanagerdb;
CREATE TABLE if not exists `users` (
      `id` bigint(16) NOT NULL AUTO_INCREMENT,
      `username` varchar(45) NOT NULL UNIQUE KEY,
      `password` varchar(100) NOT NULL,
	  `image` varchar(200) DEFAULT '',
      `age` varchar(8) DEFAULT '',
      `enabled` varchar(45) NOT NULL DEFAULT '1',
      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
INSERT INTO users(username, password, age, enabled) VALUES ('admin', 'txt123.com', '1', 1);

CREATE TABLE if not exists `authorities` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `username` varchar(45) NOT NULL,
      `authority` varchar(45) NOT NULL,
      PRIMARY KEY (`id`),
      UNIQUE KEY `username_UNIQUE` (`username`,`authority`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

INSERT INTO authorities(username, authority) VALUES ('admin','ROLE_USER');
INSERT INTO authorities(username, authority) VALUES ('admin','ROLE_ADMIN');

CREATE TABLE if not exists `serverinfo` (
  `id` bigint(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `serverid` varchar(64) NOT NULL DEFAULT "",
  `serverhost` varchar(64) NOT NULL DEFAULT "",
  `serverport` varchar(64) NOT NULL DEFAULT "",
  `serverstatus` varchar(32) NOT NULL DEFAULT "",
  `createtime` varchar(32) NOT NULL DEFAULT "",
  `updatetime` varchar(32) NOT NULL DEFAULT ""
) ENGINE=InnoDB DEFAULT CHARSET=utf8;