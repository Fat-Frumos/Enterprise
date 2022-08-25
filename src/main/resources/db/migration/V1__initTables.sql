CREATE TABLE car (
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    brand TEXT NOT NULL,
    model TEXT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    year INT NOT NULL,
    path TEXT NOT NULL,
    unique (id)
);
