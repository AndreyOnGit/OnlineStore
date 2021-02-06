create table orders (
    id                      bigserial primary key,
    user_id                 bigint not null references users (id),
    total_price             int
);

create table order_items (
    id                      bigserial primary key,
    quantity                int,
    price_per_product       int,
    price                   int,
    product_id              bigint,
    order_id                bigint not null references orders (id)
);