package com.gsorry.quiz.repository;

import com.gsorry.quiz.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
