package tasslegro.rest.model;

public class Users {
	private int ID;
	private String Login = "";
	private String Pass = "";
	private String Name = "";
	private String Surname = "";
	private String Email = "";
	private int Phone;
	private int Account;
	private String Address = "";
	private String Town = "";
	private String ZipCode = "";

	public Users() {
	}

	public Users(String login, String pass, String email) {
		this.Login = login;
		this.Pass = pass;
		this.Email = email;
	}

	public int getID() {
		return this.ID;
	}

	public void setID(int id) {
		this.ID = id;
	}

	public String getLogin() {
		return this.Login;
	}

	public void setLogin(String login) {
		this.Login = login;
	}

	public String getPass() {
		return this.Pass;
	}

	public void setPass(String pass) {
		this.Pass = pass;
	}

	public String getName() {
		return this.Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public String getSurname() {
		return this.Surname;
	}

	public void setSurname(String surname) {
		this.Surname = surname;
	}

	public String getEmail() {
		return this.Email;
	}

	public void setEmail(String email) {
		this.Email = email;
	}

	public int getPhone() {
		return this.Phone;
	}

	public void setPhone(int phone) {
		this.Phone = phone;
	}

	public int getAccount() {
		return this.Account;
	}

	public void setAccount(int account) {
		this.Account = account;
	}

	public String getAddress() {
		return this.Address;
	}

	public void setAddress(String address) {
		this.Address = address;
	}

	public String getTown() {
		return this.Town;
	}

	public void setTown(String town) {
		this.Town = town;
	}

	public String getZipCode() {
		return this.ZipCode;
	}

	public void setZipCode(String zipCode) {
		this.ZipCode = zipCode;
	}
}
