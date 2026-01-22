# Campus Report Backend

Campus Report is a graduation project developed to improve communication between university students and administration by providing a digital reporting system for campus-related issues.

This repository contains the **backend** of the system, developed with **Spring Boot**.

---

## 📌 Project Overview

Campus Report allows students to report problems they encounter on campus such as:
- Infrastructure issues
- Cleanliness problems
- Technical failures
- Safety-related concerns

Reports are reviewed and managed by **administrators** and **staff members** through role-based access.

---

## 🚀 Features

- User authentication (JWT-based)
- Role-based authorization (STUDENT / ADMIN / STAFF)
- Report creation, listing, and status tracking
- Admin panel for user and report management
- Staff panel for category-based report handling
- Password hashing and security configurations
- Forgot password functionality via email

---

## 🛠 Technologies Used

- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- MySQL
- JPA / Hibernate
- Maven

---

## 🔐 Security

- JWT-based authentication
- Passwords stored using hashing
- Role-based access control
- Sensitive configuration files are excluded from the repository

---

## ⚙️ Configuration & Setup

> **Important:**  
> `application.properties` is intentionally excluded from this repository for security reasons!!!!

To run the project locally:

1. Clone the repository
2. Create a MySQL database
3. Add your own `application.properties` file 
