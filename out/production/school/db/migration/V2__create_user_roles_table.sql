CREATE TABLE IF NOT EXISTS `user_roles` (
  `id`       INT           NOT NULL  AUTO_INCREMENT,
  `version`  INT           NOT NULL  DEFAULT 0,
  `username` VARCHAR(25) NOT NULL  ,
  `user_role` VARCHAR(40) NOT NULL ,
  `created`  TIMESTAMP     NOT NULL  DEFAULT NOW(),
  `modified` TIMESTAMP     NOT NULL  DEFAULT NOW(),
  PRIMARY KEY (`id`));


