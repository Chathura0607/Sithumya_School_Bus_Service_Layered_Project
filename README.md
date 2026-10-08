# 🚌 Sithumya School Bus Service Management System

<p align="center">
  <img src="src/main/resources/asserts/icon/logo.png" alt="Sithumya School Bus Service Logo" width="180"/>
</p>

<p align="center">
  <b>A Modern, Robust & Layered Architecture Enterprise Desktop Solution for School Transportation Management</b>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 17" />
  <img src="https://img.shields.io/badge/JavaFX-22-blue?style=for-the-badge&logo=java&logoColor=white" alt="JavaFX 22" />
  <img src="https://img.shields.io/badge/MySQL-8.3.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL" />
  <img src="https://img.shields.io/badge/JasperReports-6.20+-red?style=for-the-badge&logo=adobe&logoColor=white" alt="JasperReports" />
  <img src="https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven" />
  <img src="https://img.shields.io/badge/Architecture-Layered-22C55E?style=for-the-badge" alt="Layered Architecture" />
</p>

---

## 📖 Overview

**Sithumya School Bus Service Management System** is a standalone enterprise desktop application designed to streamline and automate daily school transportation operations. Built strictly adhering to the **Standard Layered Architecture Design Pattern** (*Controller $\rightarrow$ Business Object (BO) $\rightarrow$ Data Access Object (DAO) $\rightarrow$ Database*), it guarantees maximum scalability, loose coupling, separation of concerns, and robust ACID transaction handling.

---

## ✨ Key Features

### 1. 📊 Modern Interactive Dashboard
* **Real-time Metrics**: Instant counts of registered students, active drivers, and fleet buses.
* **School Distribution Visualizer**: Integrated JavaFX BarChart depicting student density across various schools.
* **Today's Payments**: Live monitoring table of fee collections processed throughout the day.
* **One-Click Dispatch**: Quick transit schedule dispatch to guardians with dynamic departure/arrival updates.

### 2. 🚸 Student & Guardianship Management
* Complete lifecycle management for enrolled students.
* Route mapping, school allocation, and assigned bus fleet tracking.
* Comprehensive guardian linkage with multi-contact and emergency notification support.

### 3. 🚌 Fleet & Fueling Records
* Real-time tracking of school buses, assigned drivers, and operational status.
* Maintenance event logging (servicing costs, parts replacement, service dates).
* Fueling logs tied to partner filling stations with automated debt and payment calculations.

### 4. 💳 Flexible Payment & Invoicing Plans
* Multi-tier payment installment plans (Customizable installment counts).
* Automated monthly fee computations with discounts and penalty rules.
* **Modern JasperReports Receipts**: Professionally designed invoice receipts with corporate headers, student details, payment breakdowns, and status indicators generated on-demand.

### 5. 📧 Asynchronous Automated Email Notification Engine
* Powered by background daemon thread executors to ensure zero UI lag during dispatch.
* **Responsive HTML Email Templates**:
  - **Bus Arrival Notifications**: Morning departure times and pickup alerts.
  - **Bus Return Notifications**: Afternoon school pickup and home drop-off alerts.
  - **Security Alerts**: Admin login notifications with timestamp and device security badges.
  - **Password Reset**: Fast OTP generation and verification for admin password recovery.

### 6. 🔒 Enterprise Security & Validation
* Regex-driven real-time validation for Sri Lankan NICs, RFC-compliant emails, contact numbers, and currency figures.
* Green/Red interactive visual feedback on all input forms.
* Robust SQL transaction boundaries ensuring ACID integrity during multi-table writes.

---

## 🏗️ Architecture Design Pattern

The project strictly follows the **Layered Architecture (N-Tier)**:

```
┌─────────────────────────────────────────────────────────┐
│                    UI / View Layer                      │
│        (JavaFX Views, FXML Forms, Modern CSS)           │
└────────────────────────────┬────────────────────────────┘
                             │
┌────────────────────────────▼────────────────────────────┐
│                    Controller Layer                     │
│    (User Interaction, Event Handling, Regex Validator)   │
└────────────────────────────┬────────────────────────────┘
                             │
┌────────────────────────────▼────────────────────────────┐
│              Business Logic Layer (BO)                  │
│       (BOFactory, Business Rules, Transaction Logic)    │
└────────────────────────────┬────────────────────────────┘
                             │
┌────────────────────────────▼────────────────────────────┐
│              Data Access Layer (DAO)                    │
│      (DAOFactory, CurdDAO, Entity Mapping, SQL Query)   │
└────────────────────────────┬────────────────────────────┘
                             │
┌────────────────────────────▼────────────────────────────┐
│                  Database / Storage                     │
│              (MySQL Connection Pool Singleton)          │
└─────────────────────────────────────────────────────────┘
```

---

## 🛠️ Technology Stack

| Component | Technology | Version |
|---|---|---|
| **Language** | Java | 17 (LTS) |
| **GUI Framework** | JavaFX / JFoenix | 22.0.1 / 9.0.10 |
| **Database** | MySQL | 8.0+ (Connector/J 8.3.0) |
| **Reporting** | JasperReports | 6.20.1 |
| **Email Service** | JavaMail API | 1.4.7 |
| **Boilerplate Reduction** | Project Lombok | 1.18.30 |
| **Build Tool** | Apache Maven | 3.9.9 (Maven Wrapper included) |

---

## 🚀 Getting Started

### 1. Prerequisites
* **Java Development Kit (JDK)**: Version 17 or higher
* **MySQL Server**: 8.0+ running on port `3306`

### 2. Database Setup
1. Open your MySQL client (e.g., MySQL Workbench, phpMyAdmin, or CLI).
2. Execute the database initialization script located at:
   ```
   src/main/resources/databaseQuary/query.sql
   ```
3. Update your database credentials if necessary in [`DbConnection.java`](src/main/java/lk/ijse/sithumya/dbConnection/DbConnection.java):
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/school_bus_service_management_system";
   private static final String USER = "root";
   private static final String PASSWORD = "your_mysql_password";
   ```

### 3. Build & Run Application
Clone the repository and run the project using the included Maven Wrapper:

```powershell
# Clone repository
git clone https://github.com/Chathura0607/Sithumya_School_Bus_Service_Layered_Project.git

# Navigate into directory
cd Sithumya_School_Bus_Service_Layered_Project

# Compile and run
.\mvnw.cmd clean javafx:run
```

---

## 👥 Default Login Credentials

| Role | Username | Default Password |
|---|---|---|
| **Administrator** | `Chathura` | `1234` |

---

## 📁 Project Directory Structure

```
Sithumya_School_Bus_Service_Layered_Project
├── src
│   ├── main
│   │   ├── java
│   │   │   └── lk/ijse/sithumya
│   │   │       ├── AppInitializer.java          # JavaFX Entry Point
│   │   │       ├── bo/                         # Business Object Interfaces & Implementations
│   │   │       ├── controller/                 # JavaFX UI Controllers
│   │   │       ├── dao/                        # Data Access Object Interfaces & Implementations
│   │   │       ├── dbConnection/               # Singleton Database Connection Manager
│   │   │       ├── dto/                        # Data Transfer Objects
│   │   │       ├── entity/                     # Database Entity Models
│   │   │       ├── sendMail/                   # Async HTML Email Service
│   │   │       ├── util/                       # Regex, Navigation, Transactions, Utilities
│   │   │       └── view/tm/                    # Table Model Classes
│   │   └── resources
│   │       ├── asserts/                        # Icons, Images & Branding
│   │       ├── databaseQuary/                  # MySQL Schema & Seed SQL Queries
│   │       ├── reports/                        # Modern JasperReports (.jrxml)
│   │       ├── style/                          # Modernized CSS Design System
│   │       └── view/                           # FXML UI Layout Definitions
├── pom.xml                                     # Maven Dependencies & Configuration
└── README.md                                   # Project Documentation
```

---

## 📜 License

This project is developed as an academic and enterprise portfolio project by **Chathura**.  
All rights reserved.
