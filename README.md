# Hunting and Fishing Store

Welcome to the **Hunting and Fishing Store** repository! This project is a backend system for managing a specialized store for hunting and fishing equipment. The application showcases robust Object-Oriented Programming (OOP) concepts in Java, handling products, categories, customers, sales, and stock management effectively.

---

## Table of Contents

1. [Features](#features)
2. [Technologies Used](#technologies-used)
3. [Setup Instructions](#setup-instructions)
4. [Code Examples](#code-examples)
5. [Program Outputs](#program-outputs)
6. [Future Improvements](#future-improvements)
7. [Contact](#contact)

---

## Features

### Product Management
- Create, update, and delete products in inventory.
- Categorize products into distinct groups:
  - **Hunting Gear**: Weapons, bows, and related equipment.
  - **Fishing Supplies**: Bait, rods, and accessories.
  - **Clothing**: Specialized attire for outdoor activities.

### Customer Management
- Register and manage customers.
- User-friendly authentication system with admin privileges.

### Sales and Stock Management
- View stock availability in real-time.
- Add items to a cart and finalize purchases.
- Generate invoices automatically after each sale.
- Manage inventory updates and stock levels efficiently.

### File-Based Data Persistence
- Persist data locally using Java serialization to files like `clientes.dat`, `stock.dat`, and `vendas.dat`.

---

## Technologies Used

- **Programming Language**: Java
- **Data Storage**: File-based using serialization
- **Development Paradigm**: Object-Oriented Programming (OOP)

---

## Setup Instructions

To run the project locally, follow these steps:

1. Clone the repository:
   ```bash
   git clone https://github.com/Fegue3/Hunting-and-Fishing-Store.git
   ```

2. Navigate to the project directory:
   ```bash
   cd Hunting-and-Fishing-Store
   ```

3. Compile the Java files:
   ```bash
   javac *.java
   ```

4. Run the main program:
   ```bash
   java Loja
   ```

---

## Code Examples

### Registering a New Customer
Below is a sample code snippet for registering a new customer:

```java
public static void registar() {
        try {
            System.out.print("Nome: ");
            String nome = myinputs.Ler.umaString();

            System.out.print("Contacto: ");
            long contacto = myinputs.Ler.umLong();

            System.out.print("NIF: ");
            int NIF = myinputs.Ler.umInt();
            
            if (NIF < 100000000 || NIF > 999999999) { 
                throw new LojaException("O NIF deve ter 9 dígitos.");
            }
            
            Cliente novoCliente = new Cliente(nome, contacto, NIF);
            adicionarCliente(novoCliente); 
            System.out.println("Cliente registrado com sucesso!");
        } catch (LojaException e) {
            System.out.println("Erro ao registrar cliente: " + e.getMessage());
        }
    }
```

### Viewing Stock
Example of displaying all stock items:

```java
public void salvarStock() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(this);
        } catch (IOException e) {
            throw new LojaException("Erro ao salvar o stock.", e);
        }
    }
```
Sample Output:
```
Produto 1:
  Nome: Fishing Rod
  Descrição: High-quality carbon rod
  Preço: 120.0
  Categoria: Pesca
  Ativo: true
  Quantidade: 15
```

### Invoice Generation
Generate and print a detailed invoice after a sale:

```java
public String gerarFatura() {
        StringBuilder fatura = new StringBuilder();
        fatura.append("=== Fatura ===\n");
        fatura.append("ID da Venda: ").append(idVenda).append("\n");
        fatura.append("Data: ").append(dataVenda).append("\n\n");
        fatura.append("Cliente: ").append(cliente.getNome()).append("\n\n");
        fatura.append("Produtos:\n");

        double total = 0.0;
        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            int quantidade = quantidades.get(i);
            double subtotal = produto.getPreco() * quantidade;
            total += subtotal;
            fatura.append(String.format("- %s (x%d): %.2f\n", produto.getNome(), quantidade, subtotal));
        }

        fatura.append("\nTotal: ").append(String.format("%.2f", total)).append("\n");
        return fatura.toString();
    }

```

---

## Program Outputs

### CLI Welcome Screen

```
------------ Bem vindo à loja de Caça e Pesca! --------------

Menu:
1. Registar Cliente
2. Login
3. Sair
Escolha uma opção:
```
### CLI User Menu
```
--------- Menu Cliente ---------
1: Ver produtos de Caça
2: Ver produtos de Pesca
3: Ver produtos de Roupa
4: Ver Carrinho de compras
5: Remover conta
6: Adicionar ao Carrinho
7: Finalizar compra
8: Voltar ao Menu Inicial
9: Sair
Escolha uma opção: 
```

### CLI Admin Menu

```
-------- Menu Admins ----------

------- Gestão de Clientes -------
1: Mostrar todos clientes
2: Limpar Clientes
3: Remover Utilizador
4: Voltar ao Menu Inicial
5: Sair
------- Gestão de stock -------
6: Adicionar produto de Caça ao Stock
7: Adicionar produto de Pesca ao Stock
8: Adicionar Roupas ao Stock
9: Atualizar quantidade de um produto no Stock
10: Aplicar desconto em um produto
11: Remover produto do Stock
12: Mostrar Stock
13:Limpar Stock
------- Gestão de vendas -------
14:Ver Todas as Vendas
15:Limpar todas as Vendas
16:Estatisticas da Loja
Escolha uma opção: 
```

### Example of Stock Display

```
Estado atual do stock:
Produto 1:
  Nome: Camuflagem
  Descrição: Roupa resistente para caça
  Preço: 75.00
  Categoria: Roupa
  Ativo: true
  Quantidade: 10
```

### Example of Invoice Generation

```
=== Fatura ===
ID da Venda: 12345-abcde
Data: 2025-01-15T10:00:00

Cliente: João Silva

Produtos:
- Fishing Rod (x1): 120.00
- Bait Pack (x2): 30.00

Total: 180.00
```

---

## Future Improvements

1. **Enhanced User Interface**: Develop a GUI or integrate a web-based frontend.
2. **Database Integration**: Transition from file-based storage to a database like SQLite or MySQL for scalability.
3. **Reporting Features**: Add graphical sales reports and analytics.
4. **Authentication Enhancements**: Implement user roles and advanced security measures.

---

## Contact

For more details or inquiries:

- **Author**: Sofia
- **Email**: [sofiacosta2004@hotmail.com](mailto:sofiacosta2004@hotmail.com)
- **GitHub**: [SofiaAlCosta]([https://github.com/SofiaAlCosta])

---

Thank you for exploring the **Hunting and Fishing Store** project! Your feedback is highly valued.

This project serves to demonstrate my basic backend skills and understanding of foundational programming concepts.

