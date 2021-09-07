drop table if exists users;
create table users(
    id int primary key auto_increment,
    username varchar(100) not null,
    password varchar(100) not null,
    id int role_id not null,
    active bit
)