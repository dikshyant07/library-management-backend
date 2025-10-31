# Library Management System (Backend)

## Description

A backend application for managing a library system. Features include:

- CRUD operations for books, categories, and users
- Borrowing and returning books with due dates
- Role-based access control (ADMIN, MEMBER) using Spring Security
- Validation and exception handling
- Email notifications on user registration
- API documentation and testing via Swagger UI

---

## Tech Stack

- Java 17+
- Spring Boot
- PostgreSQL
- Spring Security
- Spring Data JPA / Hibernate
- MapStruct
- Jakarta Bean Validation
- JavaMailSender
- Swagger UI

---

## Roles

- **MEMBER**: Can borrow/return books and view own profile
- **ADMIN**: Full access to books, categories, users, and loans

---

## Getting Started

1. Clone the repository:

```bash
git clone https://github.com/yourusername/library-management-backend.git
cd library-management-backend
