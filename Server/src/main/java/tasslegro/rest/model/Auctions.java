package tasslegro.rest.model;

public class Auctions {
	private int auciton_ID;
	private int user_ID;
	private int image_ID;
	private String title = "";
	private String description = "";
	private String start_Date = "";
	private String end_Date = "";
	private float price;

	public Auctions() {
	}

	public int getAuciton_ID() {
		return this.auciton_ID;
	}

	public void setAuciton_ID(int auciton_ID) {
		this.auciton_ID = auciton_ID;
	}

	public int getUser_ID() {
		return this.user_ID;
	}

	public void setUser_ID(int user_ID) {
		this.user_ID = user_ID;
	}

	public int getImage_ID() {
		return this.image_ID;
	}

	public void setImage_ID(int image_ID) {
		this.image_ID = image_ID;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStart_Date() {
		return this.start_Date;
	}

	public void setStart_Date(String start_Date) {
		this.start_Date = start_Date;
	}

	public String getEnd_Date() {
		return this.end_Date;
	}

	public void setEnd_Date(String end_Date) {
		this.end_Date = end_Date;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
}
