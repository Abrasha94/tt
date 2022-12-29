create table user_info
(
    id            bigserial PRIMARY KEY,
    email_address varchar(50)  not null,
    password      varchar(500) not null
);

create unique index idx_user_email on user_info (email_address);

create table roles
(
    id        bigserial PRIMARY KEY,
    role_name varchar(50) not null
);

create unique index ids_roles_rolename on roles (role_name);

create table user_roles
(
    id      bigserial PRIMARY KEY,
    user_id bigint not null,
    role_id bigint not null,
    constraint fk_userroles_user_id foreign key (user_id) references user_info (id),
    constraint fk_userroles_role_id foreign key (role_id) references roles (id)
);

create unique index idx_user_role_id on user_roles (user_id, role_id);

INSERT INTO user_info (email_address, password)
VALUES ('test@admin.edn.by', '$2a$12$WPKB7zZMzS4WnSsDTjS.ZOqWem3OeKauwpbSN9zj4TQ2/sNPGm6eC');

INSERT INTO roles (role_name)
VALUES ('ROLE_ADMIN');

INSERT INTO user_roles (user_id, role_id)
select u.id, r.id
from user_info u,
     roles r
where u.email_address = 'test@admin.edn.by'
  and r.role_name = 'ROLE_ADMIN';

commit;