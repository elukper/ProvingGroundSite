CREATE SCHEMA `altima` ;
CREATE TABLE `altima`.`test` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `PARENT_ID` INT NULL,
  PRIMARY KEY (`ID`));
  
ALTER TABLE `altima`.`test` 
ADD CONSTRAINT `parentfk`
  FOREIGN KEY (`PARENT_ID`)
  REFERENCES `altima`.`test` (`ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;