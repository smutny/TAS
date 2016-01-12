package tasslegro.rest.model;

public class AuctionsAuth extends Auctions {
	String login = "";
	String pass = "";

	public AuctionsAuth() {
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
