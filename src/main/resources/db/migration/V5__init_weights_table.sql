drop table if exists weights;
create table weights(
    id int primary key auto_increment,
    weight float not null,
    date datetime not null,
    user_id int not null,
    foreign key (user_id) references users (id)
)