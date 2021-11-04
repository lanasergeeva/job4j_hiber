CREATE TABLE IF NOT EXISTS car_mark (
id SERIAL PRIMARY KEY,
name VARCHAR(2000)
    );

CREATE TABLE IF NOT EXISTS car_model (
id SERIAL PRIMARY KEY,
name VARCHAR(2000),
mark_id INT REFERENCES car_mark(id)
    );