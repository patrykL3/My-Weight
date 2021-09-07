drop table if exists roles_permissions;
create table roles_permissions(
    foreign key (role_id) references roles (id),
    foreign key (permission_id) references permissions (id)
)