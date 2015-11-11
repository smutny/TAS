package tasslegro.rest.model;

public class Auctions {
	private int Auciton_ID;
	private int User_ID;
	private String Description;
	private String Start_Date;
	private String End_Date;
	private float Price;
	
	public Auctions() {	
	}
	
	public int getAuciton_ID() {
		return this.Auciton_ID;
	}
	
	public void setAuciton_ID(int auciton_ID) {
		this.Auciton_ID = auciton_ID;
	}
	
	public int getUser_ID() {
		return this.User_ID;
	}
	
	public void setUser_ID(int user_ID) {
		this.User_ID = user_ID;
	}
	
	public String getDescription() {
		return this.Description;
	}
	
	public void setDescription(String description) {
		this.Description = description;
	}
	public String getStart_Date() {
		return this.Start_Date;
	}
	
	public void setStart_Date(String start_Date) {
		this.Start_Date = start_Date;
	}
	
	public String getEnd_Date() {
		return this.End_Date;
	}
	
	public void setEnd_Date(String end_Date) {
		this.End_Date = end_Date;
	}
	
	public float getPrice() {
		return this.Price;
	}
	
	public void setPrice(float price) {
		this.Price = price;
	}
	
}
