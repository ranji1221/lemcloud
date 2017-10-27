--
--  lemon jersey auth moduel sql  -- MYSQL
--

-- user
DROP TABLE  IF EXISTS lemon_jersey_auth_user;
CREATE TABLE lemon_jersey_auth_user (
  id int auto_increment primary key,
  guid varchar(255) not null unique,
  createTime datetime,
  updateTime datetime,
  password varchar(255) not null,
  username varchar(255) not null unique,
  INDEX username_index (username)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- role
DROP TABLE  IF EXISTS lemon_jersey_auth_role;
CREATE TABLE lemon_jersey_auth_role (
  id int auto_increment primary key,
  guid varchar(255) not null unique,
  createTime datetime,
  updateTime datetime,
  roleName varchar(255) not null,
  description varchar(255)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- user_role
DROP TABLE  IF EXISTS lemon_jersey_auth_user_role;
CREATE TABLE lemon_jersey_auth_user_role (
  userId int(11) not null,
  roleId int(11) not null,
  INDEX users_id_index (userId),
  INDEX roles_id_index (roleId)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- role_permission
DROP TABLE  IF EXISTS lemon_jersey_auth_role_permission;
CREATE TABLE lemon_jersey_auth_role_permission (
  roleId int(11) not null,
  permission varchar(255) not null,
  INDEX role_id_index (roleId)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;


--
--  lemon jersey oauth2 moduel sql  -- MYSQL
--

DROP TABLE  IF EXISTS lemon_jersey_oauth2_client;
CREATE TABLE lemon_jersey_oauth2_client (
  id int auto_increment primary key,
  guid varchar(255),
  createTime datetime,
  updateTime datetime,
  clientId varchar(255) not null,
  clientSecret varchar(255) not null,
  clientName varchar(255) not null,
  clientUri varchar(255),
  clientIconUri varchar(255),
  scope varchar(255),
  grantType varchar(255),
  redirectUri varchar(255),
  description varchar(4096)
);

DROP TABLE  IF EXISTS lemon_jersey_oauth2_access_token;
CREATE TABLE lemon_jersey_oauth2_access_token (
  id int auto_increment primary key,
  guid varchar(255) not null unique,
  createTime datetime,
  updateTime datetime,
  token varchar(255) unique,
  tokenExpiredSeconds integer default -1,
  username varchar(255),
  clientId varchar(255),
  tokenType varchar(255),
  refreshToken varchar(255) unique,
  refreshTokenExpiredSeconds integer default -1
  
);

DROP TABLE  IF EXISTS lemon_jersey_oauth2_access_token_code;
CREATE TABLE lemon_jersey_oauth2_access_token_code (
  id int auto_increment primary key,
  guid varchar(255) not null unique,
  createTime datetime,
  updateTime datetime,
  code varchar(255) unique,
  username varchar(255),
  clientId varchar(255)
);






