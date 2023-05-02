package com.capstone.doconnect.serviceImpl;

import com.capstone.doconnect.dto.AnswerDTO;
import com.capstone.doconnect.dto.GetAnswerDetailsDTO;
import com.capstone.doconnect.dto.QuestionDTO;
import com.capstone.doconnect.entities.Answer;
import com.capstone.doconnect.entities.Question;
import com.capstone.doconnect.entities.User;
import com.capstone.doconnect.exceptions.DuplicateRecord;
import com.capstone.doconnect.exceptions.NotFound;
import com.capstone.doconnect.repository.AnswerRepo;
import com.capstone.doconnect.repository.QuestionRepo;
import com.capstone.doconnect.repository.UserRepo;
import com.capstone.doconnect.sevices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AnswerRepo answerRepo;

    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EntityManager entityManager;

    @Override
    public User userLogin(String email, String password) {
        User user = userRepo.findByEmail(email);
        if(Objects.isNull(user))
            throw new NotFound();

        String userPassword = user.getPassword();
        if(passwordEncoder.matches(password, userPassword)) {
            user.setActive(true);
            userRepo.save(user);
        } else
            throw new NotFound();
        System.out.println(user);
        return user;
    }

    @Override
    public User userLogout(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new NotFound("User not found" + userId));
        user.setActive(false);
        userRepo.save(user);

        return user;
    }

    @Override
    public User userRegister(User user) {
        // Store the details of the new user
        User new_user = new User();

        // Check whether user exists
        User tempUser = userRepo.findByEmail(user.getEmail());
        if(Objects.isNull(tempUser)) {
            new_user.setUsername(user.getUsername());
            new_user.setEmail(user.getEmail());
            new_user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(new_user);

            return new_user;
        }
        throw new DuplicateRecord();
    }

    @Override
    public Question askQuestion(QuestionDTO questionDTO) {
        Question question = new Question();

        User user = userRepo.findById(questionDTO.getUser_id()).orElseThrow(() -> new NotFound("User not found"));
        question.setQuestion(questionDTO.getUser_question());
        question.setUser(user);
        question.setTopic(questionDTO.getTopic());
        questionRepo.save(question);
        return question;
    }

    @Override
    public Answer postAnswer(AnswerDTO postAnswerDTO) {
        Answer answer = new Answer();

        Question question = questionRepo.findById(postAnswerDTO.getQuestion_id()).orElseThrow(() -> new NotFound("Question not found"));
        User user = userRepo.findById(postAnswerDTO.getUser_id()).orElseThrow(() -> new NotFound("User not found"));
        answer.setAnswer(postAnswerDTO.getAnswer());
        answer.setUser(user);
        answer.setQuestion(question);
        answerRepo.save(answer);
        return answer;
    }

    @Override
    public List<Question> findQuestion(String question) {
        String query = "FROM Question WHERE (question LIKE :question) and isApproved = 1";
        return entityManager.createQuery(query, Question.class).setParameter("question", "%" + question + "%")
                .getResultList();
    }


    @Override
    public List<Answer> getAnswers(Long questionId) {
        return answerRepo.getApprovedAnswersForQuestionId(questionId);
    }

    @Override
    public List<Question> getQuestionsByTopic(String topic) {
        if(topic.equalsIgnoreCase("All")) {
            return questionRepo.getApprovedQuestions();
        }
        return questionRepo.getApprovedQuestionsByTopic(topic);
    }


    @Override
    public Question getQuestionById(Long question_id) {
        Question question = questionRepo.getApprovedQuestionById(question_id);
        return question;
    }

    @Override
    public List<GetAnswerDetailsDTO> findAnswerDetailByQuestionId(Long question_id) {
        String query = "SELECT a.id as answer_id, a.answer as answer, a.is_approved as isApproved, u.id as user_id, u.username as user_name, a.question_id as question_id "
                + "FROM answer a "
                + "JOIN user u ON a.user_id = u.id "
                + "WHERE a.question_id = :questionId";

        Query q = entityManager.createNativeQuery(query);
        q.setParameter("questionId", question_id);

        List<Object[]> answerDetailsList = q.getResultList();

        List<GetAnswerDetailsDTO> answerDetailsDTOList = new ArrayList<>();

        for(Object[] answerDetails : answerDetailsList) {
            GetAnswerDetailsDTO answerDetailsDTO = new GetAnswerDetailsDTO();

            BigInteger answer_id = (BigInteger) answerDetails[0];
            BigInteger user_id = (BigInteger) answerDetails[3];
            BigInteger questionId = (BigInteger) answerDetails[5];


            answerDetailsDTO.setAnswer_id(answer_id.longValue());
            answerDetailsDTO.setAnswer((String) answerDetails[1]);
            answerDetailsDTO.setApproved((boolean) answerDetails[2]);
            answerDetailsDTO.setUser_id(user_id.longValue());
            answerDetailsDTO.setUsername((String) answerDetails[4]);
            answerDetailsDTO.setQuestion_id(questionId.longValue());

            answerDetailsDTOList.add(answerDetailsDTO);
        }
        return answerDetailsDTOList;
    }

}
