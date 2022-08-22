CREATE TABLE car (
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    date DATE NOT NULL,
    unique (id)
);

