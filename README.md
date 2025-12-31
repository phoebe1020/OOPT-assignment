# Online Shopping System (OOPT-assignment)

A simple object-oriented Java implementation of an online shopping system used for an OOPT assignment. The project models users, products, shopping cart, orders, payments, and reviews.

## Project Structure
- [Admin.java](Admin.java) — admin user model and admin operations
- [Customer.java](Customer.java) — customer user model
- [User.java](User.java) — base user class
- [Product.java](Product.java) — product model
- [Review.java](Review.java) — product reviews
- [ShoppingCart.java](ShoppingCart.java) — cart and cart operations
- [Order.java](Order.java) — order model
- [OrderItem.java](OrderItem.java) — item within an order
- [Payment.java](Payment.java) — payment handling
- [ManageProduct.java](ManageProduct.java) — product management utilities
- [ManageCustomer.java](ManageCustomer.java) — customer management utilities
- [ManageOrder.java](ManageOrder.java) — order management utilities
- [OnlineShoppingSystem.java](OnlineShoppingSystem.java) — main entry point / CLI

## Requirements
- Java JDK 8 or later

## Build & Run
From the project root directory, compile all classes:

```bash
javac *.java
```

Run the main program:

```bash
java OnlineShoppingSystem
```

If the project uses packages, adjust compilation and run commands accordingly.

## Notes
- The code is small and single-directory; adjust classpath if you move files into packages.
- If you want, I can add example input/output, usage instructions for the CLI, or a simple test harness.

---

Created for OOPT assignment. Contact the repository owner for questions or improvements.
