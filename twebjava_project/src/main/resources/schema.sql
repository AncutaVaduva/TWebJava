create database if not exists rallyCompetition;

CREATE TABLE IF NOT EXISTS rallyCompetition.`rallies` (
  `rally_id` INT NOT NULL AUTO_INCREMENT,
  `rally_name` VARCHAR(45) NULL,
  PRIMARY KEY (`rally_id`),
  UNIQUE INDEX `rally_name_UNIQUE` (`rally_name` ASC) VISIBLE);

CREATE TABLE IF NOT EXISTS  rallyCompetition.`editions` (
  `edition_id` INT NOT NULL AUTO_INCREMENT,
  `rally_id` INT NOT NULL,
  `edition_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`edition_id`),
  INDEX `fk_rally_id_editions_idx` (`rally_id` ASC) VISIBLE,
  CONSTRAINT `fk_rally_id_editions`
    FOREIGN KEY (`rally_id`)
    REFERENCES `rallycompetition`.`rallies` (`rally_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS  rallyCompetition.`stages` (
  `stage_id` INT NOT NULL AUTO_INCREMENT,
  `edition_id` INT NOT NULL,
  `stage_name` VARCHAR(45) NOT NULL,
  `distance` DOUBLE(5,2) NULL,
  `type` VARCHAR(20) NULL,
  `start_date` DATETIME NULL,
  PRIMARY KEY (`stage_id`),
  INDEX `fk_edition_id_stage_idx` (`edition_id` ASC) VISIBLE,
  CONSTRAINT `unique_index-stage_already_exists_on_edition` UNIQUE  (`edition_id`, `stage_name`),
  CONSTRAINT `fk_edition_id_stage`
    FOREIGN KEY (`edition_id`)
    REFERENCES `rallycompetition`.`editions` (`edition_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS  rallyCompetition.`teams` (
  `team_id` INT NOT NULL AUTO_INCREMENT,
  `team_name` VARCHAR(45) NOT NULL,
  `country_of_origin` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`team_id`),
  UNIQUE INDEX `team_name_UNIQUE` (`team_name` ASC) VISIBLE);

CREATE TABLE IF NOT EXISTS  rallyCompetition.`team_members` (
  `member_id` INT NOT NULL AUTO_INCREMENT,
  `last_name` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `date_of_birth` DATE NOT NULL,
  `type` VARCHAR(45) NOT NULL CHECK(type IN ('driver','coDriver','staff')),
  `team_id` INT NOT NULL,
  PRIMARY KEY (`member_id`),
  INDEX `fk_team_id_members_idx` (`team_id` ASC) VISIBLE,
  CONSTRAINT `unique_index-member_already_exists` UNIQUE  (`last_name`, `first_name`, `date_of_birth`),
  CONSTRAINT `fk_team_id_members`
    FOREIGN KEY (`team_id`)
    REFERENCES `rallycompetition`.`teams` (`team_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS rallyCompetition.`participations` (
  `participation_id` INT NOT NULL AUTO_INCREMENT,
  `edition_id` INT NOT NULL,
  `team_id` INT NOT NULL,
  `registration_date` DATE NULL,
  PRIMARY KEY (`participation_id`),
  INDEX `fk_team_id_entries_idx` (`team_id` ASC) VISIBLE,
  CONSTRAINT `fk_edition_id_entries`
    FOREIGN KEY (`edition_id`)
    REFERENCES `rallycompetition`.`editions` (`edition_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_team_id_entries`
    FOREIGN KEY (`team_id`)
    REFERENCES `rallycompetition`.`teams` (`team_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS rallyCompetition.`results` (
  `result_id` INT NOT NULL AUTO_INCREMENT,
  `stage_id` INT NOT NULL,
  `team_id` INT NOT NULL,
  `time` DOUBLE(5,2) NULL,
  `score` INT NULL,
  PRIMARY KEY (`result_id`),
  INDEX `fk_team_id_results_idx` (`team_id` ASC) VISIBLE,
  CONSTRAINT `fk_stage_id_results`
    FOREIGN KEY (`stage_id`)
    REFERENCES `rallycompetition`.`stages` (`stage_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_team_id_results`
    FOREIGN KEY (`team_id`)
    REFERENCES `rallycompetition`.`teams` (`team_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);
