# API Documentation

Login is required to use the APIs. A JWT token is automatically assigned to the user upon successful login, and all subsequent requests are processed only if the token is found. Otherwise, the server will return a 403 response. <br>

POST - "/login" <br>
{
    "username": "superadmin",
    "password": "123456"
}
{
    "username": "admin",
    "password": "1234567"
}
{
	".......": "......"
}

The system has 3 main API routes
- "api/v1/management/**"
- "api/v1/students/**"
- "signup/student"



/* MANAGEMENT */
"api/management/**" - only system admins can use this path. You can access the API paths available in the system and make requests to them

1). Working with the Users table.
POST - "api/v1/management/users/create"
Authorization - <jwt_token>
{
    "username": "....",
    "password": "...."
}
POST - "api/v1/management/users/enable/{id}"
POST - "api/v1/management/users/disable/{id}"


2). Work with the student schedule
GET - "api/v1/management/students"
GET - "api/v1/management/students/{id}"
GET - "api/v1/management/students/by?name=..."
Param
name = "..."


3). Work with the faculty table
POST - "api/v1/management/faculties"
{
	"name": "....."
}
GET - "api/v1/management/faculties"
GET - "api/v1/management/faculties/{id}"
DELETE - "api/v1/management/faculties/{id}"
PUT - "api/v1/management/faculties/{id}"
{
    "name": "....."
}


4). Working with the table of roles

POST - "api/v1/management/roles"
{
	"name": "ROLE_NAME"
}

POST - "api/v1/management/roles/assign/{user_id}"
{
	"id": ...
}
DELETE - "api/v1/management/roles/remove/{user_id}"
{
	"id": ...
}
GET - "api/v1/management/roles"
GET - "api/v1/management/roles/{role_id}"
DELETE - "api/v1/management/roles/{role_id}"
PUT - "api/v1/management/roles/{role_id}"


/* STUDENTS */
"api/v1/students/**" - With this API, students can get their data, change it and launch their account.

PUT - "api/v1/students/{id}"
{
    "firstname": "Javoxir",
    "lastname": "Farxodov",
    "birth_date": "1998-08-02",
    "address": "22 Uchtepa"
}
GET - "api/v1/students/{id}"
DELETE - "api/v1/students/{id}"

/* SIGNUP */
"signup/student" - This API is open to everyone for student registration.

POST - "signup/student"
{
    "username": "....",
    "password": "....",
    "firstname": "....",
    "lastname": "....",
    "birth_date": "....",
    "faculty": ....,
    "address": "...."
}
