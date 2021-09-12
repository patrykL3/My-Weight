drop table if exists users;
create table users(
    id int primary key auto_increment,
    username varchar(100) not null,
    password varchar(100) not null,
    role_id int not null,
    foreign key (role_id) references roles (id),
    active bit
)