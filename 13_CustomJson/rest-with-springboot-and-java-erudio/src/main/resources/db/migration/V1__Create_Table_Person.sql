CREATE TABLE IF NOT EXISTS `tb_person` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(40) NOT NULL,
  `adress` varchar(100) NOT NULL,
  `gender` varchar(6) NOT NULL,
  PRIMARY KEY (`id`)
);