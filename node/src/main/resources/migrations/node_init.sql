-- create database node;

create schema if not exists public;

CREATE TABLE IF NOT EXISTS node.public.transaction
(
  id          BIGSERIAL PRIMARY KEY NOT NULL,
  file_hash   VARCHAR               NOT NULL,
  block_hash  VARCHAR               NOT NULL,
  user_id     VARCHAR               NOT NULL,
  create_time bigint                not null,
  file_name   TEXT                  NOT NULL,
  file_data   BYTEA                 NOT NULL
);

CREATE INDEX IF NOT EXISTS transaction__file_name__ix
  ON node.public.transaction (file_name);
COMMENT ON COLUMN node.public.transaction.file_name IS 'Название файла';
COMMENT ON COLUMN node.public.transaction.file_data IS 'Данные файла в байтовом представлении';
COMMENT ON COLUMN node.public.transaction.file_hash IS 'Хэш файла';
comment on column node.public.transaction.create_time is 'Время загрузки файла в мсек после January 1, 1970, 00:00:00 GMT';
COMMENT ON TABLE node.public.transaction IS 'Транзакции загруженных файлов';


CREATE TABLE if not exists node.public.block
(
  hash          VARCHAR PRIMARY KEY,
  previous_hash VARCHAR NOT NULL
);
COMMENT ON COLUMN node.public.block.hash IS 'хеш блока';
COMMENT ON COLUMN node.public.block.previous_hash IS 'хеш предыдущего блока';