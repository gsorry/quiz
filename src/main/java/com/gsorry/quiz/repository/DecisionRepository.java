package com.gsorry.quiz.repository;

import com.gsorry.quiz.domain.Answer;
import com.gsorry.quiz.domain.Decision;
import com.gsorry.quiz.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DecisionRepository extends CrudRepository<Decision, Long> {

    Optional<Decision> findByUserAndAnswer(User user, Answer answer);
}
