Create Database If Not Exists comfunmanagerdb Character Set UTF8;
USE comfunmanagerdb;

CREATE TABLE if not exists `taskhistoryinfo` (
  `id` bigint(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `taskid` varchar(64) NOT NULL DEFAULT "",
  `serverhost` varchar(64) NOT NULL DEFAULT "",
  `serverport` varchar(64) NOT NULL DEFAULT "",
  `taskstatus` varchar(32) NOT NULL DEFAULT "",
  `updatetime` varchar(32) NOT NULL DEFAULT ""
) ENGINE=InnoDB DEFAULT CHARSET=utf8;