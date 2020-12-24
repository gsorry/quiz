package com.gsorry.quiz.service;

import com.gsorry.quiz.domain.Question;
import com.gsorry.quiz.domain.QuestionScore;
import com.gsorry.quiz.repository.QuestionRepository;
import com.gsorry.quiz.repository.QuestionScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionScoreRepository questionScoreRepository;

    public Question findById(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid question Id:" + id));
    }

    public Iterable<Question> findAll() {
        return questionRepository.findAll();
    }

    public Iterable<QuestionScore> findTopCorrect() {
        return questionScoreRepository.findTopCorrect();
    }

    public Iterable<QuestionScore> findTopIncorrect() {
        return questionScoreRepository.findTopIncorrect();
    }

    public void save(Question question) {
        questionRepository.save(question);
    }

    public void delete(Question question) {
        questionRepository.delete(question);
    }

    public Page<Question> findNext(String page) {
        Pageable firstPageWithOneElement = PageRequest.of(Integer.parseInt(page), 1, Sort.by("id").ascending());
        return questionRepository.findAll(firstPageWithOneElement);
    }

    public boolean isLast(String page) {
        Page<Question> question = this.findNext(page);
        return question.isLast();
    }
}
