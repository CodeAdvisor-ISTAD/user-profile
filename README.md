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

## Technologies Used ⚙️
- **Spring Boot**: Microservice framework for creating RESTful APIs.
- **MongoDB**: Database for storing content and tags.
- **Kafka**: For asynchronous communication (if integrated with other services).
- **JPA**: For handling relational data persistence.
- **Eureka**: Service discovery for seamless microservice interaction.
- **Config**: Centralized configuration management.

## User profile Services 📝

### User profile Management 📄
The Content Service handles the profile editing with validation, tracking reading history, bookmarking resources, achievement badges, and achievement levels. Key features include:

- **Create User profile**: Add new new user.
- **Update User Profile**: Modify user information.
- **Search User Profile**: Find User Profile base on username.
- **Soft Delete**: Soft delete user profile without removing it from the system.

#### Endpoints 🚀

- **GET /api/v1/edit_user_profiles/**: Retrieve all user profile.
- **GET /api/v1/edit_user_profiles/${username}: Retrieve user profile by username.

## Setup and Installation 🛠

### Prerequisites 📦
Before running the services, ensure the following are installed and configured:
- **JDK 21** (for building and running the application)
- **MongoDB** (for content and tag storage)
- **Kafka** (if used for inter-service communication)

### Steps to Run 🚶‍♂️

1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/user-profile.git
   cd user-profile
   ```

2. Build the project using Gradle:
   ```bash
   ./gradlew build
   ```

3. Run the services:
   ```bash
   ./gradlew bootRun
   ```

4. Ensure MongoDB and Kafka (if applicable) are running before starting the services.

## License 📜
This project is licensed under the MIT License - see the LICENSE file for details.


Built with ❤️ by the CodeAdvisors Team.
