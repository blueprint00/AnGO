package com.example.weathersearch.Response;


import com.example.weathersearch.Request.PreferDto;
import com.example.weathersearch.Request.UserDto;

import java.util.ArrayList;

public class ResponseDto {

	private int availability;
	private String token;
	private String response_msg;
	//private ArrayList<CategoryVO> category_list;
	private ArrayList<QuestionDto> question_list;
	private ArrayList<ReviewDto> review_list;
	private ArrayList<PreferDto> prefer_list;
	private UserDto user;

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getResponse_msg() {
		return response_msg;
	}

	public void setResponse_msg(String response_msg) {
		this.response_msg = response_msg;
	}

	public ArrayList<QuestionDto> getQuestion_list() {
		return question_list;
	}

	public void setQuestion_list(ArrayList<QuestionDto> question_list) {
		this.question_list = question_list;
	}

	public ArrayList<ReviewDto> getReview_list() {
		return review_list;
	}

	public void setReview_list(ArrayList<ReviewDto> review_list) {
		this.review_list = review_list;
	}

	public ArrayList<PreferDto> getPrefer_list() {
		return prefer_list;
	}

	public void setPrefer_list(ArrayList<PreferDto> prefer_list) {
		this.prefer_list = prefer_list;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ResponseVO [availability=" + availability + ", token=" + token + ", response_msg=" + response_msg
				+ ", question_list=" + question_list + ", review_list=" + review_list + ", prefer_list=" + prefer_list
				+ ", user=" + user + "]";
	}


}
