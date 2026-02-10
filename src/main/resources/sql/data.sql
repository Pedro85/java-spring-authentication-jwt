-- Sample data for users table
INSERT INTO users (id, name, username, password, role) VALUES
(nextval('users_seq'), 'Pedro Alvarez', 'pedro85', '$2a$10$rZ8YvM3qN.7L5h1vBJxRV.X5nP4qGxO0Xkq3rTHlO8mN4pBqY7tXO', 'ROLE_ADMIN'),
(nextval('users_seq'), 'Alice Johnson', 'alice', '$2a$10$Zk5QJqW9YvB3nN1xL5h1vOXkq3rTHlO8mN4pBqY7tXOrZ8YvM3qN7', 'ROLE_USER'),
(nextval('users_seq'), 'Bob Smith', 'bobsmith', '$2a$10$Y5h1vBJxRV3qN7L5h1vBqZk5QJqW9YvB3nN1xL5h1vOXkq3rTHlO8m', 'ROLE_USER'),
(nextval('users_seq'), 'Carol Lee', 'carollee', '$2a$10$L5h1vBJxRV3qN7L5h1vBqY5h1vBJxRV3qN7L5h1vBqZk5QJqW9Yv', 'ROLE_USER'),
(nextval('users_seq'), 'Dave Martin', 'davem', '$2a$10$N7L5h1vBqZk5QJqW9YvB3nN1xL5h1vOXkq3rTHlO8mY5h1vBJxRV', 'ROLE_ADMIN');