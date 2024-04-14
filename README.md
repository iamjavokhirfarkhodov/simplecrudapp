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
<details>
	<summary>Management</summary>
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
		<summary>Working with the faculty table</summary>
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
</details>
