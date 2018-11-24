ALTER TABLE user
ADD UNIQUE (email),
ADD UNIQUE (phone_number);

ALTER TABLE drone
ADD UNIQUE (manufacturer, model);

ALTER TABLE candidate
ADD UNIQUE (user_id);

ALTER TABLE subject
ADD UNIQUE (code);

ALTER TABLE subject_instructor
ADD UNIQUE (instructor_id, subject_id);