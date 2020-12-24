package com.gsorry.quiz.domain;

import java.util.Date;

public interface UserScore {

    Long getId();
    String getFirstName();
    String getLastName();
    String getUsername();
    Date getLastLogin();
    Integer getScore();
}
