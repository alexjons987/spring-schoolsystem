# Group Assignment: School System API
This group assignment is split into different parts (days) where we got different requirements each day.

# Day 1 requirements
### Purpose
You will work in a group to build a REST-based School System API with Spring Boot.

The system should be able to handle students, courses, and enrollments.

All data should be stored in memory (List/Map) — no database should be used.

The focus is on REST conventions, data transfer (DTO), error handling, validation, and logical business management in multiple layers (Controller → Service → Repository).

### Structure
The project should have these packages:
* controller
* model
  * dto
* service
* repository
* exception

### Part 1 – Domain Models (without a database)
You will create three model classes:
1. **Student**
* id
* name
* age
* email
2. **Course**
* id
* title
* teacher
* maxStudents
* list of registered students
3. **Enrollment**
* studentId
* courseId
* date

### Part 2 – DTOs and Validation
Create DTO classes for Student and Course with appropriate validation rules.

Use annotations that specify that name cannot be empty, age must be at least a certain value, email must be in the correct format, and the maximum number of students must be greater than zero.

### Part 3 – “Repository” (in memory)
Since no database will be used, each repository should manage its data in a list or map in memory.

Each repository should be able to save, retrieve, update, and delete objects.

### Part 4 – Logic (Service Layer)
In the service layer, you will implement the actual business logic.

There should be methods to:
* **C**reate, **R**ead, **U**pdate and **D**elete students
* Retrieve students with filters (e.g., age range or name)
* Create and retrieve courses
* Register a student for a course
* Retrieve all students who are registered in a particular course

The service layer should also contain controls to prevent incorrect situations, for example, adding the same student multiple times to the same course, or adding more students than the course's maximum limit.

### Part 5 – Controllers
You should create controllers that follow REST conventions with the correct endpoints and HTTP methods.

Example functionality:
* Get all students or filter using RequestParam
* Create new student
* Delete student
* Create course
* Register a student to a course via PathVariable or RequestParam
* List all students in a specific course

All methods should return correct HTTP status codes, such as `200`, `201`, `400`, `404` or `409` depending on the result.

### Part 6 – Error handling
Implement a global error handler that catches common error types, such as:
* Validation errors with invalid data
* Resources not found
* Illogical states (e.g. full course or double registration)

Error messages should be returned as JSON and provide clear information to the client about what went wrong.

### Part 7 – Business Logic
The business logic should include controls that:
* Ensure that a student cannot be registered twice for the same course
* Stop registration when the course is full
* Validate that the course and student exist before registration
* Make it possible to list which students are taking a particular course

#### Testing
Test your API with **Postman** or **CURL**.

Example of flows to test:
1. Create some students
2. Create some courses
3. Register students for courses
4. Try to register a student for a full course
5. Try to register the same student twice
6. Get all students in a course

The API should handle both successful and unsuccessful requests in a clear and consistent way.

### Extra tasks
1. Add a grading system for each student in each course.
2. Add an endpoint to update grades.
3. Add validation so that grades can only be between 0 and 100.
4. Implement a search function on student names.
5. Create a statistics endpoint that shows the total number of courses, students, and average age.

# Day 2 requirements
### Database connection
In the next step, you will extend your API to connect to a real database.

The aim is to move from a memory-based system to a system that stores data permanently.

### New requirements:
* Use a **database manager MySQL**.
* All model classes should be converted to **JPA entities** with relevant annotations.
* The repository classes should now inherit from **JpaRepository** or **CrudRepository**.
* The database connection should be configured in `application.properties` or `application.yml`.
* When you start the application, the data should be saved and can be read back from the database.

### Extra challenges:
* Add the ability to update or delete data via the API.
* Test with MySQL.
* Display the data with SQL queries in the database to verify that it was saved correctly.

# Day 3 requirements
### Relations
#### New requirements:
1. Create relationships between the entities:
* **Student ↔ Enrollment ↔ Course**
* A student can be registered in multiple courses.
* A course can have multiple students.
* `Enrollment` functions as a connection table (Many-to-Many relationship).

#### Technical requirements:
* All entities (`Student`, `Course`, `Enrollment`) should be correctly annotated with JPA relationships:
  * `@OneToMany`, `@ManyToOne`, `@ManyToMany` where appropriate.
  * `Enrollment` should have relationships to both `Student` and `Course`.
  * When a student is registered for a course, a new **Enrollment** entry is created in the database.

#### Additional challenges:
* Add **grades** to `Enrollment` and make it possible to update grades via the API.
* Add **cascade rules** so that the correct data is deleted or saved automatically when changes occur.
* Test the relationships by retrieving all courses a student is taking or all students in a course.
* Show understanding of the difference between **EAGER** and **LAZY** loading.

# Day 4 requirements (OPTIONAL)
You should create at least three of your own JPQL queries in your repository classes.
Which ones you choose is up to you

### StudentRepository
Create queries that make it possible to:
* Retrieve all students whose names contain a certain text (case-insensitive).
* Retrieve all students older than a certain age value.
* Count how many students are registered in the system.

* Retrieve all courses taught by a certain teacher (teacher).
* Retrieve all courses that still have vacancies (fewer participants than maxStudents).
* Retrieve all courses where at least one student has a grade above 90.

* Retrieve all enrollments with grades above a certain value.
* Retrieve all enrollments that were created after a certain date.
* Retrieve the number of students registered in each course (grouping).
