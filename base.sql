CREATE DATABASE IF NOT EXISTS videogame_db;
USE videogame_db;

CREATE TABLE IF NOT EXISTS app_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS video_game (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    release_date DATE,
    image VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS game_category (
    game_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    PRIMARY KEY (game_id, category_id),
    CONSTRAINT fk_game
        FOREIGN KEY (game_id) REFERENCES video_game(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_category
        FOREIGN KEY (category_id) REFERENCES category(id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS review (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    author_name VARCHAR(255),
    rating INT NOT NULL,
    comment TEXT,
    date DATE,
    video_game_id BIGINT,
    CONSTRAINT fk_video_game
        FOREIGN KEY (video_game_id) REFERENCES video_game(id)
        ON DELETE CASCADE
);