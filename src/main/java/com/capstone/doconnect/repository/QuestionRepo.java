package com.capstone.doconnect.repository;

import com.capstone.doconnect.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {

    @Query("FROM Question WHERE isApproved = false")
    public List<Question> getUnapprovedQuestions();

    @Query("FROM Question WHERE isApproved = true")
    public List<Question> getApprovedQuestions();

    @Query("FROM Question WHERE id = ?1 AND isApproved = true")
    public Question getApprovedQuestionById(Long Id);

    @Query("FROM Question WHERE topic = ?1 AND isApproved = true")
    public List<Question> getApprovedQuestionsByTopic(String topic);
}
