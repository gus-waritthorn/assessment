-- Drop tables if they exist
DROP TABLE IF EXISTS lottery CASCADE;
DROP TABLE IF EXISTS user_ticket CASCADE;
DROP TABLE IF EXISTS "user" CASCADE;

CREATE TABLE lottery (
    id SERIAL PRIMARY KEY,
    ticket VARCHAR(6) NOT NULL,
    price INTEGER NOT NULL,
    amount INTEGER NOT NULL,
    is_available BOOLEAN DEFAULT TRUE
);

CREATE TABLE "user" (
    id VARCHAR(10) UNIQUE NOT NULL,
    username VARCHAR(40) UNIQUE NOT NULL,
    role VARCHAR(10) NOT NULL
);

CREATE TABLE user_ticket (
    id SERIAL PRIMARY KEY,
    lottery_id INTEGER REFERENCES lottery(id) ON DELETE CASCADE,
    user_id VARCHAR(10) REFERENCES "user"(id) ON DELETE CASCADE
);

-- Initial data
INSERT INTO "user" (id, username, role) VALUES('1111111111', 'admin', 'ADMIN');
INSERT INTO "user" (id, username, role) VALUES('2222222222', 'customer', 'CUSTOMER');