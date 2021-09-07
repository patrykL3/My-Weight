drop table if exists weights;
create table weights(
    id int primary key auto_increment,
    weight float not null,
    date date not null,
    foreign key (user_id) references users (id)
)