package qu.change.domian;

public class Video {//视频数据类
	private int like;
	private String tag;
	private String address;
	private String author;
	private String videourl;
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like= like;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAuthor() {
		return address;
	}
	public void setAuthor(String author) {
		this.author= author;
	}
	public String getVideourl() {
		return videourl;
	}
	public void setVideourl(String videourl) {
		this.videourl= videourl;
	}

}
