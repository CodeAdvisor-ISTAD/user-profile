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
- **Eureka**: Service discovery for seamless microservice interaction.
- **Config**: Centralized configuration management.

## User profile Services ✨

### User profile Management 👤
The User Profile feature handles the creation, retrieval, update, deletion, and search of user profile. Key features include:

- **Create User profile**: Add new new user.
- **Update User Profile**: Modify user information.
- **Search User Profile**: Find User Profile base on username and search all user.
- **Disable User Profile**: Soft delete user profile without removing it from the system.
- **Enable User Profile**: Allows to make user profile active again after it has been disabled.
- **Delete User Profile**: Delete user profile and also removing it from the system.

#### Endpoints 🚀

- **POST /api/v1/edit_user_profiles**: Create user profile.
- **GET /api/v1/edit_user_profiles**: Retrieve all user profile.
- **GET /api/v1/edit_user_profiles/${username}**: Retrieve user profile by username.
- **PATCH /api/v1/contents/{username}**: Update user profile by username.
- **PATCH /api/v1/edit_user_profiles/${username}/disable**: Disable user profile by username.
- **PATCH /api/v1/edit_user_profiles/${username}/enable**: Enable user profile by username.
- **DELETE /api/v1/edit_user_profiles/${username}**: Delete user profile by username.

### Achievement Badge Management 🌟
The Achievement Badge feature handles the creation, retrieval, update, deletion, and search of user profile. Key features include:

- **Create Badge**: Add new new badge.
- **Update Badge**: Modify badge.
- **Search Badge**: Find badge by badgeName and find all badge.
- **Soft Delete Badge**: Soft delete badge without removing it from the system.
- **Enable Badge**:  Allows to make a badge public again after it has been soft deleted.
- **Delete User Profile**: Delete badge and also removing it from the system.

#### Endpoints 🚀

- **POST /api/v1/achievement_badges**: Create badge.
- **GET /api/v1/achievement_badges**: Retrieve all badge.
- **GET /api/v1/achievement_badges/${badgeName}**: Retrieve badge by badgeName.
- **PATCH /api/v1/achievement_badges/${badgeName}**: Update badge by badgeName.
- **PATCH /api/v1/achievement_badges/${badgeName}/is_deleted**: Soft delete badge by badgeName.
- **PATCH /api/v1/achievement_badges/${badgeName}/is_published**: Publish badge by badgeName.
- **DELETE /api/v1/achievement_badges/${badgeName}**: Delete badge by badgeName.
  
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
