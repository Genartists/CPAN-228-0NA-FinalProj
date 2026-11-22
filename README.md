# HumberGear

A Tech & Gaming Hardware Warehouse management project.

## Tech Stack

- **Backend:** Java 21, Spring Boot
- **Database:** H2 Database
- **Frontend:** Thymeleaf, Bootstrap 5.3, Bootstrap Icons, Custom CSS

---

## How to Run

1. Open terminal in the project root directory:

   ```bash
   mvn spring-boot:run
   ```

2. Open web browser with domains:
   - **Home / Dashboard:** [http://localhost:8080](http://localhost:8080)
   - **Hardware Catalog:** [http://localhost:8080/inventory](http://localhost:8080/inventory)
   - **Register Hardware:** [http://localhost:8080/inventory/new](http://localhost:8080/inventory/new)
   - **Distribution Depots:** [http://localhost:8080/depots](http://localhost:8080/depots)
   - **About Us:** [http://localhost:8080/about](http://localhost:8080/about)

---

## Database Access (H2 Console)

Inspect the database tables and run SQL queries while the application is running:

- **Console URL:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- **JDBC URL:** `jdbc:h2:mem:humbergeardb`
- **Username:** `sa`
- **Password:** _(leave blank)_
