INSERT INTO user (id, forename, surname, email, phone_number, role, password, disabled, activated)
VALUES
       (1, 'John', 'Smith', 'johns@example.com', '03069990541', 0,'$2a$10$zl6xZnSXDVBU.h87wnVJ1.1qFbz6CU/Rz8fe/LOYVjBQ1W9zMOESm', false, true),
       (2, 'Dave', 'Smith', 'daves@example.com', '07700900731', 1,'$2a$10$zl6xZnSXDVBU.h87wnVJ1.1qFbz6CU/Rz8fe/LOYVjBQ1W9zMOESm', false, true),
       (3, 'Sally', 'Smith', 'sallys@example.com', '03069990980', 2,'$2a$10$zl6xZnSXDVBU.h87wnVJ1.1qFbz6CU/Rz8fe/LOYVjBQ1W9zMOESm', true, true),
       (4, 'Admin', 'User', 'admin@example.com', '03069990348', 3,'$2a$10$zl6xZnSXDVBU.h87wnVJ1.1qFbz6CU/Rz8fe/LOYVjBQ1W9zMOESm', false, true);

INSERT INTO address(id, line_one, line_two, city, county, postcode)
VALUES
       (1, '123 Drone Road', null, 'Cardiff', null, 'CF241AB');

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

INSERT INTO candidate(user_id, address_id, company_id, dob, drone_id, prefered_location, flying_experience)
VALUES
       (2, 1, null, '2000-12-01', 1, 1, '2 Hours');