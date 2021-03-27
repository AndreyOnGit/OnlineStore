create table products (id bigserial primary key, title varchar(255), price int);
insert into products (title, price) values
('pen', 2),
('pencil', 1),
('note', 2),
('book', 10),
('eraser', 1);

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
('u0', '$2y$12$7bVw.7nJeZhfgPz8Ylv87eE2/pHfL0qRcDgoFvSCrDWKeDBJ.67cq', 'u0@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1);

create table orders (
    id                      bigserial primary key,
    owner_id                bigint references users (id),
    price                   int,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp,
    address                 varchar(250)
);