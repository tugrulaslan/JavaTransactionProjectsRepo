DROP SCHEMA IF EXISTS `springtx`;
CREATE SCHEMA `springtx` ;
CREATE TABLE `springtx`.`employee` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `department` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));