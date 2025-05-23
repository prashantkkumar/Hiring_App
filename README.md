# Candidate Onboarding System

## Overview
The Candidate Onboarding System is a web application that simplifies the onboarding process for hired candidates. It manages everything from sending job offer emails to uploading documents and tracking onboarding progress. Designed for HR teams, it offers a reliable and scalable solution using modern Java technologies.

## Key Features
- View hired candidates and their profiles
- Update onboarding statuses (e.g., Offer Sent, Onboarded)
- Send job offer emails automatically
- Update personal, bank, and educational details
- Upload and verify documents (e.g., resumes, IDs)
- Track onboarding progress
- Log system events for monitoring
- Process tasks asynchronously for efficiency

## Technologies Used
- Spring Boot: For building REST APIs
- Spring Data JPA: For database management
- MySQL: Main database for production
- H2 Database: For development and testing
- Spring Cache: For faster data access
- SLF4J with Logback: For logging events and errors
- RabbitMQ: For asynchronous tasks like email sending
- Spring Mail: For sending job offer emails
- Spring AOP: For tracking system performance
- Bean Validation: For checking data accuracy
- RestTemplate: For external API calls
- Multipart File Upload: For document uploads
- Maven: For managing dependencies

## Who Can Use It?
- HR Teams: To manage candidate onboarding
- Developers: To learn about Spring Boot, JPA, and messaging
- Organizations: To automate onboarding workflows

## Getting Started
Prerequisites:
   - Java 17 or higher
   - Maven
   - MySQL (production)
   - RabbitMQ (local or cloud)
   - SMTP server (e.g., Gmail)

License:
This project is licensed under the MIT License. See the LICENSE file for details.

Contact:
For questions or feedback, reach out to prashantoff61@gmail.com.