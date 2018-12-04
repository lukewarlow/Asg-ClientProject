INSERT INTO user (forename, surname, email, phone_number, role, password, disabled, activated)
VALUES
       ('John', 'Smith', 'johns@example.com', '03069990541', 0,'$2a$10$zl6xZnSXDVBU.h87wnVJ1.1qFbz6CU/Rz8fe/LOYVjBQ1W9zMOESm', false, true),
       ('Sally', 'Smith', 'sallys@example.com', '03069990980', 2,'$2a$10$zl6xZnSXDVBU.h87wnVJ1.1qFbz6CU/Rz8fe/LOYVjBQ1W9zMOESm', true, true),
       ('Admin', 'User', 'admin@example.com', '03069990348', 3,'$2a$10$zl6xZnSXDVBU.h87wnVJ1.1qFbz6CU/Rz8fe/LOYVjBQ1W9zMOESm', false, true);

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

INSERT INTO course_location(location)
VALUES
       ('Cardiff'),
       ('Somerset'),
       ('Aberdeen');