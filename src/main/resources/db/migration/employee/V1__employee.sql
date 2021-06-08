CREATE TABLE `employee`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
name varchar(255),
primary key(id) USING BTREE)
COLLATE='utf8_hungarian_ci' ENGINE=InnoDB;
