# Quiz application

## Getting Started

### Requirements

Development Environment:
- Java JDK 11 or higher installed
- Maven 3.6.3 or higher installed
- Git
- Docker (optional) or MySQL Server (Optional)

Verify Your Environment (Linux):
```shell
java -version
javac -version
mvn -v
git --version
docker -v
```

Application uses in-memory H2 Database by default.

### Setup MySQL Server using Docker (Optional)

Edit `application.properties`:
```properties
spring.jpa.database=mysql
spring.jpa.hibernate.ddl-auto=create
spring.datasource.url=jdbc:mysql://172.17.0.2:3306/quiz
spring.datasource.username=root
spring.datasource.password=root
```

Start MySQL using Docker:
```shell
docker run --detach --name=test-mysql --env="MYSQL_ROOT_PASSWORD=root" -p 3306:3306 mysql
```

Connecto to database server:
```shell
mysql -u root -p -h 172.17.0.2
```

Create database `quiz`:
```shell
CREATE DATABASE quiz;
```

### Run Application

Clone repository:
```shell
git clone repo
```

Run application:
```shell
mvn clean install
mvn spring-boot:run
```

Optional run application with java -jar command:
```shell
java -jar target/quiz-0.0.1-SNAPSHOT.jar
```

## Guides

When starting the application, an admin user is created.
Ordinary users are created during registration.
When the user opens the application, he/she can choose two options:
- Login
- Register
Depending on the role, users can see different information and perform different commands.

### Administrator

Login using `admin/admin` credentials.

Homepage links are changed:
- Logout - logout from application.
- Users - View registred users.
- Questions - View and manage questions and answers

Users Page:
- Home - Back to homepage.
- All Users - View all registred users with score
- All Answered Questions - View users who answered all questions
- All Correct Answers - View users who answered all questions correctly

Questions Page:
- Home - Back to homepage.
- All Question - View ans manage all questions.
- Top Correct - View all question ordered by score, most coorect answers on top.
- Top Incorrect - View all question ordered by score, most incorrect answers on top.
- New Question - Create new Question, opens Question Form.
- Edit - View and update question and manage related answers, opens Question Form.
- Delete - Delete Question and related answers.

Question Form:
    - Questions - Back to questions page.
    - New Answer - Create new Answer, opens Answer Form.
    - Edit - Edit Answer, opens Answer Form.
    - Delete - Delete Answer.

Answer Form:
    - Question - Return to related Question.

### User

Login with credentials used in registration form.

- Registruje se na kviz popunjavanjem osnovnih podataka (korisničko ime, lozinka, ime i prezime, e-mail), ukoliko je registrovan može pristupiti unošenjem korisničkog imena i lozinke.
- Odgovara na pitanja biranjem jednog ili više ponuđenih odgovora. Ukoliko ne zna odgovor na pitanje, može ga preskočiti.
- Učesnik može da odustane od kviza.
- Učesniku je prikazan indikator na koje po redu odgovara pitanje (primer 2/6).
- Na kraju kviza prikazati učesniku prikazati rang listu od top 5 učesnika kviza, ukoliko je trenutni učesnik na prvom mestu, prikazati mu poruku čestitke.

Homepage links are changed:
- Logout - logout from application.
- Questionnaire - View Questions and Answers and make Decisions.

Questionnaire Form:
- Home - Back to homepage.
- Prev - Display previous Question.
- Next - Submit your Decition and go to next Question.
- Finish - Submit your Decition and display results page.
