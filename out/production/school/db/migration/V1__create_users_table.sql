CREATE TABLE IF NOT EXISTS `users` (
  `id`       INT           NOT NULL  AUTO_INCREMENT,
  `version`  INT           NOT NULL  DEFAULT 0,
  `username` VARCHAR(25) NOT NULL  ,
  `password` VARCHAR(40) NOT NULL ,
  `email`    VARCHAR(255)  NOT NULL,
  `enabled` VARCHAR(6) NOT NULL ,
  `dob`   DATE           NOT NULL,
  `created`  TIMESTAMP     NOT NULL  DEFAULT NOW(),
  `modified` TIMESTAMP     NOT NULL  DEFAULT NOW(),
  PRIMARY KEY (`id`));
