package dto;

public class PreferDTO {

	private String cg_id;
	private String category_nm;
	private String question_type;
	private Float user_score;

	public String getCg_id() {
		return cg_id;
	}

	public String getQuuestion_type() {
		return question_type;
	}

	public void setQuuestion_type(String quuestion_type) {
		this.question_type = quuestion_type;
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

	public String getCategory_nm() {
		return category_nm;
	}

	public void setCategory_nm(String category_nm) {
		this.category_nm = category_nm;
	}

	@Override
	public String toString() {
		return "PreferVO [cg_id=" + cg_id + ", category_nm=" + category_nm + ", quuestion_type=" + question_type
				+ ", user_score=" + user_score + "]";
	}

}
