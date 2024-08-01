SET REFERENTIAL_INTEGRITY FALSE ;

TRUNCATE TABLE user_role RESTART IDENTITY;
TRUNCATE TABLE mail RESTART IDENTITY;
TRUNCATE TABLE phone RESTART IDENTITY;
TRUNCATE TABLE users RESTART IDENTITY;
TRUNCATE TABLE account RESTART IDENTITY;

SET REFERENTIAL_INTEGRITY TRUE;

INSERT INTO account (account)
VALUES (1000),
       (500),
       (750);

INSERT INTO users (login, password, account_id)
VALUES ('User', '{noop}password', 1),
       ('Admin', '{noop}admin', 2),
       ('User2', '{noop}user2', 3);

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