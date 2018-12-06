INSERT INTO candidate_process_stage(id, stage)
VALUES
       (1, 'Needs To Make Payment'),
       (2, 'Awaiting Ground School Assignment'),
       (3, 'Awaiting Ground School Result'),
       (4, 'Needs To Submit Operations Manual'),
       (5, 'Awaiting Operations Manual Result'),
       (6, 'Awaiting Flight Assessment'),
       (7, 'Awaiting Flight Assessment Result'),
       (8, 'Awaiting PfCO Recommendation'),
       (9, 'Feedback Collection'),
       (10, 'Completed');

INSERT INTO user (id, forename, surname, email, phone_number, role, password, disabled, activated)
VALUES
       (1, 'John', 'Smith', 'johns@example.com', '03069990541', 0,'$2a$10$zl6xZnSXDVBU.h87wnVJ1.1qFbz6CU/Rz8fe/LOYVjBQ1W9zMOESm', false, true),
       (2, 'Dave', 'Smith', 'daves@example.com', '07700900731', 1,'$2a$10$zl6xZnSXDVBU.h87wnVJ1.1qFbz6CU/Rz8fe/LOYVjBQ1W9zMOESm', false, true),
       (3, 'William', 'Smith', 'wills@example.com', '07700900762', 1,'$2a$10$zl6xZnSXDVBU.h87wnVJ1.1qFbz6CU/Rz8fe/LOYVjBQ1W9zMOESm', false, true),
       (4, 'Mary', 'Horne', 'maryh@example.com', '03069990562', 1,'$2a$10$zl6xZnSXDVBU.h87wnVJ1.1qFbz6CU/Rz8fe/LOYVjBQ1W9zMOESm', false, true),
       (5, 'Abbey', 'Foster', 'abbeyf@example.com', '07700900964', 1,'$2a$10$zl6xZnSXDVBU.h87wnVJ1.1qFbz6CU/Rz8fe/LOYVjBQ1W9zMOESm', false, true),
       (6, 'Sally', 'Smith', 'sallys@example.com', '03069990980', 2,'$2a$10$zl6xZnSXDVBU.h87wnVJ1.1qFbz6CU/Rz8fe/LOYVjBQ1W9zMOESm', true, true),
       (7, 'Jodie', 'Bright', 'jodieb@example.com', '02920180220', 2,'$2a$10$zl6xZnSXDVBU.h87wnVJ1.1qFbz6CU/Rz8fe/LOYVjBQ1W9zMOESm', false, true),
       (8, 'Instructor', 'User', 'instructor@example.com', '03069990970', 2,'$2a$10$zl6xZnSXDVBU.h87wnVJ1.1qFbz6CU/Rz8fe/LOYVjBQ1W9zMOESm', false, true),
       (9, 'Admin', 'User', 'admin@example.com', '03069990348', 3,'$2a$10$zl6xZnSXDVBU.h87wnVJ1.1qFbz6CU/Rz8fe/LOYVjBQ1W9zMOESm', false, true);

INSERT INTO address(id, line_one, line_two, city, county, postcode)
VALUES
       (1, '123 Drone Road', null, 'Cardiff', null, 'CF241AB'),
       (2, '123 Fake Road', null, 'Taunton', null, 'TA11AE');

INSERT INTO drone(manufacturer, model)
VALUES
('DJI', 'Mavic 2'),
('DJI', 'Mavic Air'),
('DJI', 'Mavic Pro'),
('DJI', 'Mavic Pro Platinum'),
('DJI', 'Phantom 1'),
('DJI', 'Phantom FC40'),
('DJI', 'Phantom 2'),
('DJI', 'Phantom 2 Vision'),
('DJI', 'Phantom 2 Vision+'),
('DJI', 'Phantom 3 Standard'),
('DJI', 'Phantom 3 4K'),
('DJI', 'Phantom 3 SE'),
('DJI', 'Phantom 3 Advanced'),
('DJI', 'Phantom 3 Professional'),
('DJI', 'Phantom 4'),
('DJI', 'Phantom 4 Advanced'),
('DJI', 'Phantom 4 Pro'),
('DJI', 'Phantom 4 Pro V2.0'),
('DJI', 'Inspire 1'),
('DJI', 'Inspire 1 Pro/Raw'),
('DJI', 'Inspire 2'),
('Parrot', 'Anafi'),
('Parrot', 'Bebop 2'),
('Parrot', 'Bebop 2 Power'),
('Parrot', 'Mambo');

INSERT INTO course_type(type)
VALUES
       ('Full'),
       ('Partial');

INSERT INTO course_location(id, location)
VALUES
       (1, 'Cardiff'),
       (2, 'Somerset'),
       (3, 'Aberdeen');

INSERT INTO ground_school_course(id, start_date, end_date, type_id, location_id)
VALUES
       (1, '2018-12-11','2018-12-14', 1, 1);

INSERT INTO ground_school_course(id, start_date, end_date, type_id, location_id, instructor_id)
VALUES (2, '2018-12-06','2018-12-09', 1, 2, 7);

INSERT INTO candidate(user_id, address_id, company_id, dob, drone_id, prefered_location, has_payed, flying_experience, stage_id)
VALUES
       (2, 1, null, '2000-12-01', 1, 1, FALSE, '2 Hours', 1),
       (3, 1, null, '1990-01-09', 1, 1, TRUE, '6 Hours', 2),
       (4, 2, null, '1996-03-17', 2, 2, TRUE, '4 Hours', 3);

INSERT INTO ground_school_attempt(ground_school_id, candidate_id)
VALUES
       (2, 3);