drop table if exists permissions;
create table permissions(
    id int primary key auto_increment,
    name varchar(100) not null
)