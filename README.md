# AI Automation Agency Enterprise Architecture

A robust, console-based Enterprise Resource Planning (ERP) and management system designed for an AI Automation Agency. Built entirely in Java using pure Object-Oriented Programming (OOP) design principles, this system handles client CRM, employee infrastructure management, project tracking, flat-file data persistence, and an account billing ledger.

---

## 🚀 Features

### 1. Centralized Authentication
* **Role-Based Access Control:** Secure portal separation for Administrators and Employees.
* **Admin Override:** Universal admin bypass for high-level management actions.
* **Default Employee Access:** Automatic workspace creation for newly provisioned employee accounts.

### 2. Comprehensive Core Modules
* **Client CRM:** Add, view, update baseline records, or remove corporate enterprise client nodes.
* **Workforce Infrastructure:** Track employee profiles, update titles, adjust base salaries, and view assigned project workflows.
* **Project Ecosystem:** Design and launch new projects, assign specialized employee architects, select core AI service packages, and manage lifecycle pipeline states (`Pending`, `In Progress`, `Completed`).
* **Billing Ledger:** Generate tracking invoices and monitor financial performance.

### 3. Performance Metrics & Analytics
* Real-time aggregation of key system metrics:
    * Total tracked corporate clients, workforce units, and active engagements.
    * **Liquidated Assets:** Revenue collected from cleared (Paid) invoices.
    * **Accounts Receivable:** System pipeline deficit balance from outstanding (Unpaid) invoices.

### 4. Robust File-Based Persistence
* Utilizes flat-file parsing (`.txt` formats) with custom delimiters (`|`) to save and reload system states continuously across runtimes. 
* Automatic initialization of missing system files at launch.

---

## 🛠️ System Architecture & OOP Principles

This project serves as a showcase for core **Object-Oriented Programming (OOP)** pillars:

* **Abstraction:** Leverages an abstract base class (`User`) to define common traits while keeping structural implementation flexible.
* **Inheritance:** Specialized extensions of the `User` class (`Client` and `Employee`) inherit foundational properties while implementing domain-specific fields.
* **Encapsulation:** All fields across data entities are marked `private` and accessed securely via explicit getters and setters, maintaining strict data integrity constraints.
* **Polymorphism:** Implements dynamic method overriding through the `displayDashboard()` function across different user contexts to present completely different UI workspaces at runtime.

---

## 📁 File Structure

```text
├── User.java              # Abstract base class enforcing Abstraction & Encapsulation
├── Client.java            # Client model extending User (Inheritance & Polymorphism)
├── Employee.java          # Employee model extending User with assignment strings
├── ServicePackage.java    # AI Service offering definitions
├── Project.java           # Project lifecycle model
├── Invoice.java           # Billing ledger item model
├── FileManager.java       # Flat-file I/O data engine handler
├── Authentication.java    # Validation logic for system logins
├── AgencyManager.java     # Main operational business logic and CRUD workflows
└── MenuSystem.java        # Main central entry point containing the UI console engine
