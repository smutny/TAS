package tasslegro.rest.model;

import java.io.InputStream;

public class Images {
	private int id;
	private InputStream image = null;
	
	public Images(){
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public InputStream getImage() {
		return this.image;
	}
	
	public void setImage(InputStream inputStream) {
		this.image = inputStream;
	}
}
