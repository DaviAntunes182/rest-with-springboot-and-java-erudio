CREATE TABLE IF NOT EXISTS `tb_books` (
  `id` bigint NOT NULL AUTO_INCREMENT ,
  `author` varchar(150) NOT NULL,
  `launch_date` datetime NOT NULL,
  `price` decimal NOT NULL,
  `title` varchar(200) NOT NULL,
  PRIMARY KEY(`id`)
);