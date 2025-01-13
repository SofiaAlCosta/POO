# Hunting and Fishing Store

Welcome to the **Hunting and Fishing Store** repository! This project provides a robust backend platform for managing a store specializing in hunting and fishing items. It features tools for managing inventory, categories, and sales while demonstrating key programming concepts and best practices.

---

## Features

### Product Management
- Add, update, and delete products in the inventory.
- Keep track of stock levels and product details.

### Categories
- Organize products into categories such as:
  - **Weapons**: Guns, bows, and other hunting gear.
  - **Bait**: Fishing bait and related accessories.
  - **Clothing**: Specialized attire for hunting and fishing.

### File-Based Persistence
- Data is stored in files (`clientes.dat`, `stock.dat`, `vendas.dat`), ensuring the application can retain information between sessions.


### User-Friendly CLI
- Navigate through a simple and intuitive Command-Line Interface (CLI) to:
  - View inventory.
  - Add new products or categories.
  - Simulate sales.

---

## Technologies Used

- **Programming Language**: Java
- **Data Storage**: File-based (no external database required)
- **Development Concepts**: Object-Oriented Programming (OOP), exception handling, and modular code structure

---

## Key Development Concepts

### Object-Oriented Programming (OOP)
This project leverages OOP principles to ensure the code is modular, reusable, and easy to maintain:

- **Encapsulation**: All data is accessed and modified through clearly defined methods, improving security and maintainability.
- **Inheritance**: Shared behaviors are implemented in base classes and extended for specific functionalities.
- **Polymorphism**: Interfaces and method overriding provide flexibility and scalability.
- **Abstraction**: Simplifies complex operations by focusing on essential details and hiding implementation logic.

### Exception Handling
To ensure a stable and user-friendly system, the project incorporates:

- **Custom Exceptions**: Domain-specific exceptions clarify error handling.
- **Try-Catch Blocks**: Key operations are safeguarded to handle runtime errors gracefully.
- **Logging**: Errors are logged with descriptive messages to assist debugging.
- **Input Validation**: Prevent invalid data entry by validating inputs and throwing appropriate errors.

---

## How to Run

This project is designed for demonstration purposes and requires Java installed on your machine. Follow these steps:

1. Clone this repository:
   ```bash
   git clone https://github.com/Fegue3/Hunting-and-Fishing-Store.git
   ```
2. Navigate to the project directory.
3. Compile the Java files:
   ```bash
   javac *.java
   ```
4. Run the main program:
   ```bash
   java Main
   ```

---

## Future Improvements

- **Enhanced Reporting**: Add graphical reports or detailed insights.
- **Improved Data Storage**: Transition to a lightweight database like SQLite for better scalability.
- **Frontend Integration**: Develop a simple GUI or web interface for better usability.
- **Authentication Expansion**: Implement roles and permissions (e.g., admin vs. regular user).

---

## Contact

For more information or inquiries, please contact:

- **Author**: Francisco
- **Email**: fp226766@gmail.com
- **GitHub**: [Fegue3](https://github.com/Fegue3)

---

Thank you for checking out the **Hunting and Fishing Store** project!

