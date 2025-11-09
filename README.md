# JournalApplication

JournalApplication is a web application built with Spring Boot, designed to help users manage and record their personal journals securely. It features authentication and authorization, ensuring that only authenticated users can access and manage their entries.

## Features

- User registration and login
- Secure authentication and authorization (Spring Security)
- Create, view, update, and delete journal entries
- User-specific journals (each user's entries are kept private)
- RESTful API endpoints for CRUD operations
- Data persistence using a database (e.g., MongoDB)
- Error handling and validation

## Technologies Used

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Maven
- MongoDB

## Getting Started

### Prerequisites

- Java 21
- Maven
- Git

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/HarshalShisode/JournalApplication.git
    cd JournalApplication
    ```

2. Build and run the application:
    ```bash
    mvn spring-boot:run
    ```

3. Access the application:
    - API endpoints: `/api/...`

### Configuration

- Default database is MongoDB. You can configure other databases in `src/main/resources/application.properties`.
- Update authentication settings, secret keys, and other configuration in the properties file.

## Usage

- Register a new user.
- Log in to access personal journal dashboard.
- Create, update, or delete entries.

