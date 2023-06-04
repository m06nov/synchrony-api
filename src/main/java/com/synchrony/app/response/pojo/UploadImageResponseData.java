package com.synchrony.app.response.pojo; 
import java.util.ArrayList; 
public class UploadImageResponseData{
    public String id;
    public Object title;
    public Object description;
    public int datetime;
    public String type;
    public boolean animated;
    public int width;
    public int height;
    public int size;
    public int views;
    public int bandwidth;
    public Object vote;
    public boolean favorite;
    public Object nsfw;
    public Object section;
    public Object account_url;
    public int account_id;
    public boolean is_ad;
    public boolean in_most_viral;
    public ArrayList<Object> tags;
    public int ad_type;
    public String ad_url;
    public boolean in_gallery;
    public String deletehash;
    public String name;
    public String link;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Object getTitle() {
		return title;
	}
	public void setTitle(Object title) {
		this.title = title;
	}
	public Object getDescription() {
		return description;
	}
	public void setDescription(Object description) {
		this.description = description;
	}
	public int getDatetime() {
		return datetime;
	}
	public void setDatetime(int datetime) {
		this.datetime = datetime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isAnimated() {
		return animated;
	}
	public void setAnimated(boolean animated) {
		this.animated = animated;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getBandwidth() {
		return bandwidth;
	}
	public void setBandwidth(int bandwidth) {
		this.bandwidth = bandwidth;
	}
	public Object getVote() {
		return vote;
	}
	public void setVote(Object vote) {
		this.vote = vote;
	}
	public boolean isFavorite() {
		return favorite;
	}
	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}
	public Object getNsfw() {
		return nsfw;
	}
	public void setNsfw(Object nsfw) {
		this.nsfw = nsfw;
	}
	public Object getSection() {
		return section;
	}
	public void setSection(Object section) {
		this.section = section;
	}
	public Object getAccount_url() {
		return account_url;
	}
	public void setAccount_url(Object account_url) {
		this.account_url = account_url;
	}
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public boolean isIs_ad() {
		return is_ad;
	}
	public void setIs_ad(boolean is_ad) {
		this.is_ad = is_ad;
	}
	public boolean isIn_most_viral() {
		return in_most_viral;
	}
	public void setIn_most_viral(boolean in_most_viral) {
		this.in_most_viral = in_most_viral;
	}
	public ArrayList<Object> getTags() {
		return tags;
	}
	public void setTags(ArrayList<Object> tags) {
		this.tags = tags;
	}
	public int getAd_type() {
		return ad_type;
	}
	public void setAd_type(int ad_type) {
		this.ad_type = ad_type;
	}
	public String getAd_url() {
		return ad_url;
	}
	public void setAd_url(String ad_url) {
		this.ad_url = ad_url;
	}
	public boolean isIn_gallery() {
		return in_gallery;
	}
	public void setIn_gallery(boolean in_gallery) {
		this.in_gallery = in_gallery;
	}
	public String getDeletehash() {
		return deletehash;
	}
	public void setDeletehash(String deletehash) {
		this.deletehash = deletehash;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@Override
	public String toString() {
		return "UploadImageResponseData [id=" + id + ", title=" + title + ", description=" + description + ", datetime="
				+ datetime + ", type=" + type + ", animated=" + animated + ", width=" + width + ", height=" + height
				+ ", size=" + size + ", views=" + views + ", bandwidth=" + bandwidth + ", vote=" + vote + ", favorite="
				+ favorite + ", nsfw=" + nsfw + ", section=" + section + ", account_url=" + account_url
				+ ", account_id=" + account_id + ", is_ad=" + is_ad + ", in_most_viral=" + in_most_viral + ", tags="
				+ tags + ", ad_type=" + ad_type + ", ad_url=" + ad_url + ", in_gallery=" + in_gallery + ", deletehash="
				+ deletehash + ", name=" + name + ", link=" + link + "]";
	}
    
    
}
