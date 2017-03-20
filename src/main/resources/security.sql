CREATE TABLE users (
  username varchar(50) NOT NULL,
  password varchar(50) NOT NULL,
  enabled tinyint(1) NOT NULL,
  PRIMARY KEY (username)
) COMMENT='spring security users table';

CREATE TABLE spring_security.authorities
(
  username  VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  CONSTRAINT fk_authorities_iisers
  FOREIGN KEY (username) REFERENCES users (username)
) COMMENT='spring security authorities table';
CREATE UNIQUE INDEX ix_auth_username ON spring_security.authorities (username, authority);


insert into users(username, password, enabled)
    values("john", "wick", true);

insert into authorities(username, authority)
    value ("john", "ROLE_USER");