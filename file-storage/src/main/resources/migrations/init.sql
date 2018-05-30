create table public.file
(
  id   bigserial primary key,
  name varchar(255) not null,
  hash varchar(255) not null,
  data bytea        not null
);