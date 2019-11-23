package com.e.ango.Request;

public class PreferDto {

	private String cg_id;
	private Float user_score;
	private String category_nm;

	public String getCg_id() {
		return cg_id;
	}

	public void setCg_id(String cg_id) {
		this.cg_id = cg_id;
	}

	public Float getUser_score() {
		return user_score;
	}

	public void setUser_score(Float user_score) {
		this.user_score = user_score;
	}

	@Override
	public String toString() {
		return "PreferVO [cg_id=" + cg_id + ", user_score=" + user_score + "]";
	}

}
