DELIMITER $
CREATE PROCEDURE delete_all_user_data(p_user_id LONG)
BEGIN
  DECLARE user_exists BOOLEAN;
  DECLARE c_id BIGINT;
  DECLARE a_id BIGINT;
  DECLARE company_id BIGINT;

  SELECT CASE WHEN (COUNT(*) = 1) THEN TRUE ELSE FALSE END INTO user_exists FROM user WHERE user.id = p_user_id;
  IF NOT user_exists THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Given user id doesnt match any user';
  END IF;

  SELECT id INTO c_id FROM candidate WHERE user_id = p_user_id;
  IF c_id != 0 THEN
    DELETE FROM flight_assessment_attempt WHERE candidate_id = c_id;
    DELETE FROM operation_manual_attempt WHERE candidate_id = c_id;
    DELETE FROM ground_school_attempt WHERE candidate_id = c_id;

    SELECT address_id INTO a_id FROM candidate WHERE id = c_id;
    SELECT company_id INTO company_id FROM candidate WHERE id = c_id;
    DELETE FROM candidate WHERE id = c_id;
    DELETE FROM company WHERE id = company_id;
  END IF;

  DELETE FROM flight_assessment_attempt WHERE instructor_id = p_user_id;
  DELETE FROM operation_manual_attempt WHERE instructor_id = p_user_id;
  UPDATE ground_school_course SET instructor_id = NULL WHERE instructor_id = p_user_id;
  DELETE FROM user WHERE id = p_user_id;
END $