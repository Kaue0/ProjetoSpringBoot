create table usuarios(

    user_id serial not null,
    created_at date,
    updated_at date,
    name varchar(50) not null,
    username varchar(30) not null,
    phone varchar(20),
    email varchar(70) not null unique,
    password varchar(40) not null,
    profile_link varchar(1000),
    deleted boolean not null default false,

    PRIMARY KEY(user_id)

);