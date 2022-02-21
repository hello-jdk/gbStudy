package model;

public class Mv_VO {

	private int mNum;
	private String mName;
	private String onAir;
	private String genre;
	private String nation;
	private String showTime;
	private String release;
	private String dName;
	private String actors;
	private String rAge;
	private int rAgeInt;
	private int rank;
	private String story;
	private int stock;
	private int price;
	
	public int getmNum() {
		return mNum;
	}
	public void setmNum(int mNum) {
		this.mNum = mNum;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getOnAir() {
		return onAir;
	}
	public void setOnAir(String onAir) {
		this.onAir = onAir;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	public String getRelease() {
		return release;
	}
	public void setRelease(String release) {
		this.release = release;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	public String getrAge() {
		return rAge;
	}
	public void setrAge(String rAge) {
		this.rAge = rAge;
	}
	public int getrAgeInt() {
		return rAgeInt;
	}
	public void setrAgeInt(int rAgeInt) {
		this.rAgeInt = rAgeInt;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Mv_VO [mNum=" + mNum + ", mName=" + mName + ", onAir=" + onAir + ", genre=" + genre + ", nation="
				+ nation + ", showTime=" + showTime + ", release=" + release + ", dName=" + dName + ", actors=" + actors
				+ ", rAge=" + rAge + ", rAgeInt=" + rAgeInt + ", rank=" + rank + ", story=" + story + ", stock=" + stock
				+ ", price=" + price + "]";
	}
	
	
	
}
