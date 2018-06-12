-- create database file_storage;

create schema if not exists public;

create table if not exists public.file
(
  id            bigserial primary key,
  name          varchar(255) not null,
  data          bytea        not null,
  uploaded_time timestamp,
  block_number  bigint
);