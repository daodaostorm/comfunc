Create Database If Not Exists comfunmanagerdb Character Set UTF8;
USE comfunmanagerdb;

CREATE TABLE if not exists `questioninfo` (
  `id` bigint(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `questionid` varchar(64) NOT NULL DEFAULT "",
  `questionname` varchar(64) NOT NULL DEFAULT "",
  `userid` varchar(64) NOT NULL DEFAULT "",
  `answerpos` varchar(64) NOT NULL DEFAULT "",
  `answerneg` varchar(64) NOT NULL DEFAULT "",
  `status` varchar(32) NOT NULL DEFAULT "",
  `updatetime` varchar(32) NOT NULL DEFAULT ""
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE if not exists `answerinfo` (
  `id` bigint(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `questionid` varchar(64) NOT NULL DEFAULT "",
  `questionname` varchar(64) NOT NULL DEFAULT "",
  `answerid` varchar(64) NOT NULL DEFAULT "",
  `username` varchar(64) NOT NULL DEFAULT "",
  `questiontext` varchar(64) NOT NULL DEFAULT "",
  `answerresult` varchar(64) NOT NULL DEFAULT "",
  `status` varchar(32) NOT NULL DEFAULT "",
  `updatetime` varchar(32) NOT NULL DEFAULT ""
) ENGINE=InnoDB DEFAULT CHARSET=utf8;