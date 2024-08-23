SET REFERENTIAL_INTEGRITY FALSE ;

TRUNCATE TABLE user_role RESTART IDENTITY;
TRUNCATE TABLE mail RESTART IDENTITY;
TRUNCATE TABLE phone RESTART IDENTITY;
TRUNCATE TABLE user_detail RESTART IDENTITY;
TRUNCATE TABLE account RESTART IDENTITY;
TRUNCATE TABLE users RESTART IDENTITY;

SET REFERENTIAL_INTEGRITY TRUE;

INSERT INTO users (login, password)
VALUES ('User', '{noop}password'),
       ('Admin', '{noop}admin'),
       ('User2', '{noop}user2');

INSERT INTO account (amount, user_id)
VALUES (1000, 1),
       (500, 2),
       (750, 3);

INSERT INTO user_detail (firstname, lastname, middlename, birth_date, user_id)
VALUES ('Захарова', 'Евгения', 'Владимировна', '1992-02-19', 1),
       ('Иванов', 'Иван', 'Иванович', '1971-11-02', 2);

INSERT INTO user_role (role, user_id)
VALUES ('USER', 1),
       ('USER', 2),
       ('ADMIN', 2),
       ('USER', 3);

INSERT INTO phone (user_id, number)
VALUES (1, '79500024743'),
       (2, '79113456789'),
       (3, '79117400558');

INSERT INTO mail (user_id, email)
VALUES (1, 'user@yandex.ru'),
       (2, 'admin@gmail.com'),
       (3, 'user2@gmail.com');