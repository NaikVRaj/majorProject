INSERT IGNORE INTO role (`role_id`,`description`, `name`) VALUES (1,'Admin role', 'ADMIN');
INSERT IGNORE INTO role (`role_id`,`description`, `name`) VALUES (2,'Manager role', 'MANAGER');
INSERT IGNORE INTO role (`role_id`,`description`, `name`) VALUES (3,'Employee role', 'EMPLOYEE');
INSERT IGNORE INTO task_status_lu(`task_status_lu_id`,`description`,`status_name`)
values (1,'pending','pending');
INSERT IGNORE INTO task_status_lu(`task_status_lu_id`,`description`,`status_name`)
values (2,'working','working');
INSERT IGNORE INTO task_status_lu(`task_status_lu_id`,`description`,`status_name`)
values (3,'completed','completed');