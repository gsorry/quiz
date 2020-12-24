package com.gsorry.quiz.repository;

import com.gsorry.quiz.domain.Question;
import com.gsorry.quiz.domain.QuestionScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionScoreRepository extends JpaRepository<Question, Long> {

    @Query(value = "" +
            "SELECT\n" +
            "    q.id AS id,\n" +
            "    q.content AS question,\n" +
            "    COUNT(CASE WHEN a.correct=true THEN 1 END) AS score\n" +
            "FROM question q\n" +
            "LEFT JOIN answer a on q.id = a.question_id\n" +
            "LEFT JOIN decision d on a.id = d.answer_id\n" +
            "GROUP BY q.id\n" +
            "ORDER BY score DESC", nativeQuery = true)
    List<QuestionScore> findAllWithScore();

    @Query(value = "" +
            "SELECT\n" +
            "    q.id AS id,\n" +
            "    q.content AS question,\n" +
            "    COUNT(CASE WHEN a.correct=true THEN 1 END) AS score\n" +
            "FROM question q\n" +
            "LEFT JOIN answer a on q.id = a.question_id\n" +
            "LEFT JOIN decision d on a.id = d.answer_id\n" +
            "GROUP BY q.id\n" +
            "ORDER BY score DESC", nativeQuery = true)
    List<QuestionScore> findTopCorrect();

    @Query(value = "" +
            "SELECT\n" +
            "    q.id AS id,\n" +
            "    q.content AS question,\n" +
            "    a.content AS answer,\n" +
            "    COUNT(CASE WHEN a.correct=false THEN 1 END) AS score\n" +
            "FROM question q\n" +
            "LEFT JOIN answer a on q.id = a.question_id\n" +
            "LEFT JOIN decision d on a.id = d.answer_id\n" +
            "GROUP BY q.id, a.id\n" +
            "ORDER BY score DESC", nativeQuery = true)
    List<QuestionScore> findTopIncorrect();
}
