INSERT INTO doctor (ID, FIRSTNAME, LASTNAME) VALUES
            (1, 'morrison', 'spellcaster'),
            (2, 'wizzard', 'spellcaster'),
            (3, 'magic', 'rogue'),
            (4, 'beautiful', 'glutton'),
            (5, 'whispering', 'wolverine'),
            (6, 'the king gizzard', 'and the lizzard Wizard');

INSERT INTO patient (ID, FIRSTNAME, LASTNAME, BIRTHDATE, EMAIL, USERNAME, PASSWORD) VALUES
             (1, 'John', 'Snow', '1999-10-18', 'imaliar@gmail.com', 'john', '123'),
             (2, 'Daenerys', 'Targaryen', '1998-11-11', 'dragonqueen@gmail.com', 'user2', 'itisme123'),
             (3, 'Antwuan', 'Dixon', '1990-02-18', 'nollieheelflip@gmail.com', 'patient3', 'mysecretpass'),
             (4, 'Robb', 'Stark', '1994-01-25', 'theking@gmail.com', 'robb', 'secret123'),
             (5, 'Arya', 'Stark', '2005-03-03', 'nobody@gmail.com', 'astark', 'qwertyuiop'),
             (6, 'Jamie', 'Lannister','1970-04-12', 'kingslayer@gmail.com', 'jamie', 'totallysecretpass11');

INSERT INTO appointment (ID, DOCTOR_ID, PATIENT_ID, DATE, TOPIC) VALUES
             (1, 1, 1, '2022-11-15', 'Headaches'),
             (2, 2, 2, '2022-11-11', 'Headaches'),
             (3, 3, 3, '2022-11-12', 'Headaches'),
             (4, 4, 4, '2022-11-13', 'Headaches'),
             (5, 5, 5, '2022-11-14', 'Headaches'),
             (6, 6, 6, '2022-11-16', 'Headaches');

INSERT INTO diagnosis (ID, DESCRIPTION, DISEASE, APPOINTMENT_ID) VALUES
    ('aa', 'diagnosis description', 'headache', 1),
    ('bb', 'diagnosis description', 'insomnia', 2),
    ('cc', 'diagnosis description', 'cancer', 3),
    ('dd', 'diagnosis description', 'brain issues', 4),
    ('ee', 'diagnosis description', 'lorem ipsum', 5),
    ('ff', 'diagnosis description', 'dolomes ador', 6);

