DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    secret VARCHAR(100),
    email VARCHAR(200),
    role VARCHAR(50)
);

INSERT INTO users(name, secret, email, role) VALUES ('admin', 'admin123', 'admin@example.com', 'ADMIN');
INSERT INTO users(name, secret, email, role) VALUES ('alice', 'alicepw', 'alice@example.com', 'USER');
INSERT INTO users(name, secret, email, role) VALUES ('bob', 'bobpw', 'bob@example.com', 'USER');

DROP TABLE IF EXISTS comments;

CREATE TABLE comments (
    id SERIAL PRIMARY KEY,
    text VARCHAR(2000)
);

INSERT INTO comments(text) VALUES ('hello world');
