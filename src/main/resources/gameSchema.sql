CREATE SCHEMA IF NOT EXISTS `game`;

CREATE TABLE IF NOT EXISTS `game` (
    `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `title` varchar(255) not null,
    `release_date` varchar(255),
    `genre` varchar(255) not null,
    `price` DEC(5,2) not null
);