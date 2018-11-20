CREATE TABLE address
(
  id BIGINT AUTO_INCREMENT,
  line_one VARCHAR(255) NOT NULL,
  line_two VARCHAR(255),
  city VARCHAR(255) NOT NULL,
  county VARCHAR(255),
  postcode VARCHAR(7) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE company
(
  id BIGINT AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  phone_number VARCHAR(12) NOT NULL,
  email VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE user
(
  id BIGINT AUTO_INCREMENT,
  forename VARCHAR(255) NOT NULL,
  surname VARCHAR(255) NOT NULl,
  email VARCHAR(255) NOT NULL,
  phone_number VARCHAR(12),
  role TINYINT NOT NULL DEFAULT 1,
  password VARCHAR(255),
  activation_token VARCHAR(255),
  disabled BOOLEAN NOT NULL DEFAULT FALSE,
  created_at DATETIME DEFAULT NOW(),
  updated_at DATETIME DEFAULT NOW(),
  PRIMARY KEY (id)
);

CREATE TABLE drone
(
  id BIGINT AUTO_INCREMENT,
  manufacturer VARCHAR(255) NOT NULL,
  model VARCHAR(255) NOT NULL,
  suas_category VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE candidate
(
  candidate_number VARCHAR(255) NOT NULL,
  user_id BIGINT NOT NULL,
  address_id BIGINT NOT NULL,
  company_id BIGINT,
  dob DATE NOT NULL,
  drone_id BIGINT NOT NULL,
  reason VARCHAR(255),
  prefered_location VARCHAR(255),
  has_payed BOOLEAN,
  PRIMARY KEY (candidate_number),
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (address_id) REFERENCES address(id),
  FOREIGN KEY (company_id) REFERENCES company(id),
  FOREIGN KEY (drone_id) REFERENCES drone(id)
);

CREATE TABLE subject
(
  id BIGINT AUTO_INCREMENT,
  code CHAR(2),
  PRIMARY KEY (id)
);


CREATE TABLE subject_instructor
(
  instructor_id BIGINT NOT NULL,
  subject_id BIGINT NOT NULL,
  FOREIGN KEY (instructor_id) REFERENCES user(id),
  FOREIGN KEY (subject_id) REFERENCES subject(id)
);

CREATE TABLE course
(
  number VARCHAR(255),
  location VARCHAR(255),
  start_date DATE,
  end_date DATE,
  candidate_number VARCHAR(255) NOT NULL,
  PRIMARY KEY (number),
  FOREIGN KEY (candidate_number) REFERENCES candidate(candidate_number)
);

CREATE TABLE ground_school_attempt
(
  id BIGINT AUTO_INCREMENT,
  course_number VARCHAR(255) NOT NULL,
  instructor_id BIGINT NOT NULL,
  completed_date DATE,
  question_band TINYINT NOT NULL,
  pass_date DATE,
  result_mark BIGINT,
  PRIMARY KEY (id),
  FOREIGN KEY (course_number) REFERENCES course(number),
  FOREIGN KEY (instructor_id) REFERENCES user(id)
);

CREATE TABLE operation_manual_attempt
(
  id BIGINT AUTO_INCREMENT,
  course_number VARCHAR(255) NOT NULL,
  instructor_id BIGINT,
  submitted_date DATE NOT NULL,
  passed_date DATE,
  PRIMARY KEY (id),
  FOREIGN KEY (course_number) REFERENCES course(number),
  FOREIGN KEY (instructor_id) REFERENCES user(id)
);

CREATE TABLE flight_assessment_attempt
(
  id BIGINT AUTO_INCREMENT,
  course_number VARCHAR(255) NOT NULL,
  has_insurance BOOLEAN NOT NULL,
  logged_hours DATETIME NOT NULL,
  pass_date DATE,
  PRIMARY KEY (id),
  FOREIGN KEY (course_number) REFERENCES course(number)
);

CREATE TABLE recommendations
(
  id BIGINT AUTO_INCREMENT,
  candidate_number VARCHAR(255) NOT NULL,
  pfco_recommendation date NOT NULL,
  flight_competence date NOT NULL,
  application_data_sent date NOT NULL,
  application_sent date NOT NULL,
  pfco_approved date NOT NULL,
  comment VARCHAR(255) NOT NULL,
  approved date NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (candidate_number) REFERENCES candidate(candidate_number)
);