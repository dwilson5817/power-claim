create table powerclaim_claims
(
    id int auto_increment,
    owner varchar(36) not null,
    x int not null,
    z int not null,
    active tinyint(1) default 1 not null,
    claimed_at datetime default current_timestamp not null ,
    updated_at datetime default current_timestamp on update current_timestamp not null,
    constraint powerclaim_claims_primary_key
        primary key (id)
);
