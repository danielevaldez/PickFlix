BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, profile, genre, profile_genre;

CREATE TABLE users (
	user_id SERIAL NOT NULL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE profile (
	profile_id SERIAL PRIMARY KEY,
	user_id int NOT NULL,
	profile_name varchar(50) NOT NULL,
	CONSTRAINT FK_profile_users FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE genre (
	genre_id SERIAL PRIMARY KEY,
	genre_name varchar(50) NOT NULL
);

CREATE TABLE profile_genre (
	profile_id int NOT NULL,
	genre_id int NOT NULL,
	PRIMARY KEY (profile_id, genre_id),
	CONSTRAINT FK_pg_profile FOREIGN KEY (profile_id) REFERENCES profile (profile_id),
	CONSTRAINT FK_pg_genre FOREIGN KEY (genre_id) REFERENCES genre (genre_id)
);

COMMIT TRANSACTION;
