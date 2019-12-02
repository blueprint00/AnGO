package dto;

public class ReviewDTO {

	private String review_id;
	private Long review_idx;
	private String content_id;
	private String review_text;
	private String time;
	private float review_score;
	private String title;
	private String review_type;
	private String category_id;

	public Long getReview_idx() {
		return review_idx;
	}

	public void setReview_idx(Long review_idx) {
		this.review_idx = review_idx;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public String getReview_type() {
		return review_type;
	}

	public void setReview_type(String review_type) {
		this.review_type = review_type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
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
		return "ReviewVO [review_id=" + review_id + ", review_idx=" + review_idx + ", content_id=" + content_id
				+ ", review_text=" + review_text + ", time=" + time + ", review_score=" + review_score + ", title="
				+ title + ", review_type=" + review_type + ", category_id=" + category_id + "]";
	}

}
