package com.quizservice.repository;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.quizservice.dto.QuestionDTO;
import com.quizservice.dto.Response;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

	@GetMapping("question/generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,
			@RequestParam Integer numQuestions);

	// it will help in getting the questions for the particular id
	@PostMapping("question/getQuestions")
	public ResponseEntity<List<QuestionDTO>> getQuestionFromId(@RequestBody List<Integer> questionIds);

	@PostMapping("question/getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> response);
}
