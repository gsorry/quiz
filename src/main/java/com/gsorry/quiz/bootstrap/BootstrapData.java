package com.gsorry.quiz.bootstrap;

import com.gsorry.quiz.domain.Answer;
import com.gsorry.quiz.domain.Decision;
import com.gsorry.quiz.domain.Question;
import com.gsorry.quiz.domain.User;
import com.gsorry.quiz.repository.AnswerRepository;
import com.gsorry.quiz.repository.DecisionRepository;
import com.gsorry.quiz.repository.QuestionRepository;
import com.gsorry.quiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BootstrapData implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    DecisionRepository decisionRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Create Admin User");

        User admin = new User();
        admin.setFirstName("Admin");
        admin.setLastName("Admin");
        admin.setEmail("admin@example.com");
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setLastLogin(new Date());
        admin.setAdmin(true);
        userRepository.save(admin);

        System.out.println("Create Users");

        User user1 = new User();
        user1.setFirstName("User 1");
        user1.setLastName("User 1");
        user1.setEmail("user1@example.com");
        user1.setUsername("user1");
        user1.setPassword("user1");
        user1.setLastLogin(new Date());
        user1.setAdmin(false);
        userRepository.save(user1);

        User user2 = new User();
        user2.setFirstName("User 2");
        user2.setLastName("User 2");
        user2.setEmail("user2@example.com");
        user2.setUsername("user2");
        user2.setPassword("user2");
        user2.setLastLogin(new Date());
        user2.setAdmin(false);
        userRepository.save(user2);

        User user3 = new User();
        user3.setFirstName("User 3");
        user3.setLastName("User 3");
        user3.setEmail("user3@example.com");
        user3.setUsername("user3");
        user3.setPassword("user3");
        user3.setLastLogin(new Date());
        user3.setAdmin(false);
        userRepository.save(user3);

        User user4 = new User();
        user4.setFirstName("User 4");
        user4.setLastName("User 4");
        user4.setEmail("user4@example.com");
        user4.setUsername("user4");
        user4.setPassword("user4");
        user4.setLastLogin(new Date());
        user4.setAdmin(false);
        userRepository.save(user4);

        User user5 = new User();
        user5.setFirstName("User 5");
        user5.setLastName("User 5");
        user5.setEmail("user5@example.com");
        user5.setUsername("user5");
        user5.setPassword("user5");
        user5.setLastLogin(new Date());
        user5.setAdmin(false);
        userRepository.save(user5);

        User user6 = new User();
        user6.setFirstName("User 6");
        user6.setLastName("User 6");
        user6.setEmail("user6@example.com");
        user6.setUsername("user6");
        user6.setPassword("user6");
        user6.setLastLogin(new Date());
        user6.setAdmin(false);
        userRepository.save(user6);

        System.out.println("Number of Users: " + userRepository.count());

        System.out.println("Create Questions and Answers");

        Question question1 = new Question("Question 1?");
        questionRepository.save(question1);

        Answer answer1 = new Answer("Answer 1", false, question1);
        answerRepository.save(answer1);
        Answer answer2 = new Answer("Answer 2", false, question1);
        answerRepository.save(answer2);
        Answer answer3 = new Answer("Answer 3", true, question1);
        answerRepository.save(answer3);

        Question question2 = new Question("Question 2?");
        questionRepository.save(question2);

        Answer answer4 = new Answer("Answer 4", false, question2);
        answerRepository.save(answer4);
        Answer answer5 = new Answer("Answer 5", false, question2);
        answerRepository.save(answer5);
        Answer answer6 = new Answer("Answer 6", true, question2);
        answerRepository.save(answer6);

        System.out.println("Number of Questions: " + questionRepository.count());
        System.out.println("Number of Answers: " + answerRepository.count());

        System.out.println("Create Decisions");

        Decision decision1 = new Decision(user1, answer3);
        decisionRepository.save(decision1);
        Decision decision2 = new Decision(user1, answer6);
        decisionRepository.save(decision2);

        Decision decision3 = new Decision(user2, answer3);
        decisionRepository.save(decision3);
        Decision decision4 = new Decision(user2, answer5);
        decisionRepository.save(decision4);

        Decision decision5 = new Decision(user3, answer2);
        decisionRepository.save(decision5);
        Decision decision6 = new Decision(user3, answer5);
        decisionRepository.save(decision6);

        Decision decision7 = new Decision(user4, answer3);
        decisionRepository.save(decision7);

        Decision decision8 = new Decision(user5, answer5);
        decisionRepository.save(decision8);

        System.out.println("Number of Decisions: " + decisionRepository.count());
    }
}
