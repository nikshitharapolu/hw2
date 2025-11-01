# Homework 2 - Expense Tracker App

This project is an improvised version of the original Expense Tracker app. It enables the users to add and manage  transactions with enhanced architecture and testability using the MVC pattern and design principles.

---

## Project Overview

This version of the Expense Tracker implements the following:

- MVC architecture
- Strategy design pattern for filtering
- Input validation for amount and category
- Test cases (JUnit 4.11)
- Export to CSV functionality design

---

## Features

- Add transactions
- Input validation:
  - Amount must be a number between 0 and 1000
  - Category must be one of: 'Food', 'Travel', 'Bills', 'Entertainment', 'Other'
- Display transactions in a table format and display the total amount at the end
- Calculate and update total amount every time a new transaction is added
- Filters can be applied on:
  - Amount
  - Category


---

## Build & Test Instructions (Terminal)

1. Make sure you have Apache Ant installed.
2. In the project root directory (contains `build.xml`), run the following commands:

```bash

Test Cases (JUnit 4.11)
Located in: test/TestExample.java

Test coverage:
- Add transaction
- Invalid Input Handling (for both Amount and Category)
- Filter by Amount
- Filter by Category


Test screenshot: test-screenshot.png

Folder Structure:
expense_tracker/
├── src/
│   ├── controller/
│   │   └── ExpenseTrackerController.java
│   │   └── InputValidation.java
│   ├── model/
│   │   ├── ExpenseTrackerModel.java
│   │   ├── Transaction.java
│   │   ├── TransactionFilter.java
│   │   ├── AmountFilter.java
│   │   └── CategoryFilter.java
│   └── view/
│       └── ExpenseTrackerView.java
│   └── ExpenseTrackerApp.java
├── test/
│   └── TestExample.java
├── jdoc/
├── export.txt
├── gitlog.txt
├── test_screenshot.png
└── README.md

Design Principles Followed:

Modularity: Clearly separated Model, View, and Controller components.

Open-Closed Principle: Transaction filters are implemented using the Strategy pattern.

Strategy Pattern: Added support for flexible filter extension (category, amount).

Immutability & Encapsulation: Transaction objects and transaction list are immutable and encapsulated.

Export to CSV functionality:

Export allows the user to export all the current transactions into a .csv file and store it. 

It is to be implemented across all 3 layers: Model, View and Controller.


Notes:

Git history is saved in gitlog.txt

All test cases passed are shown in the test screenshot.