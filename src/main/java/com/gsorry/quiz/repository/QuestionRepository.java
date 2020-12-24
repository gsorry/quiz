package com.gsorry.quiz.repository;

import com.gsorry.quiz.domain.Question;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuestionRepository extends PagingAndSortingRepository<Question, Long> {
}
