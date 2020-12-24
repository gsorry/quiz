package com.gsorry.quiz.service;

import com.gsorry.quiz.domain.User;
import com.gsorry.quiz.domain.UserScore;
import com.gsorry.quiz.repository.UserRepository;
import com.gsorry.quiz.repository.UserScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserScoreRepository userScoreRepository;

    public User getCurrent() {
        return this.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Not Found: " + username));
    }

    public Iterable<UserScore> finAll() {
        return userScoreRepository.findAllWithScore();
    }

    public Object finAllAnswered() {
        return userScoreRepository.findAllAnsweredWithScore();
    }

    public Object finAllCorrect() {
        return userScoreRepository.findAllCorrectWithScore();
    }

    public Iterable<UserScore> findTopFive() {
        return userScoreRepository.findTopFive();
    }

    public void registerUser(User user) {
        user.setAdmin(false);
        user.setLastLogin(new Date());
        userRepository.save(user);
    }
}
