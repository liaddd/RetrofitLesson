package com.liadhorovitz.retrofitprac.models;

public class Comment{

	private String name;
	private int postId;
	private int id;
	private String body;
	private String email;


	public Comment(String name, int postId, int id, String body, String email) {
		this.name = name;
		this.postId = postId;
		this.id = id;
		this.body = body;
		this.email = email;
	}

	public String getName(){
		return name;
	}

	public int getPostId(){
		return postId;
	}

	public int getId(){
		return id;
	}

	public String getBody(){
		return body;
	}

	public String getEmail(){
		return email;
	}

	@Override
	public String toString() {
		return "Comment{" +
				"name='" + name + '\'' +
				", postId=" + postId +
				", id=" + id +
				", body='" + body + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}