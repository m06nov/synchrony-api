package com.synchrony.app.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ImageInfoDTO {

	@Id
	@GeneratedValue
	private Long id;
	private Long userId;
	private String link;
	private String imageId;
	private String deletehash;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getDeletehash() {
		return deletehash;
	}
	public void setDeletehash(String deletehash) {
		this.deletehash = deletehash;
	}
	
	
	
}
