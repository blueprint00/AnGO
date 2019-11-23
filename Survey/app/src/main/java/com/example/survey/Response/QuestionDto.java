package com.example.survey.Response;

public class QuestionDto {

	private String weather_type;
	private String question_text;
	private int total;

	public String getWeather_type() {
		return weather_type;
	}

	public void setWeather_type(String weather_type) {
		this.weather_type = weather_type;
	}

	public String getQuestion_text() {
		return question_text;
	}

	public void setQuestion_text(String question_text) {
		this.question_text = question_text;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "QuestionDto [weather_type=" + weather_type + ", question_text=" + question_text + ", total=" + total
				+ "]";
	}

}
