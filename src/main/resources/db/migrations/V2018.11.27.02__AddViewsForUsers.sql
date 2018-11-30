ALTER TABLE user
    ADD COLUMN activated BOOLEAN NOT NULL DEFAULT FALSE;

CREATE VIEW enabled_user as
  SELECT u.id,
         u.forename,
         u.surname,
         u.email,
         u.phone_number,
         u.role,
         u.password,
         u.activation_token,
         u.disabled,
         u.created_at,
         u.updated_at,
         u.activated
  FROM user u
  WHERE u.disabled = FALSE;

CREATE VIEW activated_user as
  SELECT u.id,
         u.forename,
         u.surname,
         u.email,
         u.phone_number,
         u.role,
         u.password,
         u.activation_token,
         u.disabled,
         u.created_at,
         u.updated_at,
         u.activated
  FROM user u
  WHERE u.disabled = FALSE AND u.activated = TRUE;