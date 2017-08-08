
TRUNCATE table users;

TRUNCATE table user_roles;

INSERT INTO `users` (`id`, `username`, `password`,`email`, `enabled`,`dob`) VALUES
  (1, 'dbuser1', '12345','dbuser@gmail.com', 1,'1994-01-25 00:00:00'),
  (2, 'dbadmin1', '12345','dbadmin@gmail.com', 1,'1995-01-25 00:00:00');


INSERT INTO `user_roles` (`id`, `username`, `user_role`) VALUES
  (1, 'dbuser1', 'type 1'),
  (2, 'dbadmin1', 'type 1'),
  (3, 'dbadmin1', 'type 2');