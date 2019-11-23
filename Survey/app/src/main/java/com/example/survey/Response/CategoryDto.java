package com.example.survey.Response;

public class CategoryDto {

	private String category_id;
	private String category_nm;


	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public String getCategory_nm() {
		return category_nm;
	}

	public void setCategory_nm(String category_nm) {
		this.category_nm = category_nm;
	}


	@Override
	public String toString() {
		return "CategoryDto [category_id=" + category_id + ", category_nm=" + category_nm  + "]";
	}

}
