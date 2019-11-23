package com.example.weathersearch.Request;

public class PreferDto {

	private String category_nm;
	private String cg_id;
	private Float user_score;

	public PreferDto() {
	}

	public PreferDto(String cg_id, Float user_score) {
		this.cg_id = cg_id;
		this.user_score = user_score;
	}

	public String getCg_id() {
		return cg_id;
	}

	public void setCg_id(String cg_id) {
		this.cg_id = cg_id;
	}

	public String getCategory_nm() { return category_nm; }

	public void setCategory_nm(String category_nm) { this.category_nm = category_nm; }

	public Float getUser_score() {
		return user_score;
	}

	public void setUser_score(Float user_score) {
		this.user_score = user_score;
	}

	@Override
	public String toString() {
		return "PreferDto [cg_id=" + cg_id + ", user_score=" + user_score + "]";
	}

}
