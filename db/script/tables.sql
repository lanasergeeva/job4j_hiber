create table engine(
id serial primary key,
name text
);

create table driver(
id serial primary key,
name text
);

create table car(
id serial primary key,
name text,
engine_id int not null unique references engine(id)
);

create table history_owner(
driver_id int not null references driver(id),
car_id int not null references car(id)
);

