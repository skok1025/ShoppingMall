-- used in tests that use HSQL
drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARBINARY(4096),
  autoapprove VARCHAR(256)
);

drop table if exists oauth_client_token;
create table oauth_client_token (
  token_id VARCHAR(256),
  token VARBINARY(4096),
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);


drop table if exists oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(256),
  token VARBINARY(4096),
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication VARBINARY(4096),
  refresh_token VARCHAR(256)
);

drop table if exists oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(256),
  token VARBINARY(4096),
  authentication VARBINARY(4096)
);

drop table if exists oauth_code;
create table oauth_code (
  code VARCHAR(256), authentication VARBINARY(4096)
);

drop table if exists oauth_approvals;
create table oauth_approvals (
   userId VARCHAR(256),
   clientId VARCHAR(256),
   scope VARCHAR(256),
   status VARCHAR(10),
   expiresAt TIMESTAMP,
   lastModifiedAt TIMESTAMP
);

-- customized oauth_client_details table
drop table if exists ClientDetails;
create table ClientDetails (
  appId VARCHAR(255) PRIMARY KEY,
  resourceIds VARCHAR(256),
  appSecret VARCHAR(256),
  scope VARCHAR(256),
  grantTypes VARCHAR(256),
  redirectUrl VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARBINARY(4096),
  autoApproveScopes VARCHAR(256)
);

INSERT INTO oauth_client_details(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, WEB_SERVER_REDIRECT_URI, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY, ADDITIONAL_INFORMATION, AUTOAPPROVE) VALUES('mall', '', '1234', 'ADMIN', 'password, authorization_code, refresh_token', '', '', null, null, '{}', '');
