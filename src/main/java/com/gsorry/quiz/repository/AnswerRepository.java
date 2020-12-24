package com.gsorry.quiz.repository;

import com.gsorry.quiz.domain.Answer;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
}
