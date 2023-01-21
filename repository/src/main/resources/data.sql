-- Adding default user roles
INSERT IGNORE INTO t_role (role_id, role_name) VALUES (1, 'ADMIN');
INSERT IGNORE INTO t_role (role_id, role_name) VALUES (2, 'MANAGER');
INSERT IGNORE INTO t_role (role_id, role_name) VALUES (3, 'USER');

-- Adding "root" user with "root" password
INSERT IGNORE INTO t_user (user_id, user_email, user_locked, user_password, user_username)
VALUES ('00000000-0000-0000-0000-000000000001', 'root@root.com', false, '$2a$10$cBtsPltbJbv8rf67d2IUTO2NuE7MrMQIgDadE5HWGNXgSecVp7I1W', 'root');
INSERT IGNORE INTO t_user_detail (user_detail_id, user_birth_date, user_credit_card, user_first_name, user_last_name, user_phoneNumber)
VALUES ('00000000-0000-0000-0000-000000000001', '0008-08-08', '0000000000000001', 'Root', 'The Root', '+880000001');
INSERT IGNORE INTO t_user_role (user_id, role_id) VALUES ('00000000-0000-0000-0000-000000000001', '1');
INSERT IGNORE INTO t_user_role (user_id, role_id) VALUES ('00000000-0000-0000-0000-000000000001', '2');
INSERT IGNORE INTO t_user_role (user_id, role_id) VALUES ('00000000-0000-0000-0000-000000000001', '3');

-- Adding "manager" user with "manager" password
INSERT IGNORE INTO t_user (user_id, user_email, user_locked, user_password, user_username)
VALUES ('00000000-0000-0000-0000-000000000002', 'manager@manager.com', false, '$2a$10$Focn3iN1TJjCq1bdlI/TrO7WADqf6zYqH5CC9WyPALBq/gkmGtVGq', 'manager');
INSERT IGNORE INTO t_user_detail (user_detail_id, user_birth_date, user_credit_card, user_first_name, user_last_name, user_phoneNumber)
VALUES ('00000000-0000-0000-0000-000000000002', '0008-08-08', '0000000000000002', 'Manager', 'The Manager', '+880000002');
INSERT IGNORE INTO t_user_role (user_id, role_id) VALUES ('00000000-0000-0000-0000-000000000002', '2');
INSERT IGNORE INTO t_user_role (user_id, role_id) VALUES ('00000000-0000-0000-0000-000000000002', '3');
