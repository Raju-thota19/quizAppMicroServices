package com.quizservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizservice.dto.QuestionDTO;
import com.quizservice.dto.Response;
import com.quizservice.entity.Quiz;
import com.quizservice.repository.QuizInterface;
import com.quizservice.repository.QuizRepository;

@Service
public class QuizService {

	@Autowired
	QuizRepository quizRepo;

	@Autowired
	QuizInterface quizInterface;

	public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) {

		List<Integer> questionsWithIds = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
		Quiz quiz = new Quiz();
		quiz.setNumQ(numQ);
		quiz.setTitle(title);
		quiz.setQuestionIds(questionsWithIds);
		quizRepo.save(quiz);
		return new ResponseEntity<>("Quiz successfully created", HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionDTO>> getQuizQuestions(Integer id) {

		Optional<Quiz> quiz = quizRepo.findById(id);
		List<Integer> questions = quiz.get().getQuestionIds();
		ResponseEntity<List<QuestionDTO>> questionDTO = quizInterface.getQuestionFromId(questions);
		return questionDTO;
	}

	public ResponseEntity<Integer> getScore(Integer id, List<Response> responses) {
		ResponseEntity<Integer> score = quizInterface.getScore(responses);
		return score;
	}

}
