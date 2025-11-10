# Group Assignment: School System API
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
## Part 1 – Domain Models (without a database)
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

## Part 2 – DTOs and Validation
Create DTO classes for Student and Course with appropriate validation rules.

Use annotations that specify that name cannot be empty, age must be at least a certain value, email must be in the correct format, and the maximum number of students must be greater than zero.

## Part 3 – “Repository” (in memory)
Since no database will be used, each repository should manage its data in a list or map in memory.

Each repository should be able to save, retrieve, update, and delete objects.

## Part 4 – Logic (Service Layer)
In the service layer, you will implement the actual business logic.

There should be methods to:
* **C**reate, **R**ead, **U**pdate and **D**elete students
* Retrieve students with filters (e.g., age range or name)
* Create and retrieve courses
* Register a student for a course
* Retrieve all students who are registered in a particular course

The service layer should also contain controls to prevent incorrect situations, for example, adding the same student multiple times to the same course, or adding more students than the course's maximum limit.

## Part 5 – Controllers
You should create controllers that follow REST conventions with the correct endpoints and HTTP methods.

Example functionality:
* Get all students or filter using RequestParam
* Create new student
* Delete student
* Create course
* Register a student to a course via PathVariable or RequestParam
* List all students in a specific course

All methods should return correct HTTP status codes, such as `200`, `201`, `400`, `404` or `409` depending on the result.

## Part 6 – Error handling
Implement a global error handler that catches common error types, such as:
* Validation errors with invalid data
* Resources not found
* Illogical states (e.g. full course or double registration)

Error messages should be returned as JSON and provide clear information to the client about what went wrong.

## Part 7 – Business Logic
The business logic should include controls that:
* Ensure that a student cannot be registered twice for the same course
* Stop registration when the course is full
* Validate that the course and student exist before registration
* Make it possible to list which students are taking a particular course
Testing
Test your API with Postman or curl.
Example of flows to test:
1. Create some students
2. Create some courses
3. Register students for courses
4. Try to register a student for a full course
5. Try to register the same student twice
6. Get all students in a course
The API should handle both successful and unsuccessful requests in a clear and
consistent way.
Extra tasks
1. Add a grading system for each student in each course.
2. Add an endpoint to update grades.
3. Add validation so that grades can only be between 0 and 100.
4. Implement a search function on student names.
5. Create a statistics endpoint that shows the total number of courses, students, and average age.