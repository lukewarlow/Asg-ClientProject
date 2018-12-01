DROP TABLE flight_assessment_attempt;
DROP TABLE ground_school_attempt;
DROP TABLE operation_manual_attempt;
DROP TABLE recommendations;
DROP TABLE course;
DROP TABLE candidate;

CREATE TABLE candidate
(
  id BIGINT AUTO_INCREMENT,
  candidate_number VARCHAR(255) NULL,
  user_id BIGINT NOT NULL,
  address_id BIGINT NOT NULL,
  company_id BIGINT NULL,
  dob DATE NOT NULL,
  drone_id BIGINT NOT NULL,
  prefered_location VARCHAR(255) NULL,
  has_payed BOOLEAN NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE(candidate_number),
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (address_id) REFERENCES address(id),
  FOREIGN KEY (company_id) REFERENCES company(id),
  FOREIGN KEY (drone_id) REFERENCES drone(id)
);

DELIMITER $
CREATE TRIGGER generate_candidate_number BEFORE INSERT ON candidate
  FOR EACH ROW
    BEGIN
      DECLARE NewID INT;
      SELECT MAX(id) + 1 INTO NewID FROM candidate;
      SET NEW.candidate_number = CONCAT('ASG-', NewID, '-', YEAR(NOW()), '-', MONTH(NOW()));
    END;$

DELIMITER $
CREATE TRIGGER candidate_number_lock BEFORE UPDATE ON candidate
FOR EACH ROW
  BEGIN
    IF NEW.candidate_number != OLD.candidate_number THEN SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Cannot update candidate number';
    END IF;
  END;$

DELIMITER $
CREATE TRIGGER candidate_timestamp_lock BEFORE UPDATE ON candidate
FOR EACH ROW
  BEGIN
    IF NEW.created_at != OLD.created_at THEN SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Cannot update created_at column of candidate';
    END IF;
  END;$

DELIMITER $
CREATE TRIGGER user_timestamp_lock BEFORE UPDATE ON user
FOR EACH ROW
  BEGIN
    IF NEW.created_at != OLD.created_at THEN SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Cannot update created_at column of user';
    END IF;
  END;$