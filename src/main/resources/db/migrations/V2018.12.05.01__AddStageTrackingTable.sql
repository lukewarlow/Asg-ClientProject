DROP TABLE subject_instructor;
DROP TABLE subject;

CREATE TABLE candidate_process_stage
(
  id BIGINT AUTO_INCREMENT,
  stage VARCHAR(255),
  PRIMARY KEY (id),
  UNIQUE KEY(stage)
);

ALTER TABLE candidate
ADD COLUMN stage_id BIGINT,
ADD FOREIGN KEY (stage_id) REFERENCES candidate_process_stage(id);

DROP TRIGGER generate_candidate_number;

DELIMITER $
CREATE TRIGGER generate_candidate_number BEFORE INSERT ON candidate
  FOR EACH ROW
BEGIN
  DECLARE NewID INT;
  SELECT IFNULL(MAX(id) + 1, 1) INTO NewID FROM candidate;
  SET NEW.candidate_number = CONCAT('ASG-', NewID, '-', YEAR(NOW()), '-', MONTH(NOW()));
END;$
DELIMITER ;

DROP TRIGGER generate_course_number;

DELIMITER $
CREATE TRIGGER generate_course_number BEFORE INSERT ON ground_school_course
  FOR EACH ROW
BEGIN
  DECLARE NewID BIGINT DEFAULT NULL;
  DECLARE Type VARCHAR(255);
  DECLARE Location VARCHAR(255);
  SELECT IFNULL(MAX(id) + 1, 1) INTO NewID FROM ground_school_course;
  SELECT ct.type INTO Type FROM course_type ct WHERE ct.id = New.type_id;
  SELECT cl.location INTO Location FROM course_location cl WHERE cl.id = New.location_id;
  SET NEW.course_number = CONCAT(YEAR(NOW()), '-', MONTH(NOW()), '-', Location, '-', Type, '-', NewId);
END;$
DELIMITER ;