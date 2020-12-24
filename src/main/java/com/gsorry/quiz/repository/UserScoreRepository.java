package com.gsorry.quiz.repository;

import com.gsorry.quiz.domain.User;
import com.gsorry.quiz.domain.UserScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserScoreRepository extends JpaRepository<User, Long> {

    @Query(value = "" +
            "SELECT\n" +
            "    u.id,\n" +
            "    u.first_name as firstName,\n" +
            "    u.last_name as lastName,\n" +
            "    u.username,\n" +
            "    u.last_login as lastLogin,\n" +
            "    COUNT(CASE WHEN a.correct=true THEN 1 END) AS score\n" +
            "FROM user u\n" +
            "LEFT JOIN decision d on d.user_id = u.id\n" +
            "LEFT JOIN answer a on d.answer_id = a.id\n" +
            "WHERE NOT u.admin\n" +
            "GROUP BY u.id\n" +
            "ORDER BY score DESC", nativeQuery = true)
    List<UserScore> findAllWithScore();

    @Query(value = "" +
            "WITH user_score AS (\n" +
            "    SELECT\n" +
            "        u.id,\n" +
            "        u.first_name as firstName,\n" +
            "        u.last_name as lastName,\n" +
            "        u.username,\n" +
            "        u.last_login as lastLogin,\n" +
            "        COUNT(CASE WHEN a.correct=true THEN 1 END) AS score,\n" +
            "        COUNT(CASE WHEN a.correct=false THEN 1 END) AS penalty\n" +
            "    FROM user u\n" +
            "    LEFT JOIN decision d on d.user_id = u.id\n" +
            "    LEFT JOIN answer a on d.answer_id = a.id\n" +
            "    WHERE NOT u.admin\n" +
            "    GROUP BY u.id\n" +
            "    ORDER BY score DESC\n" +
            ")\n" +
            "SELECT\n" +
            "    id,\n" +
            "    firstName,\n" +
            "    lastName,\n" +
            "    username,\n" +
            "    lastLogin,\n" +
            "    score\n" +
            "FROM user_score\n" +
            "WHERE score + penalty = (SELECT COUNT(*) FROM question)", nativeQuery = true)
    List<UserScore> findAllAnsweredWithScore();

    @Query(value = "" +
            "WITH user_score AS (\n" +
            "    SELECT\n" +
            "        u.id,\n" +
            "        u.first_name as firstName,\n" +
            "        u.last_name as lastName,\n" +
            "        u.username,\n" +
            "        u.last_login as lastLogin,\n" +
            "        COUNT(CASE WHEN a.correct=true THEN 1 END) AS score\n" +
            "    FROM user u\n" +
            "    LEFT JOIN decision d on d.user_id = u.id\n" +
            "    LEFT JOIN answer a on d.answer_id = a.id\n" +
            "    WHERE NOT u.admin AND a.correct\n" +
            "    GROUP BY u.id\n" +
            "    ORDER BY score DESC\n" +
            ")\n" +
            "SELECT *\n" +
            "FROM user_score\n" +
            "WHERE score = (SELECT COUNT(*) FROM question)", nativeQuery = true)
    List<UserScore> findAllCorrectWithScore();

    @Query(value = "" +
            "SELECT\n" +
            "    u.id,\n" +
            "    u.first_name as firstName,\n" +
            "    u.last_name as lastName,\n" +
            "    u.username,\n" +
            "    u.last_login as lastLogin,\n" +
            "    COUNT(CASE WHEN a.correct=true THEN 1 END) AS score\n" +
            "FROM user u\n" +
            "LEFT JOIN decision d on d.user_id = u.id\n" +
            "LEFT JOIN answer a on d.answer_id = a.id\n" +
            "WHERE NOT u.admin\n" +
            "GROUP BY u.id\n" +
            "ORDER BY score DESC\n" +
            "LIMIT 5", nativeQuery = true)
    List<UserScore> findTopFive();
}
