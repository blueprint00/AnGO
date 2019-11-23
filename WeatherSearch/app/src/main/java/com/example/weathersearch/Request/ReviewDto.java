package com.example.weathersearch.Request;

import java.sql.Timestamp;

public class ReviewDto {

	private String content_id;
	private String review_text;
	private Timestamp time;

	public String getContent_id() {
		return content_id;
	}

	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}

	public String getReview_text() {
		return review_text;
	}

	public void setReview_text(String review_text) {
		this.review_text = review_text;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "ReviewVO [content_id=" + content_id + ", review_text=" + review_text + ", time=" + time + "]";
	}


}
