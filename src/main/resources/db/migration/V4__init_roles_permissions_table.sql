drop table if exists roles_permissions;
create table roles_permissions(
    role_id int not null,
    permission_id int not null,
    foreign key (role_id) references roles (id),
    foreign key (permission_id) references permissions (id),
    primary key(role_id, permission_id)
)