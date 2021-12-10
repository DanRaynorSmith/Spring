# drop table if exists `game` CASCADE;
#
# create table `game` (
#     `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
#     `title` varchar(255) not null,
#     `release_date` varchar(255),
#     `genre` varchar(255) not null,
#     `price` DOUBLE not null
# );