alter table user add(
    phonetic    varchar(100),
    postal_code varchar(8),
    prefecture_id   integer not null default 0,
    city    varchar(50),
    address varchar(50),
    telephone varchar(20),
    email varchar(256),
    note varchar(500),
    admin boolean not null default false
);