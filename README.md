# 🚀 Expense Tracker – Full-Stack Application (Spring Boot + HTML/JS)

A simple and elegant personal expense tracking application built using:

Spring Boot (REST API + JWT Authentication)

Spring Security 6 + JWT

HTML, CSS & Vanilla JavaScript frontend

PostgreSQL / MySQL database

Users can sign up, log in, add expenses, filter them by time period, and delete them — all protected with JWT.

# 📸 Screenshots
## 🔐 Authentication Page

(Signup + Login)
<img width="1705" height="977" alt="Screenshot 2025-11-30 at 6 15 54 PM" src="https://github.com/user-attachments/assets/1d53f277-47ee-46f4-99a0-ccdb22ad18d2" />



## 📊 Dashboard

(Add Expense + Filters + Listings)
<img width="1710" height="980" alt="Screenshot 2025-11-30 at 6 16 30 PM" src="https://github.com/user-attachments/assets/b2d5a394-9c65-4342-b6ea-6235d562312e" />


# ✨ Features
## 🔐 Authentication

User Sign-up (with password confirmation on frontend)

User Login

JWT Token generation on login/signup

Token stored in localStorage on frontend

Protected API routes for all expense operations

## 💸 Expense Management

Add an expense (amount, date, description, category)

View all expenses for the logged-in user

Filter expenses by:

Past week

Past month

Last 3 months

Custom date range

Delete an expense

## 🎨 Clean Frontend UI

Beautiful, modern, minimal UI using plain HTML + CSS

Fully responsive centered design

Frontend pages:

index.html – Authentication

dashboard.html – Main app dashboard

# 🧱 Tech Stack
## Backend

Java 17+

Spring Boot 3

Spring Security 6

JWT Authentication

Spring Data JPA

PostgreSQL / MySQL

## Frontend

HTML5

CSS3

Vanilla JavaScript (no frameworks)

Fetch API

# 🚦 Project Structure
```
src/
 └── main/
      ├── java/com/example/expensetracker/
      │       ├── config/          # Security + JWT config
      │       ├── controller/      # REST controllers
      │       ├── dto/             # Request/Response DTOs
      │       ├── enums/           # ExpenseCategory enum
      │       ├── model/           # JPA Entities
      │       ├── repository/      # Spring Data repositories
      │       ├── service/         # Business logic
      │
      └── resources/
              ├── static/          # index.html + dashboard.html
              ├── application.yml

```
# ⚙️ Setup Instructions
## 1️⃣ Clone the Repository
```
git clone https://github.com/yourusername/expense-tracker.git
cd expense-tracker
```
## 2️⃣ Configure Database (PostgreSQL / MySQL)

In application.properties or application.yml:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/expensedb
spring.datasource.username=youruser
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
## 3️⃣ Run the Backend

Using IntelliJ or:
```
mvn spring-boot:run
```

Backend runs on:

http://localhost:8080

## 4️⃣ Access Frontend

Open in browser:

http://localhost:8080/index.html


Dashboard loads after successful login/signup.

# 🔐 API Routes
```
Authentication
Method	Endpoint	Description
POST	/api/auth/signup	Create user + return JWT
POST	/api/auth/login	Login + return JWT
Expenses
Method	Endpoint	Description
GET	/api/expenses	Get all expenses
POST	/api/expenses	Add new expense
PUT	/api/expenses/{id}	Update expense
DELETE	/api/expenses/{id}	Delete expense
```
# 🛡 JWT Security

Every protected route requires Authorization: Bearer <token>

Custom JwtAuthenticationFilter validates tokens

SecurityConfig exposes /api/auth/** publicly

🧪 Sample Expense Request (JSON)
```
{
  "amount": 1500,
  "date": "2025-11-29",
  "description": "Grocery shopping",
  "expenseCategory": "GROCERIES"
}
```
# 🚀 Future Enhancements

Edit expenses in UI

Monthly / category-wise charts

Export expenses to PDF/Excel

Dark mode

# 🤝 Contributing

Pull requests are welcome!

# ⭐ Support

If this project helped you, please star the repo on GitHub ❤️
