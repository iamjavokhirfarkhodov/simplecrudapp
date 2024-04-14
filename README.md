# API Documentation

Login is required to use the APIs. A JWT token is automatically assigned to the user upon successful login, and all subsequent requests are processed only if the token is found. Otherwise, the server will return a 403 response. <br>
<details>
<summary><strong>Login</strong></summary>
POST - "/login" <br>
{<br>
    "username": "superadmin",<br>
    "password": "123456"<br>
}<br>
{<br>
    "username": "admin",<br>
    "password": "1234567"<br>
}<br>
{<br>
	".......": "......"<br>
}<br>
</details>
The system has 3 main API routes <br>
- "api/v1/management/**" <br>
- "api/v1/students/**" <br>
- "signup/student" <br>

<br>
<br>

<details>
<summary><strong>Available endpoints</strong></summary>
<details>
<summary>Management</summary>
"api/management/**" - only system admins can use this path. You can access the API paths available in the system and make requests to them <br>
<br>
1). Working with the Users table.
POST - "api/v1/management/users/create" <br>
Authorization - <jwt_token> <br>
{<br>
    "username": "....",<br>
    "password": "...."<br>
}<br>
POST - "api/v1/management/users/enable/{id}" <br>
POST - "api/v1/management/users/disable/{id}" <br>
<details>
<summary>2). Work with the Student table</summary>summary>
GET - "api/v1/management/students" <br>
GET - "api/v1/management/students/{id}" <br>
GET - "api/v1/management/students/by?name=..." <br>
Param<br>
name = "..." <br>
</details>

<details>
<summary>3). Work with the faculty table</summary>summary>
POST - "api/v1/management/faculties"<br>
{<br>
	"name": "....."<br>
}<br>
GET - "api/v1/management/faculties" <br>
GET - "api/v1/management/faculties/{id}" <br>
DELETE - "api/v1/management/faculties/{id}" <br>
PUT - "api/v1/management/faculties/{id}" <br>
{<br>
    "name": "....." <br>
}<br>
</details>

<details>
<summary>4). Working with the table of roles </summary>
<br>
POST - "api/v1/management/roles" <br>
{<br>
	"name": "ROLE_NAME" <br>
}<br>
<br>
POST - "api/v1/management/roles/assign/{user_id}" <br>
{<br>
	"id": ... <br>
}<br>
DELETE - "api/v1/management/roles/remove/{user_id}" <br>
{<br>
	"id": ... <br>
}<br>
GET - "api/v1/management/roles" <br>
GET - "api/v1/management/roles/{role_id}" <br>
DELETE - "api/v1/management/roles/{role_id}" <br>
PUT - "api/v1/management/roles/{role_id}" <br>
</details>
</details>
<details>
<summary><strong>Students</strong></summary>
"api/v1/students/**" - With this API, students can get their data, change it and launch their account. <br>
<br>
PUT - "api/v1/students/{id}" <br>
{<br>
    "firstname": "Firstname", <br>
    "lastname": "Lastname", <br>
    "birth_date": "1990-01-01", <br>
    "address": "22 Galaxy" <br>
} <br>
GET - "api/v1/students/{id}" <br>
DELETE - "api/v1/students/{id}" <br>
</details>
<details>
<summary>## SIGNUP</summary>
"signup/student" - This API is open to everyone for student registration. <br>

POST - "signup/student" <br>
{<br>
    "username": "....", <br>
    "password": "....", <br>
    "firstname": "....", <br>
    "lastname": "....", <br>
    "birth_date": "....", <br>
    "faculty": ...., <br>
    "address": "...." <br>
}
</details>
