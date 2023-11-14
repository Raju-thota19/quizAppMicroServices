package com.quizservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QuizDTO {

	String category;
	Integer numQ;
	String title;

}
