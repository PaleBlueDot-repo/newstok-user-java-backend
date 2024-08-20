# Login SignUp ->
## create database name "userdb" in Mysql

## signup endpoint=http://localhost:8080/user/signup

post call json body=
{
  "email": "testuser@example.com",
  "name": "Test User",
  "password": "password123"
}


## login endpoint=http://localhost:8080/user/login

post call json body=
{
  "email": "testuser@example.com",
  "password": "password123"
}
