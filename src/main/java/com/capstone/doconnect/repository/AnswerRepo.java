package com.capstone.doconnect.repository;

import com.capstone.doconnect.dto.GetAnswerDetailsDTO;
import com.capstone.doconnect.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepo extends JpaRepository<Answer, Long> {

    @Query("FROM Answer WHERE isApproved = false")
    public List<Answer> getUnapprovedAnswers();

    @Query("FROM Answer a JOIN a.question q WHERE q.id = ?1 AND a.isApproved = true")
    public List<Answer> getApprovedAnswersForQuestionId(Long questionId);

    @Query("FROM Answer WHERE question.id = ?1 AND isApproved = true")
    public List<Answer> findAnswerByQuestionId(Long questionId);
}
