package com.example.myproject;

public class latest_news {

	private String Id;
	private String title;
	private String description;
	private String created;
	private String ImageFilename;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getImageFilename() {
		return ImageFilename;
	}

	public void setImageFilename(String imageFilename) {
		ImageFilename = imageFilename;
	}

}