# 🚗 JDBC Dealership Project

This Java application allows users to search, add, remove, and purchase vehicles using a **MySQL database** instead of CSV files. It is a continuation of Workshop 5 and integrates the database logic from Workshop 7.

---

## 📌 Project Overview

- Fully database-connected car dealership application
- Supports **sales** and **lease** contracts
- Admin panel to view all contracts
- Vehicle search by price, make/model, year, color, mileage, and type
- Clean UI with clear menu prompts

---

## 🛠 Features

- ✅ View all vehicles from database
- ✅ Search vehicles by multiple filters
- ✅ Add / remove vehicles (CRUD)
- ✅ Create sales and lease contracts
- ✅ Admin interface to view contract records
- ✅ Robust DAO layer (`VehicleDao`, `SalesDao`, `LeaseDao`)

---

## 💡 Interesting Code Snippet

```java
System.out.print("Is this a (1) Sale or (2) Lease? ");
String type = scanner.nextLine();

 if (type.equals("1")) {
                boolean financed = scanner.nextLine().equalsIgnoreCase("yes");

                SalesContract salesContract = new SalesContract(name, email, vehicle, LocalDate.now().toString(), vehicle.getPrice(), financed);
                SalesDao salesDao = new SalesDao(connection);
                salesDao.insertSalesContract(salesContract);
 } else if (type.equals("2")) {
                LocalDate endDate = LocalDate.parse(scanner.nextLine());
                double monthlyPayment = Double.parseDouble(scanner.nextLine());

                LeaseContract leaseContract = new LeaseContract(name, email, vehicle, LocalDate.now().toString(), vehicle.getPrice(), endDate);
                LeaseDao leaseDao = new LeaseDao(connection);
                leaseDao.insertLeaseContract(leaseContract);
}
```
## 🔍 Why it's interesting
This logic clearly separates behavior based on contract type and routes to the correct DAO. It keeps the flow concise and readable while making the program flexible to handle both contract types without duplicating effort.
