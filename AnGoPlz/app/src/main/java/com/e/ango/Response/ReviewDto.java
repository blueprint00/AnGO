package com.e.ango.Response;

import java.sql.Timestamp;

public class ReviewDto {

	private String review_id; // 유저 아이디
	private String content_id; // 카테고리 아이디
	private String review_text; //리뷰
	private Timestamp time; //작성 시간
	private float review_score; //리뷰 점수

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

	public String getReview_id() {
		return review_id;
	}

	public void setReview_id(String review_id) {
		this.review_id = review_id;
	}

	public float getReview_score() {
		return review_score;
	}

	public void setReview_score(float review_score) {
		this.review_score = review_score;
	}

	@Override
	public String toString() {
		return "ReviewVO [review_id=" + review_id + ", content_id=" + content_id + ", review_text=" + review_text
				+ ", time=" + time + ", review_score=" + review_score + "]";
	}



}