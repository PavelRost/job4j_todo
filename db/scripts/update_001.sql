CREATE TABLE if NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name TEXT,
    email TEXT,
    password TEXT
);
INSERT INTO users(name, email, password) VALUES ('Fedor', 'root@local', '1111');
create table if not exists items (
    id serial primary key,
    description text,
    created timestamp,
    done boolean,
    user_id INTEGER REFERENCES users (id)
);