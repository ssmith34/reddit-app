package com.example.reddit.model;

public class Post {

	private String title;
	private int ups;
	private String image;
	

	public Post(String title, int ups, String image) {
		this.title = title;
		this.ups = ups;
		this.image = image;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getUps() {
		return ups;
	}
	public void setUps(int ups) {
		this.ups = ups;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
