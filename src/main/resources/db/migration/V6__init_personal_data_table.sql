drop table if exists personal_data;
create table personal_data(
    id int primary key auto_increment,
    age int not null,
    height int not null,
    sex varchar(100) not null,
    desired_weight float not null,
    user_id int not null,
    foreign key (user_id) references users (id)
)