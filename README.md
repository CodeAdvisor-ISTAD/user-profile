# CodeAdvisors User Profile ⚙️

![CodeAdvisors Logo](http://167.172.78.79:8090/api/v1/files/preview?fileName=b5d01918-2824-48d7-83e0-fb557ce6bd73_2024-12-21T18-28-24.856529397.jpg)

## Overview 🌐
The **User-Profile Service** is a core component of the **CodeAdvisors**  platform that manages user profiles, tracks activities, and enables personalization. It supports features like profile editing with validation, tracking reading history, bookmarking resources, achievement badges, and achievement levels.

## Features ✨
- **Editable Profile Information**: Update personal details such as name, email, bio, and profile picture.
- **Reading History**: Track and view previously accessed articles, tutorials, and other resources.
- **Bookmarking**: Save resources for quick and organized access.
- **Achievement Badges**: Earn recognition for milestones
- **Achievement Levels**: Progress through defined stages of activity.

---

## Templates for Testing UI 🖥️

The Identity Service includes the following HTML templates located in the `src/main/resources/templates` directory:

1. **user-profile.html**:  
   Used to view user information.

2. **edit-user-profile.html**:  
   Used to edit user information.

---

## Technologies Used ⚙️

- **Spring Boot**: Backend framework for building RESTful APIs and authentication.
- **Spring Security**: For managing OAuth2 and resource server functionality.
- **JPA with MongoDB**: For storing user information and data.

---

## Prerequisites 📦

1. **JDK 21**: Required for building and running the service.
2. **MongoDB Database**: Ensure it is set up with the necessary configurations:
    - URL: `mongodb://localhost:27017/db-edit-profile`
    - Username: `user-service`
    - 
3. **Eureka Server**: Required for service registration and discovery.

---

## Running the Service 🚀

1. Clone the repository:
   ```bash
   git clone https://github.com/CodeAdvisor-ISTAD/user-profile.git
   cd user-profile
   ```

2. Build the project:
   ```bash
   ./gradlew build
   ```

3. Start the application:
   ```bash
   ./gradlew bootRun
   ```

4. Access the service via `http://localhost:8080`.

---

## Testing the UI 🧪

1. **Setup**: Start the service and ensure the database are running.
2. **Access Templates**: Open the following endpoints in your browser:
    - **Login**: `http://localhost:9090/login`
    - **Registration**: `http://localhost:9090/register`
    - **Password Recovery**: `http://localhost:9090/forget-password`
3. **Test Flows**:

---

## License 📜
This project is licensed under the MIT License. See the LICENSE file for more details.

---

Built with ❤️ by the CodeAdvisors Team.
