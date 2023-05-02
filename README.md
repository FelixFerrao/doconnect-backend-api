# DoConnect Backend API

This Spring Boot application serves as the backend for the DoConnect application. It provides various API endpoints for registering users and admins, logging in and out, asking questions, posting answers, and managing unapproved questions and answers.

The API endpoints and their corresponding controllers are as follows:

## User Controller

This controller handles user-related API endpoints.

### Endpoints

- POST /user/register: Register a new user.
- POST /user/login: Log in a user.
- GET /user/logout/{userId}: Log out a user.
- POST /user/askQuestion: Ask a new question.
- POST /user/giveAnswer: Post an answer to a question.
- GET /user/searchQuestion/{question}: Search for a question.
- GET /user/getAnswerDetails/{questionId}: Get the details of all answers to a question.
- GET /user/getQuestionById/{question_id}: Get a question by ID.
- GET /user/getQuestions/{topic}: Get all approved questions by topic.

## Admin Controller

This controller handles admin-related API endpoints.

### Endpoints

- POST /admin/register: Register a new admin.
- GET /admin/login/{email}/{password}: Log in an admin.
- GET /admin/logout/{adminId}: Log out an admin.
- GET /admin/getUnApprovedQuestions: Get all unapproved questions.
- GET /admin/getUnApprovedAnswers: Get all unapproved answers.
- PUT /admin/approveQuestion/{questionId}: Approve a question.
- PUT /admin/approveAnswer/{answerId}: Approve an answer.
- DELETE /admin/deleteQuestion/{questionId}: Delete a question.
- DELETE /admin/deleteAnswer/{answerId}: Delete an answer.
- GET /admin/getUser/{email}: Get a user by email.
- GET /admin/getAllUsers: Get all users. (This API is only for the admin)

## Technologies Used

The backend API is built using the following technologies:

- Java Spring Boot: provides the framework for building the backend API.
- Maven: manages the project's dependencies.
- MySQL: stores the application data.

## Running the Application

To run the application, you will need to have Java and MySQL installed on your system. Follow these steps:

1. Clone the repository.
2. Create a MySQL database named "doconnectdb".
3. Modify the "application.properties" file to connect to your MySQL instance.
4. Build the application using Maven: `mvn clean install`.
5. Run the application: `mvn spring-boot:run`.

The application will start up on `http://localhost:9090`. You can now send requests to the API using your favorite HTTP client.