# WildTrail — Viva-friendly Java simplification

## Scope
The **13 Java files** in `backend/src/main/java/com/wildtrail/modules/resources/` were simplified for readability. All other project files remain as provided.

## What stays the same
- Spring Boot architecture and existing API paths / method names.
- Guide CRUD using `resources.txt` and atomic file replacement.
- Vehicle CRUD using JDBC and the existing SQL tables.
- Admin-only edit endpoints, public owner-data filtering, guide booking conflict checks and vehicle booking safeguards.
- Inheritance and polymorphism demonstrated by the Guide and Vehicle subclasses.

## Where to explain code at the viva
- `Guide.java`, `NaturalistGuide.java`: encapsulation, inheritance, method overriding.
- `FileGuideRepository.java`: create, read, update and delete using a text file.
- `GuideController.java`: REST methods and assigning guides to bookings.
- `Vehicle.java`, `SafariJeep.java`, `FamilySafariJeep.java`, `HiluxSafariJeep.java`, `LuxuryJeep.java`: abstraction, inheritance, polymorphism.
- `JdbcVehicleRepository.java`: SQL INSERT, SELECT, UPDATE and DELETE via JDBC.
- `VehicleService.java`: input validation and booking rules.
- `VehicleController.java`: HTTP endpoints and admin authorization.
- `TeamController.java`: public team listing without private guide information.

## Verification limits
The 13 Java source files were checked with the Java compiler parser (syntax only). The full Maven build and database-backed integration tests **could not be run** in this environment because Maven dependencies were unavailable for download. **Before submission or viva, run `mvnw.cmd test` in the backend directory on your Windows laptop and test the UI CRUD flows with your database.**

## Important
This is a readability-focused refactor, not a rewrite into plain Java without Spring Boot. Keep a backup of your original project.
