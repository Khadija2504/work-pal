# WorkPal - Coworking Space Management Platform

## Project Overview

**WorkPal** is a coworking space management platform designed to optimize the operations of coworking spaces and improve the experience for both members and space managers. With the rise of remote work and flexible office environments, coworking spaces provide a valuable solution for freelancers, startups, and businesses. This platform centralizes management processes such as space reservations, subscriptions, payments, and member services.

## Features

### For Members

- **Account Management**:  
  - Create and manage your profile, with options to update personal information and reset forgotten passwords.
  
- **Space Reservation**:  
  - Search for available coworking spaces and meeting rooms by date, time, and type.
  - Easily book spaces online and receive email confirmation.
  
- **Subscription Plans**:  
  - Subscribe to flexible membership plans.
  - Renew or cancel your subscription.
  - View billing history.
  
- **Additional Services**:  
  - Reserve additional services like equipment rentals or cleaning services.
  
- **Event Calendar**:  
  - Browse upcoming events and workshops and register for them.

- **Reservation Management**:  
  - View and manage upcoming reservations.
  - Cancel reservations and receive confirmation via email.

- **Feedback and Ratings**:  
  - Leave reviews and ratings for spaces after use to help improve the service.

### For Space Managers

- **Space Management**:  
  - Add new coworking spaces and meeting rooms with detailed attributes (size, equipment, etc.).
  - Update or delete spaces as needed.

- **Reservation Management**:  
  - View current and past reservations for space utilization tracking.
  - Modify or cancel reservations as necessary.

- **Member Management**:  
  - Add, modify, or delete member accounts.
  - Assign special roles and adjust membership levels.

- **Subscription Management**:  
  - Manage various subscription plans.
  - Generate invoices and view payment history for each member.
  
- **Reporting and Analytics**:  
  - Generate reports on space usage, revenue, and subscription details.
  - View real-time statistics on occupancy and revenue performance.

- **Notifications**:  
  - Receive notifications for new bookings, cancellations, or significant changes.

### For Administrators

- **User Management**:  
  - Add, update, or delete member and manager accounts.
  
- **System Configuration**:  
  - Configure application settings, such as space types, reservation policies, and payment options.
  
- **Access Management**:  
  - Assign roles and permissions to control access to specific features for managers and members.

## Technologies Used

- **Programming Language**: Java
- **Database**: PostgreSQL
- **Libraries/Frameworks**:  
  - Java Time API for date and time handling.
  - Java Streams, Collections, Optional, and HashMap for data processing.
  
- **Design Patterns**:  
  - Singleton Pattern for shared services.
  - Repository Pattern for database interaction.

- **Architecture**: Layered architecture with separation of concerns.
  
## Requirements

- **Java Version**: 11 or higher
- **Database**: PostgreSQL (version 13+)
- **Build Tool**: Maven/Gradle

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/workpal.git
   ```bash

   Navigate to the project directory:

```bash
cd workpal
```bash
Build the project:

```bash
mvn clean install
```bash
Set up the PostgreSQL database:

Create a new database named workpal_db.
Run the provided SQL scripts located in the /src/main/resources/sql/ directory to create tables.
Configure the application.properties file:

Update the PostgreSQL connection details:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/workpal_db
spring.datasource.username=your_username
spring.datasource.password=your_password
Run the application:

```bash
mvn spring-boot:run
```bash

conception uml link
```link
https://drive.google.com/file/d/14vl1HoZ-mESLsmEFxnK0nw12sO-ZJbgd/view?usp=sharing
```link
jira link

https://khadja-ourraiss.atlassian.net/jira/software/projects/WOR/boards/2?sprintStarted=true&sprints=2

