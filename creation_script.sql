DROP USER IF EXISTS 'asg'@'127.0.0.1';
DROP USER IF EXISTS 'asgflyway'@'127.0.0.1';

CREATE SCHEMA asgproject;
USE asgproject;

CREATE USER 'asg'@'127.0.0.1' IDENTIFIED BY 'correcthorsebatterystaple';
CREATE USER 'asgflyway'@'127.0.0.1' IDENTIFIED BY 'correcthorsebattery';

GRANT INSERT, SELECT, UPDATE, DELETE, EXECUTE ON asgproject.* TO 'asg'@'127.0.0.1';
GRANT ALL PRIVILEGES ON asgproject.* TO 'asgflyway'@'127.0.0.1';
SET GLOBAL log_bin_trust_function_creators = 1;

FLUSH PRIVILEGES;
