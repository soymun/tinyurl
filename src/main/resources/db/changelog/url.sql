--liquibase formatted sql
--changeset soymun:1

create table public.tiny_url
(
    id       bigserial
        primary key,
    hash_url varchar(255)
        constraint tiny_url_pk
            unique,
    url      varchar(255)
);

alter table public.tiny_url
    owner to postgres;

create index tiny_url_hash_url_index
    on public.tiny_url (hash_url);

create index tiny_url_url_index
    on public.tiny_url (url);

