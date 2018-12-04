CREATE TABLE course_location
(
  id BIGINT AUTO_INCREMENT,
  location VARCHAR(255) NOT NULL,
  PRIMARY KEY(id),
  UNIQUE (location)
);

ALTER TABLE candidate
  ADD COLUMN flying_experience VARCHAR(255),
  ADD COLUMN has_insurance BOOLEAN,
  MODIFY COLUMN prefered_location BIGINT,
  ADD FOREIGN KEY (prefered_location) REFERENCES course_location(id);

DROP TRIGGER generate_candidate_number;

DELIMITER $
CREATE TRIGGER generate_candidate_number BEFORE INSERT ON candidate
  FOR EACH ROW
BEGIN
  DECLARE NewID INT;
  SELECT MAX(id) + 1 INTO NewID FROM candidate;
  IF(@NewID IS NULL) THEN
    SET NewID := 1;
  END IF;
  SET NEW.candidate_number = CONCAT('ASG-', NewID, '-', YEAR(NOW()), '-', MONTH(NOW()));
END;$
DELIMITER ;

CREATE TABLE course_type
(
  id BIGINT AUTO_INCREMENT,
  type VARCHAR(255) NOT NULL,
  PRIMARY KEY(id),
  UNIQUE (type)
);

CREATE TABLE ground_school_course
(
  id BIGINT AUTO_INCREMENT,
  course_number VARCHAR(255),
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  type_id BIGINT NOT NULL,
  location_id BIGINT NOT NULL,
  instructor_id BIGINT,
  PRIMARY KEY(id),
  FOREIGN KEY (type_id) REFERENCES course_type(id),
  FOREIGN KEY (location_id) REFERENCES course_location(id),
  FOREIGN KEY (instructor_id) REFERENCES user(id),
  UNIQUE KEY (start_date, end_date, location_id)
);

DELIMITER $
CREATE TRIGGER generate_course_number BEFORE INSERT ON ground_school_course
  FOR EACH ROW
BEGIN
  DECLARE NewID INT;
  DECLARE Type VARCHAR(255);
  DECLARE Location VARCHAR(255);
  SELECT MAX(id) + 1 INTO NewID FROM ground_school_course;
  IF(@NewID IS NULL) THEN
    SET NewID := 1;
  END IF;
  SELECT ct.type INTO Type FROM course_type ct WHERE ct.id = New.type_id;
  SELECT cl.location INTO Location FROM course_location cl WHERE cl.id = New.location_id;
  SET NEW.course_number = CONCAT(YEAR(NOW()), '-', MONTH(NOW()), '-', Location, '-', Type, '-', NewId);
END;$

DELIMITER $
CREATE TRIGGER course_number_lock BEFORE UPDATE ON ground_school_course
  FOR EACH ROW
BEGIN
  IF NEW.course_number != OLD.course_number THEN SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Cannot update course number';
  END IF;
END;$

DELIMITER ;

CREATE TABLE ground_school_attempt
(
  id BIGINT AUTO_INCREMENT,
  ground_school_id BIGINT NOT NULL,
  candidate_id BIGINT NOT NULL,
  question_bank TINYINT,
  marked_date DATE,
  result INT,
  PRIMARY KEY (id),
  FOREIGN KEY (ground_school_id) REFERENCES ground_school_course(id),
  FOREIGN KEY (candidate_id) REFERENCES candidate(id)
);

CREATE TABLE operation_manual_attempt
(
  id BIGINT AUTO_INCREMENT,
  candidate_id BIGINT NOT NULL,
  submitted DATETIME NOT NULL DEFAULT NOW(),
  instructor_id BIGINT,
  passed DATETIME,
  PRIMARY KEY (id),
  FOREIGN KEY (candidate_id) REFERENCES candidate(id),
  FOREIGN KEY (instructor_id) REFERENCES user(id)
);

CREATE TABLE flight_assessment_attempt
(
  id BIGINT AUTO_INCREMENT,
  candidate_id BIGINT NOT NULL,
  logged_hours TIME NOT NULL,
  instructor_id BIGINT,
  pass_date DATE,
  PRIMARY KEY (id),
  FOREIGN KEY (candidate_id) REFERENCES candidate(id),
  FOREIGN KEY (instructor_id) REFERENCES user(id)
);