<h1>About</h1>
<p>Simples CRUD application using Java and Spring Boot</p>
<h1>API Documentation</h1>

Login is required to use the APIs. A JWT token is automatically assigned to the user upon successful login, and all subsequent requests are processed only if the token is found. Otherwise, the server will return a 403 response. <br>

<p>The system has 4 main API routes<br>
- "/login"<br>
- "api/v1/management/**"<br>
- "api/v1/students/**"<br>
- "signup/student"<br>
</p>

<details>
	<summary><strong>Login</strong></summary>
	<p>POST - "/login"<br>
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
	</p>
</details>
<details>
	<summary><strong>Management</strong></summary>
	<p>"api/management/**" - only system admins can use this path. You can access the API paths available in the system and make requests to them</p>
	<details>
		<summary>Working with Users table.</summary>
		<p>POST - "api/v1/management/users/create"</p>
		<p>Authorization - "jwt_token"<br>
		{<br>
		"username": "....",<br>
		<p>"password": "...."<br>
		}<br>
		</p>
		<p>POST - "api/v1/management/users/enable/{id}"</p>
		<p>POST - "api/v1/management/users/disable/{id}"</p>
	</details>
	<details>
		<summary>Working with Student table</summary>
		<p>GET - "api/v1/management/students"</p>
		<p>GET - "api/v1/management/students/{id}"</p>
		<p>GET - "api/v1/management/students/by?name=..."<br>
		Param name = "..."<br>
		</p>
	</details>
	<details>
		<summary>Working with Faculty table</summary>
		<p>POST - "api/v1/management/faculties"<br>
		{<br>
		"name": "....."<br>
		}<br>
		</p>
		<p>GET - "api/v1/management/faculties"</p>
		</p>GET - "api/v1/management/faculties/{id}"</p>
		<p>DELETE - "api/v1/management/faculties/{id}"</p>
		<p>PUT - "api/v1/management/faculties/{id}"<br>
		{<br>
		"name": "....."<br>
		}<br>
		</p>
	</details>
	<details>
		<summary>Working with Roles table </summary>
		<p>POST - "api/v1/management/roles"<br>
		{<br>
		"name": "ROLE_NAME" <br>
		}<br>
		</p>
		<p>POST - "api/v1/management/roles/assign/{user_id}"<br>
		{<br>
		"id": ... <br>
		}<br>
		</p>
		<p>DELETE - "api/v1/management/roles/remove/{user_id}"<br>
		{<br>
		"id": ... <br>
		}<br>
		</p>
		<p>GET - "api/v1/management/roles"</p>
		<p>GET - "api/v1/management/roles/{role_id}"</p>
		<p>DELETE - "api/v1/management/roles/{role_id}"</p>
		<p>PUT - "api/v1/management/roles/{role_id}"</p>
	</details>
</details>
<details>
	<summary><strong>Students</strong></summary>
	<p>"api/v1/students/**" - With this API, students can get their data, change it and launch their account.</p>
	<p>PUT - "api/v1/students/{id}" <br>
	{<br>
	"firstname": "Firstname",<br>
	"lastname": "Lastname", <br>
	"birth_date": "1990-01-01", <br>
	"address": "22 Galaxy" <br>
	}<br>
	</p>
	<p>GET - "api/v1/students/{id}"</p>
	<p>DELETE - "api/v1/students/{id}"</p>
</details>
<details>
	<summary><strong>Sign UP</strong></summary>
	<p>"signup/student" - This API is open to everyone for student registration.</p>	
	</p>POST - "signup/student"<br>
	{<br>
		"username": "....", <br>
		"password": "....", <br>
		"firstname": "....", <br>
		"lastname": "....", <br>
		"birth_date": "....", <br>
		"faculty": ...., <br>
		"address": "...." <br>
	}
	</p>
</details>
<h1>Used technologies</h1>
<div style="display:flex; gap: 5px;">
<img src="./src/main/resources/static/java.png" alt="Description of the image" width="150" height="100">
<img src="./src/main/resources/static/spring.png" alt="Description of the image" width="150" height="100">
<img src="./src/main/resources/static/postgres.png" alt="Description of the image" width="200" height="100">
<img src="./src/main/resources/static/jwt.png" alt="Description of the image" width="200" height="100">
</div>
<details>
	<summary>Dependencies tree</summary>
	|_ _ _ spring-boot-starter-data-jpa
	|
	|_ _ _ spring-boot-starter-security
	|
	|_ _ _ jjwt-api
	|
	|_ _ _ postgresql
	|
	|_ _ _ projectlombok
	|
	|_ _ _ spring-boot-starter-test
	|
	|_ _ _ spring-boot-starter-web
</details>
