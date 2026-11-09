# WildTrail — Option A: All Java Files (Presentation-Friendly)

## Goal
Make the Java source easier to read and explain while preserving the exact application behavior.

## Scope
- All 84 Java files in `backend/src` (production classes plus tests).
- Builds on the previously simplified 13 Guide/Vehicle Java files.
- Original frontend source, SQL schema, environment settings, Maven dependencies and project structure retained.

## Changes applied
- Inserted short beginner-oriented class and method explanations.
- Expanded straightforward single-line `if` statements into clearly braced blocks.
- Expanded eligible one-statement methods over multiple lines.
- Split dense field assignments onto their own lines in two classes.

These transformations deliberately preserve class names, method signatures, URLs, HTTP responses, database tables/SQL, security rules, exception messages, business logic and data storage behavior.

**Important:** This is a conservative readability refactor. Complex core methods (such as booking transactions, access control, JDBC SQL and concurrency rules) are intentionally NOT rewritten into different algorithms. Doing so would risk changing behavior. It is not honest to claim that every algorithm has been converted to basic beginner Java or that no bug could occur.

## Simple backend flow (for viva)
1. **Controller** receives the HTTP request from the frontend.
2. **Service** validates business requirements and coordinates operations.
3. **Repository** executes SQL or reads/writes files.
4. **Model** holds data and implements OOP behavior.
5. **Security** checks login and permission requirements before protected actions.

## Validate locally before your demonstration
1. Open `WildTrail-Public-Source-main/backend` in IntelliJ IDEA.
2. Configure the database and settings exactly as for your original project.
3. On Windows run `mvnw.cmd test` from the backend folder.
4. Launch the backend and frontend using the project's original instructions.
5. Test create/list/update/delete for all modules, login, bookings, vehicle assignment, payments, review features and both user/admin UI.

If tests report failures, do NOT replace your working viva version until the cause is resolved.
