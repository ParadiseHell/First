package com.ct.movie.data;

import com.ct.movie.R;

public class MovieComment {
	private String title;
	private String content;
	private String rating;
	private String like;
	private String dislike;
	private int images[];

	public MovieComment() {
		super();
		images = new int[] { R.drawable.comment_pic_a,
				R.drawable.comment_pic_b, R.drawable.comment_pic_c,
				R.drawable.comment_pic_d };
	}

	public int[] getImages() {
		return images;
	}

	public void setImages(int[] images) {
		this.images = images;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getLike() {
		return like;
	}

	public void setLike(String like) {
		this.like = like;
	}

	public String getDislike() {
		return dislike;
	}

	public void setDislike(String dislike) {
		this.dislike = dislike;
	}
}
