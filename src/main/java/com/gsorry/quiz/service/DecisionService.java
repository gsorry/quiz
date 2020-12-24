package com.gsorry.quiz.service;

import com.gsorry.quiz.domain.Answer;
import com.gsorry.quiz.domain.Decision;
import com.gsorry.quiz.domain.Question;
import com.gsorry.quiz.domain.User;
import com.gsorry.quiz.repository.DecisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DecisionService {

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    @Autowired
    DecisionRepository decisionRepository;

    public Decision findOrNew(String page) {
        User user = userService.getCurrent();
        Question question = questionService.findNext(page).getContent().get(0);
        Decision decision = new Decision();
        decision.setUser(user);
        for (Answer answer : question.getAnswers()) {
            Optional<Decision> optional_decision = decisionRepository.findByUserAndAnswer(user, answer);
            if (optional_decision.isPresent()) {
                decision = optional_decision.get();
            }
        }
        return decision;
    }

    public void save(Decision decision) {
        User user = userService.getCurrent();
        decision.setUser(user);
        decisionRepository.save(decision);
    }
}
