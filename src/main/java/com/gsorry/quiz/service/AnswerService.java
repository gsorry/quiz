package com.gsorry.quiz.service;

import com.gsorry.quiz.domain.Answer;
import com.gsorry.quiz.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    public Answer findById(Long id) {
        return answerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid answer Id:" + id));
    }

    public void save(Answer answer) {
        answerRepository.save(answer);
    }

    public void delete(Answer answer) {
        answerRepository.delete(answer);
    }
}
