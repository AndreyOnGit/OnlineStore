create table users (
    id                      bigserial primary key,
    username                varchar(30) not null unique,
    password                varchar(80) not null,
    email                   varchar(50) unique,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

create table roles (
    id                      bigserial primary key,
    name                    varchar(50) not null unique,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

create TABLE users_roles (
    user_id                 bigint not null references users (id),
    role_id                 bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values
('ROLE_USER'),
('ROLE_ADMIN');

insert into users (username, password, email)
values
('u1', '$2y$12$VlL/ymZakbjvWnRA4Gj8LOT24Jvvmay78B8ToBi3kwnW1xNgaIr/K', 'u1@gmail.com'),
('u2', '$2y$12$bctklz80wjpkYOflbWEApeWFVEs.NAZfz.glTXYsdm1eWDn30PtIS', 'u2@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2);