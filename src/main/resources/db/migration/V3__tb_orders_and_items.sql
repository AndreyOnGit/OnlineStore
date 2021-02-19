create table orders (
    id                      bigserial primary key,
    owner_id                bigint references users (id),
    price                   int,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp,
    address                 varchar(250)
);

create table order_items (
    id                      bigserial primary key,
    order_id                bigint references orders (id),
    product_id              bigint references products (id),
    title                   varchar(255),
    quantity                int,
    price_per_product       int,
    price                   int,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);