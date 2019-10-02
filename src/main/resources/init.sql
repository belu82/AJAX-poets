/*
    Database initialization script that runs on every web-application redeployment.
*/

DROP TABLE IF EXISTS poets;
DROP TABLE IF EXISTS poems;

CREATE TABLE poets (
    id SERIAL PRIMARY KEY,
    email TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
	CONSTRAINT email_not_empty CHECK (email <> ''),
	CONSTRAINT password_not_empty CHECK (password <> '')
);

CREATE TABLE poems (
  id SERIAL PRIMARY KEY,
  title TEXT UNIQUE NOT NULL,
  content TEXT NOT NULL);

INSERT INTO poets (email, password) VALUES
	('user1@user1', 'user1'), -- 1
	('user2@user2', 'user2'), -- 2
	('user2@user3', 'user3'); -- 3



