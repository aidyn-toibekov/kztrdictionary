CREATE TABLE `kzword` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `word` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE_kzword` (`id` ASC));

  CREATE TABLE `trword` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `word` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE_trword` (`id` ASC));

  CREATE TABLE `dictionary` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `kzwordid` INT NOT NULL,
  `trwordid` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `kzword_indx` (`kzwordid` ASC),
  INDEX `trword_indx` (`trwordid` ASC),
  INDEX `dictionary_indx` (`kzwordid` ASC, `trwordid` ASC),
  CONSTRAINT `fk_kzword`
    FOREIGN KEY (`kzwordid`)
    REFERENCES `kzword` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_trword`
    FOREIGN KEY (`trwordid`)
    REFERENCES `trword` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
