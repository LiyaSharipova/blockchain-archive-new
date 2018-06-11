-- create database node;

create schema if not exists public;


create table if not exists node.public.block
(
  hash          varchar primary key,
  nonce         bigint,
  previous_hash varchar not null
);
comment on column node.public.block.hash is 'хеш блока';
comment on column node.public.block.previous_hash is 'хеш предыдущего блока';

create table if not exists node.public.transaction
(
  id            bigserial primary key not null,
  file_hash     varchar               not null,
  file_id       bigint,
  block_hash    varchar               not null references block (hash),
  uploaded_time bigint                not null
);

comment on column node.public.transaction.file_hash is 'Хэш файла';
comment on column node.public.transaction.uploaded_time is 'Время загрузки файла в мсек после January 1, 1970, 00:00:00 GMT';
comment on table node.public.transaction is 'Транзакции загруженных файлов';

