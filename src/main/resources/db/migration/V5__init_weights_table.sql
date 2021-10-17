drop table if exists weights;
create table weights(
    id int primary key auto_increment,
    value float not null,
    date timestamp not null,
    user_id int not null,
    foreign key (user_id) references users (id)
)