DELIMITER $
CREATE TRIGGER ground_school_attempt_lock BEFORE UPDATE ON ground_school_attempt
  FOR EACH ROW
BEGIN
    IF OLD.marked_date IS NOT NULL THEN
      SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Cannot update already entered ground school results';
    END IF;
END;$

CREATE PROCEDURE mark_ground_school_attempt(p_candidate_id LONG, p_gs_course_id LONG, p_question_bank INT, p_result INT, p_passmark INT)
BEGIN
  DECLARE candidate_exists BOOLEAN;
  DECLARE candidate_stage INT;
  DECLARE course_exists BOOLEAN;
  DECLARE candidate_latest_attempt_id LONG;

  SELECT CASE WHEN (COUNT(*) = 1) THEN TRUE ELSE FALSE END INTO candidate_exists FROM candidate WHERE candidate.id = p_candidate_id;
  IF NOT candidate_exists THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Given candidate id doesnt match any candidate';
  END IF;

  SELECT stage_id INTO candidate_stage FROM candidate WHERE candidate.id = p_candidate_id;
  IF candidate_stage != 3 THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Given candidate is at wrong stage';
  END IF;

  SELECT CASE WHEN (COUNT(*) = 1) THEN TRUE ELSE FALSE END INTO course_exists FROM ground_school_course WHERE id = p_gs_course_id;
  IF NOT candidate_exists THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Given ground school id doesnt match any course';
  END IF;

  SELECT gsa.id INTO candidate_latest_attempt_id FROM ground_school_attempt gsa JOIN ground_school_course gsc ON gsc.id = gsa.ground_school_id WHERE candidate_id = p_candidate_id AND ground_school_id = p_gs_course_id ORDER BY gsc.course_number DESC LIMIT 1;
  IF candidate_latest_attempt_id > 0 THEN
    UPDATE ground_school_attempt SET question_bank = p_question_bank, result = p_result, marked_date = NOW() WHERE candidate_id = p_candidate_id AND ground_school_id = p_gs_course_id;
    IF p_result >= p_passmark THEN
      UPDATE candidate SET stage_id = 4 WHERE id = p_candidate_id;
    END IF;
  END IF;
END; $