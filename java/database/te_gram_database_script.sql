




DROP TABLE IF EXISTS photos CASCADE;
CREATE TABLE photos (
photo_id serial ,
photo_url varchar,
comment_id int,
user_id int,
caption varchar,

constraint pk_photos primary key(photo_id),
constraint fk_photos foreign key(user_id) references users(user_id)

);


DROP TABLE IF EXISTS favorites CASCADE;
CREATE TABLE favorites (
favorited_id serial,
user_id int,
photo_id int,
is_favorited boolean,

constraint pk_favorites_id primary key(favorited_id),
constraint fk_favorites foreign key(photo_id) references photos(photo_id),
constraint fk_favorites_user_id foreign key(user_id) references users(user_id)
);



DROP TABLE IF EXISTS liked_photos CASCADE;
CREATE TABLE liked_photos (
liked_id serial,
user_id int,
photo_id int,
is_liked boolean,

constraint pk_liked_photos primary key(liked_id),
constraint fk_liked_photos_user_id foreign key(user_id) references users(user_id),
constraint fk_liked_photos foreign key(photo_id) references photos(photo_id)

);



DROP TABLE IF EXISTS comments CASCADE;
CREATE TABLE comments (
comment_id serial,
comment varchar,
user_id int,
photo_id int,

constraint pk_comments primary key(comment_id),
constraint fk_comments_user_id foreign key(user_id) references users(user_id),
constraint fk_comments_photo_id foreign key(photo_id) references photos(photo_id)
);


DROP TABLE IF EXISTS profile CASCADE;
CREATE TABLE profile (
user_id int,
bio varchar,
profile_pic_url varchar,

constraint fk_profile_user_id foreign key(user_id) references users(user_id)
);





