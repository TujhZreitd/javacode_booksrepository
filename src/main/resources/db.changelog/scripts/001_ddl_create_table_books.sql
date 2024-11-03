create table books(
    id serial primary key,
    title varchar not null,
    author varchar not null,
    publication_year date not null
)