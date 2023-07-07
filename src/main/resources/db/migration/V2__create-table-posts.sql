create table posts(

    post_id serial not null,
    created_at date,
    updated_at date,
    title varchar(50) not null,
    description varchar(200) not null,
    photo_link varchar(500),
    video_link varchar(500),
    privated boolean,
    deleted boolean not null default false,

    PRIMARY KEY(post_id)

);