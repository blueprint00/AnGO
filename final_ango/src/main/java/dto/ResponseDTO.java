package dto;

import java.util.ArrayList;

public class ResponseDTO {

	private int availability;
	private String token;
	private String response_msg;
	private String token_msg;
	private ArrayList<QuestionDTO> question_list; // weather_type, question_text
	private ArrayList<ReviewDTO> review_list;	// review_text, time, content_id
	private ArrayList<PreferDTO> prefer_list; // preferVO : cg_id, category_nm, user_score
	private UserDTO user; // user_id user_pw user_nm

	public int getAvailability() {
		return availability;
	}
	

	public String getToken_msg() {
		return token_msg;
	}


	public void setToken_msg(String token_msg) {
		this.token_msg = token_msg;
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


	public ArrayList<QuestionDTO> getQuestion_list() {
		return question_list;
	}

	public void setQuestion_list(ArrayList<QuestionDTO> question_list) {
		this.question_list = question_list;
	}

	public ArrayList<ReviewDTO> getReview_list() {
		return review_list;
	}

	public void setReview_list(ArrayList<ReviewDTO> review_list) {
		this.review_list = review_list;
	}

	public ArrayList<PreferDTO> getPrefer_list() {
		return prefer_list;
	}

	public void setPrefer_list(ArrayList<PreferDTO> prefer_list) {
		this.prefer_list = prefer_list;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "ResponseVO [availability=" + availability + ", token=" + token + ", response_msg=" + response_msg
				+ ", token_msg=" + token_msg + ", question_list=" + question_list + ", review_list=" + review_list
				+ ", prefer_list=" + prefer_list + ", user=" + user + "]";
	}





}
