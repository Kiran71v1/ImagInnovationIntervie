Employee Income Tax Calculation Service
This is a Spring Boot application that provides RESTful APIs to manage employee details and calculate tax deductions based on salary. The application uses Spring Boot, Spring Data JPA, MySQL, and JWT-based authentication for securing the endpoints.

Features
Employee Management: Create, read, and manage employee details.
Tax Deduction Calculation: Calculate tax deductions based on the employee's salary and applicable tax slabs.
JWT Authentication: Secures the API endpoints with JWT-based authentication.
Technologies Used
Spring Boot
Spring Data JPA
MySQL
JWT (JSON Web Tokens)
Maven
Hibernate
Requirements
Java 8 or higher
Maven 3.6 or higher
MySQL database
Setup Instructions
Clone the Repository

git clone --URI
cd your-repository

Configure MySQL Database

Update the application.properties file with your MySQL database credentials: spring.datasource.url=jdbc:mysql://localhost:3306/employeedb spring.datasource.username=your_username spring.datasource.password=your_password spring.jpa.hibernate.ddl-auto=update spring.jpa.show-sql=true spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

Build and Run the Application mvn clean install mvn spring-boot:run

API Endpoints

User Registration URL: /api/users/register
Method: POST

Description: Register a new user with roles.

Request Body:

json Copy code { "username": "john", "password": "password", "roles": ["ROLE_USER", "ROLE_ADMIN"] } Response: Returns the saved user details (with encoded password).

JWT Authentication Authentication Endpoint Before accessing secured endpoints, you must authenticate and receive a JWT token.

URL: /login

Method: POST

Request Body: { "username": "john", "password": "password" } Response: A JWT token in the following format: { "jwt": "eyJhbGciOiJIUzI1NiJ9..." }

Secured Endpoints All endpoints except /authenticate are secured. Use the JWT token to access the secured endpoints by adding an Authorization header:

Header: Authorization: Bearer

API Endpoints

Save Employee URL: http://localhost:8090/api/employees/save
Method: POST

Description: Save a new employee to the database.

Request Body: { "employeeId": "E123", "firstName": "John", "lastName": "Doe", "email": "john.doe@example.com", "phoneNumbers": ["1234567890", "0987654321"], "doj": "2023-05-16", "salary": 50000 } Response: Returns the saved employee data.

Get Employee Tax Deductions URL: http://localhost:8090/api/employees/{employeeId}/tax-deductions
Method: GET

Description: Retrieves the tax deduction details for a specific employee based on their salary. Response: { "employeeId": "E123", "firstName": "John", "lastName": "Doe", "yearlySalary": 600000, "taxAmount": 37500, "cessAmount": 6000 }
