package dto;

import java.util.ArrayList;

public class RequestDTO {

	
	private String request_msg;
	private String weather_type;
	private String token;
	private UserDTO user;
	private ArrayList<PreferDTO> preference_list; // preferVO :
	private ArrayList<ReviewDTO> review_list;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRequest_msg() {
		return request_msg;
	}

	public void setRequest_msg(String request_msg) {
		this.request_msg = request_msg;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public String getWeather_type() {
		return weather_type;
	}


	public void setWeather_type(String weather_type) {
		this.weather_type = weather_type;
	}

	public ArrayList<PreferDTO> getPreference_list() {
		return preference_list;
	}

	public void setPreference_list(ArrayList<PreferDTO> preference_list) {
		this.preference_list = preference_list;
	}

	public ArrayList<ReviewDTO> getReview_list() {
		return review_list;
	}

	public void setReview_list(ArrayList<ReviewDTO> review_list) {
		this.review_list = review_list;
	}

	@Override
	public String toString() {
		return "RequestVO [request_msg=" + request_msg + ", weather_type="
				+ weather_type + ", token=" + token + ", user=" + user + ", preference_list=" + preference_list
				+ ", review_list=" + review_list + "]";
	}

}
