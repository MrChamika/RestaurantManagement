# Restaurant Management System

A Java Swing-based desktop application for managing restaurant operations including menu management, order processing, customer management, staff management, and sales reporting.

## Tech Stack

- **Language:** Java
- **UI Framework:** Swing with FlatLaf (Flat Light Look and Feel)
- **Database:** MySQL
- **Build Tool:** Apache Ant (NetBeans project)
- **Reporting:** JasperReports

## Features

- **Dashboard** — Overview of sales, orders, and key metrics
- **Menu Management** — Add, edit, and categorize menu items with availability status
- **Order Management** — Place and manage customer orders
- **Customer Management** — Track customer details and history
- **Staff Management** — Manage staff information
- **Reports** — Sales reports, popular items, and customer history (JasperReports)

## Database Setup

1. Install MySQL and create a database:
   `sql
   CREATE DATABASE restaurant_db;
   `

2. Run the setup script:
   `ash
   mysql -u root -p restaurant_db < database/setup.sql
   `

3. Update database credentials in src/com/restaurant/db/DBConnection.java.

## How to Run

### Using NetBeans
1. Open the project in NetBeans
2. Build and run

### Using Ant
`ash
ant clean jar
java -jar dist/RestaurantManagement.jar
`

## Project Structure

`
src/com/restaurant/
+-- App.java                 # Entry point
+-- controller/              # Business logic controllers
+-- dao/                     # Data access objects (interfaces)
+-- dao/impl/                # DAO implementations
+-- db/                      # Database connection
+-- exception/               # Custom exceptions
+-- factory/                 # Factory pattern (DAOFactory)
+-- model/                   # Domain models
+-- report/                  # JasperReports manager
+-- view/                    # Swing UI panels
`

## License

MIT
