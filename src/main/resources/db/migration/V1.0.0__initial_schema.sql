create table users
 (
    id                  varchar(36)              primary key,
    creation_time       datetime(6)              null,
    created_by          varchar(36)              null,
    last_modified       datetime(6)              null,
    last_modified_by    varchar(36)              null,
    is_active           tinyint(1)  default 1    null,
    nickname            varchar(50)              null,
    password            varchar(500)             null,
    username            varchar(50)              null
);