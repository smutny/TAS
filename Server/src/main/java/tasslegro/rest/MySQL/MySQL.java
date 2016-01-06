package tasslegro.rest.MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tasslegro.rest.model.Auctions;
import tasslegro.rest.model.Images;
import tasslegro.rest.model.Users;

public class MySQL {

	private String Driver;
	private String ConnectionDBAddres;
	Connection ConnectionDB = null;
	Statement StatementDB = null;
	ResultSet ResultDB = null;
	String UserName;
	String UserPassword;
	String SQLQueryString;
	Boolean Connected = false;

	int selectLimit = 10;

	public MySQL() throws ClassNotFoundException, SQLException {
		this.Driver = "com.mysql.jdbc.Driver";
		this.ConnectionDBAddres = "jdbc:mysql://localhost:3306/";
		this.UserName = "root";
		this.UserPassword = "root";
		this.StartConnection();
	}

	public MySQL(String driver, String connection_addres) throws ClassNotFoundException, SQLException {
		this.Driver = driver;
		this.ConnectionDBAddres = connection_addres;
		this.UserName = "root";
		this.UserPassword = "root";
		this.StartConnection();
	}

	public Connection getConnection() {
		return this.ConnectionDB;
	}

	public boolean IsConnected() {
		return this.Connected;
	}

	public void finalize() {
		this.Connected = false;
		try {
			if (this.StatementDB != null) {
				this.StatementDB.close();
			}
		} catch (SQLException error) {
			System.err.println("[ERROR] " + new Date() + ": " + error.getMessage());
		}
		try {
			if (this.ConnectionDB != null) {
				this.ConnectionDB.close();
			}
		} catch (SQLException error) {
			System.err.println("[ERROR] " + new Date() + ": " + error.getMessage());
		}
	}

	public void StartConnection() throws ClassNotFoundException, SQLException {
		Class.forName(this.Driver);
		try {
			this.ConnectionDB = DriverManager.getConnection(this.ConnectionDBAddres, this.UserName, this.UserPassword);
			this.StatementDB = this.ConnectionDB.createStatement();
		} catch (SQLException error) {
			System.err.println("[ERROR] " + new Date() + ": " + error.getMessage());
			System.err.println("[ERROR] " + new Date() + ": Can not connect: " + this.ConnectionDBAddres);
			this.Connected = false;
			return;
		} catch (Exception error) {
			System.err.println("[ERROR] " + new Date() + ": " + error.getMessage());
			System.err.println("[ERROR] " + new Date() + ": Can not connect: " + this.ConnectionDBAddres);
			this.Connected = false;
			return;
		}
		this.Connected = true;
	}

	/*
	 * USERS *
	 */

	public List<Users> getUsers() throws SQLException {
		if (this.Connected) {
			try {
				this.SQLQueryString = "SELECT User_ID, Name, Surname, Email, Phone, Login, Account, Address, Town, ZipCode "
						+ "FROM ONLINE_AUCTIONS.USERS";
				this.ResultDB = this.StatementDB.executeQuery(this.SQLQueryString);
				List<Users> UserList = new ArrayList<>();
				while (this.ResultDB.next()) {
					Users User = new Users();
					User.setId(this.ResultDB.getInt("User_ID"));
					User.setName(this.ResultDB.getString("Name"));
					User.setSurname(this.ResultDB.getString("Surname"));
					User.setEmail(this.ResultDB.getString("Email"));
					User.setPhone(this.ResultDB.getInt("Phone"));
					User.setLogin(this.ResultDB.getString("Login"));
					User.setAccount(this.ResultDB.getInt("Account"));
					User.setAddress(this.ResultDB.getString("Address"));
					User.setTown(this.ResultDB.getString("Town"));
					User.setZipCode(this.ResultDB.getString("ZipCode"));
					UserList.add(User);
				}
				this.ResultDB = null;
				return UserList;
			} catch (SQLException error) {
				this.ResultDB = null;
				System.err.println("[ERROR] " + new Date() + ": " + error.getMessage());
				System.err.println("[ERROR] " + new Date() + ": Can not do query: " + this.SQLQueryString
						+ " in connection: " + this.ConnectionDBAddres);
				return null;
			}
		}
		return null;
	}

	public Users getUserById(String id) throws SQLException {
		if (this.Connected) {
			try {
				this.SQLQueryString = "SELECT User_ID, Name, Surname, Email, Phone, Login, Account, Address, Town, ZipCode "
						+ "FROM ONLINE_AUCTIONS.USERS " + "WHERE User_ID = " + id;
				this.ResultDB = this.StatementDB.executeQuery(this.SQLQueryString);
				Users User = new Users();
				if (this.ResultDB.next()) {
					User.setId(this.ResultDB.getInt("User_ID"));
					User.setName(this.ResultDB.getString("Name"));
					User.setSurname(this.ResultDB.getString("Surname"));
					User.setEmail(this.ResultDB.getString("Email"));
					User.setPhone(this.ResultDB.getInt("Phone"));
					User.setLogin(this.ResultDB.getString("Login"));
					User.setAccount(this.ResultDB.getInt("Account"));
					User.setAddress(this.ResultDB.getString("Address"));
					User.setTown(this.ResultDB.getString("Town"));
					User.setZipCode(this.ResultDB.getString("ZipCode"));
				} else {
					System.out.println("[LOG] " + new Date() + ": User with id: " + id + " not found!");
					this.ResultDB = null;
					return null;
				}
				this.ResultDB = null;
				return User;
			} catch (SQLException error) {
				this.ResultDB = null;
				System.err.println("[ERROR] " + new Date() + ": " + error.getMessage());
				System.err.println("[ERROR] " + new Date() + ": Can not do query: " + this.SQLQueryString
						+ " in connection: " + this.ConnectionDBAddres);
				return null;
			}
		}
		return null;
	}

	public Users getUserById(int id) throws SQLException {
		return getUserById(Integer.toString(id));
	}

	public Users getUserByLogin(String login) {
		if (this.Connected) {
			try {
				this.SQLQueryString = "SELECT User_ID, Name, Surname, Email, Phone, Login, Account, Address, Town, ZipCode "
						+ "FROM ONLINE_AUCTIONS.USERS " + "WHERE Login = \"" + login + "\"";
				this.ResultDB = this.StatementDB.executeQuery(this.SQLQueryString);
				Users User = new Users();
				if (this.ResultDB.next()) {
					User.setId(this.ResultDB.getInt("User_ID"));
					User.setName(this.ResultDB.getString("Name"));
					User.setSurname(this.ResultDB.getString("Surname"));
					User.setEmail(this.ResultDB.getString("Email"));
					User.setPhone(this.ResultDB.getInt("Phone"));
					User.setLogin(this.ResultDB.getString("Login"));
					User.setAccount(this.ResultDB.getInt("Account"));
					User.setAddress(this.ResultDB.getString("Address"));
					User.setTown(this.ResultDB.getString("Town"));
					User.setZipCode(this.ResultDB.getString("ZipCode"));
				} else {
					System.out.println("[LOG] " + new Date() + ": User " + login + " not found!");
					this.ResultDB = null;
					return null;
				}
				this.ResultDB = null;
				return User;
			} catch (SQLException error) {
				this.ResultDB = null;
				System.err.println("[ERROR] " + new Date() + ": " + error.getMessage());
				System.err.println("[ERROR] " + new Date() + ": Can not do query: " + this.SQLQueryString
						+ " in connection: " + this.ConnectionDBAddres);
				return null;
			}
		}
		return null;
	}

	public boolean checkExistUserByLogin(String login) {
		if (this.Connected) {
			try {
				this.SQLQueryString = "SELECT User_ID " + "FROM ONLINE_AUCTIONS.USERS " + "WHERE Login = \"" + login
						+ "\"";
				this.ResultDB = this.StatementDB.executeQuery(this.SQLQueryString);
				if (this.ResultDB.next()) {
					this.ResultDB = null;
					return true;
				} else {
					this.ResultDB = null;
					return false;
				}
			} catch (SQLException error) {
				this.ResultDB = null;
				System.err.println("[ERROR] " + new Date() + ": " + error.getMessage());
				System.err.println("[ERROR] " + new Date() + ": Can not do query: " + this.SQLQueryString
						+ " in connection: " + this.ConnectionDBAddres);
				return false;
			}
		}
		return false;
	}

	public boolean checkExistUserByEmail(String email) {
		if (this.Connected) {
			try {
				this.SQLQueryString = "SELECT User_ID " + "FROM ONLINE_AUCTIONS.USERS " + "WHERE Email = \"" + email
						+ "\"";
				this.ResultDB = this.StatementDB.executeQuery(this.SQLQueryString);
				if (this.ResultDB.next()) {
					this.ResultDB = null;
					return true;
				} else {
					this.ResultDB = null;
					return false;
				}
			} catch (SQLException error) {
				this.ResultDB = null;
				System.err.println("[ERROR] " + new Date() + ": " + error.getMessage());
				System.err.println("[ERROR] " + new Date() + ": Can not do query: " + this.SQLQueryString
						+ " in connection: " + this.ConnectionDBAddres);
				return false;
			}
		}
		return false;
	}

	public boolean checkExistUserById(String id) {
		if (this.Connected) {
			try {
				this.SQLQueryString = "SELECT User_ID " + "FROM ONLINE_AUCTIONS.USERS " + "WHERE User_ID = " + id + ";";
				this.ResultDB = this.StatementDB.executeQuery(this.SQLQueryString);
				if (this.ResultDB.next()) {
					this.ResultDB = null;
					return true;
				} else {
					this.ResultDB = null;
					return false;
				}
			} catch (SQLException error) {
				this.ResultDB = null;
				System.err.println("[ERROR] " + new Date() + ": " + error.getMessage());
				System.err.println("[ERROR] " + new Date() + ": Can not do query: " + this.SQLQueryString
						+ " in connection: " + this.ConnectionDBAddres);
				return false;
			}
		}
		return false;
	}

	public boolean checkExistUserById(int id) {
		return this.checkExistUserById(Integer.toString(id));
	}

	public Users addUser(Users user) {
		if (this.Connected) {
			try {
				this.SQLQueryString = "INSERT INTO ONLINE_AUCTIONS.USERS(Name, Surname, Email, Phone, Login, Pass, Account, Address, Town, ZipCode)"
						+ "VALUES (\"" + user.getName() + "\", \"" + user.getSurname() + "\", \"" + user.getEmail()
						+ "\", " + user.getPhone() + ", \"" + user.getLogin() + "\",\"" + user.getPass() + "\", "
						+ user.getAccount() + ", \"" + user.getAddress() + "\", \"" + user.getTown() + "\", \""
						+ user.getZipCode() + "\" );";
				this.StatementDB.executeUpdate(this.SQLQueryString);
				return getUserByLogin(user.getLogin());
			} catch (SQLException error) {
				System.err.println("[ERROR] " + new Date() + ": " + error.getMessage());
				System.err.println("[ERROR] " + new Date() + ": Can not do query: " + this.SQLQueryString
						+ " in connection: " + this.ConnectionDBAddres);
				return null;
			}
		}
		return null;
	}

	/*
	 * AUCTIONS *
	 */

	public List<Auctions> getAuctions() throws SQLException {
		if (this.Connected) {
			try {
				this.SQLQueryString = "SELECT Auciton_ID, User_ID, Image_ID, Title, Description, Start_Date, End_Date, Price "
						+ "FROM ONLINE_AUCTIONS.AUCTIONS_VIEW";
				this.ResultDB = this.StatementDB.executeQuery(this.SQLQueryString);
				List<Auctions> AuctionList = new ArrayList<>();
				while (this.ResultDB.next()) {
					Auctions Auction = new Auctions();
					Auction.setAuciton_ID(this.ResultDB.getInt("Auciton_ID"));
					Auction.setUser_ID(this.ResultDB.getInt("User_ID"));
					Auction.setImage_ID(this.ResultDB.getInt("Image_ID"));
					Auction.setTitle(this.ResultDB.getString("Title"));
					Auction.setDescription(this.ResultDB.getString("Description"));
					Auction.setStart_Date(this.ResultDB.getString("Start_Date"));
					Auction.setEnd_Date(this.ResultDB.getString("End_Date"));
					Auction.setPrice(this.ResultDB.getFloat("Price"));
					AuctionList.add(Auction);
				}
				this.ResultDB = null;
				return AuctionList;
			} catch (SQLException error) {
				this.ResultDB = null;
				System.err.println("[ERROR] " + new Date() + ": " + error.getMessage());
				System.err.println("[ERROR] " + new Date() + ": Can not do query: " + this.SQLQueryString
						+ " in connection: " + this.ConnectionDBAddres);
				return null;
			}
		}
		return null;
	}

	public List<Auctions> getAuctionsByPage(int page) throws SQLException {
		if (this.Connected) {
			if (page < 1) {

				return null;
			}
			try {
				page = (page - 1) * this.selectLimit;
				this.SQLQueryString = "SELECT Auciton_ID, User_ID, Image_ID, Title, Description, Start_Date, End_Date, Price "
						+ "FROM ONLINE_AUCTIONS.AUCTIONS_VIEW LIMIT " + this.selectLimit + " OFFSET " + page;
				this.ResultDB = this.StatementDB.executeQuery(this.SQLQueryString);
				List<Auctions> AuctionList = new ArrayList<>();
				while (this.ResultDB.next()) {
					Auctions Auction = new Auctions();
					Auction.setAuciton_ID(this.ResultDB.getInt("Auciton_ID"));
					Auction.setUser_ID(this.ResultDB.getInt("User_ID"));
					Auction.setImage_ID(this.ResultDB.getInt("Image_ID"));
					Auction.setTitle(this.ResultDB.getString("Title"));
					Auction.setDescription(this.ResultDB.getString("Description"));
					Auction.setStart_Date(this.ResultDB.getString("Start_Date"));
					Auction.setEnd_Date(this.ResultDB.getString("End_Date"));
					Auction.setPrice(this.ResultDB.getFloat("Price"));
					AuctionList.add(Auction);
				}
				this.ResultDB = null;
				return AuctionList;
			} catch (SQLException error) {
				this.ResultDB = null;
				System.err.println("[ERROR] " + new Date() + ": " + error.getMessage());
				System.err.println("[ERROR] " + new Date() + ": Can not do query: " + this.SQLQueryString
						+ " in connection: " + this.ConnectionDBAddres);
				return null;
			}
		}
		return null;
	}

	public Auctions addAuction(Auctions auction) {
		if (this.Connected) {
			try {
				this.SQLQueryString = "INSERT INTO ONLINE_AUCTIONS.AUCTIONS(User_ID, Image_ID, Title, Description, Start_Date, End_Date, Price)"
						+ "VALUES (\"" + auction.getUser_ID() + "\"," + auction.getImage_ID() + ", \""
						+ auction.getTitle() + "\", \"" + auction.getDescription() + "\", " + "NOW(), "
						+ "DATE_ADD(NOW(),INTERVAL 2 WEEK), " + auction.getPrice() + " );";
				this.StatementDB.executeUpdate(this.SQLQueryString);
				return getAuctionByTitleDescriptionId(auction);
			} catch (SQLException error) {
				System.err.println("[ERROR] " + new Date() + ": " + error.getMessage());
				System.err.println("[ERROR] " + new Date() + ": Can not do query: " + this.SQLQueryString
						+ " in connection: " + this.ConnectionDBAddres);
				return null;
			}
		}
		return null;
	}

	public Auctions getAuctionByTitleDescriptionId(Auctions auction) {
		if (this.Connected) {
			try {
				this.SQLQueryString = "SELECT Auciton_ID, User_ID, Image_ID, Title, Description, Start_Date, End_Date, Price "
						+ "FROM ONLINE_AUCTIONS.AUCTIONS WHERE User_ID = " + auction.getUser_ID() + " AND Title = \""
						+ auction.getTitle() + "\" AND Description = \"" + auction.getDescription() + "\";";
				this.ResultDB = this.StatementDB.executeQuery(this.SQLQueryString);
				Auctions tmp = new Auctions();
				if (this.ResultDB.next()) {
					tmp.setAuciton_ID(this.ResultDB.getInt("Auciton_ID"));
					tmp.setUser_ID(this.ResultDB.getInt("User_ID"));
					tmp.setImage_ID(this.ResultDB.getInt("Image_ID"));
					tmp.setTitle(this.ResultDB.getString("Title"));
					tmp.setDescription(this.ResultDB.getString("Description"));
					tmp.setStart_Date(this.ResultDB.getString("Start_Date"));
					tmp.setEnd_Date(this.ResultDB.getString("End_Date"));
					tmp.setPrice(this.ResultDB.getFloat("Price"));
				} else {
					System.out.println(
							"[LOG] " + new Date() + ": Auction with title \"" + auction.getTitle() + "\" not found!");
					this.ResultDB = null;
					return null;
				}
				this.ResultDB = null;
				return tmp;
			} catch (SQLException error) {
				this.ResultDB = null;
				System.err.println("[ERROR] " + new Date() + ": " + error.getMessage());
				System.err.println("[ERROR] " + new Date() + ": Can not do query: " + this.SQLQueryString
						+ " in connection: " + this.ConnectionDBAddres);
				return null;
			}
		}
		return null;
	}

	/*
	 * IMAGES *
	 */

	public List<Images> getImagesList() throws SQLException {
		if (this.Connected) {
			try {
				this.SQLQueryString = "SELECT ID " + "FROM ONLINE_AUCTIONS.IMAGES";
				this.ResultDB = this.StatementDB.executeQuery(this.SQLQueryString);
				List<Images> ImageList = new ArrayList<>();
				while (this.ResultDB.next()) {
					Images Image = new Images();
					Image.setId(this.ResultDB.getInt("ID"));
					ImageList.add(Image);
				}
				this.ResultDB = null;
				return ImageList;
			} catch (SQLException error) {
				this.ResultDB = null;
				System.err.println("[ERROR] " + new Date() + ": " + error.getMessage());
				System.err.println("[ERROR] " + new Date() + ": Can not do query: " + this.SQLQueryString
						+ " in connection: " + this.ConnectionDBAddres);
				return null;
			}
		}
		return null;
	}

	public Images addImage(Images image) throws SQLException {
		if (this.Connected) {
			try {
				this.SQLQueryString = "INSERT INTO ONLINE_AUCTIONS.IMAGES(Image) VALUES(?)";
				PreparedStatement pre = this.ConnectionDB.prepareStatement(this.SQLQueryString);
				pre.setBlob(1, image.getImage());
				pre.executeUpdate();
				this.ResultDB = pre.getGeneratedKeys();
				if (this.ResultDB.next()) {
					Images tmp = new Images();
					tmp.setId(this.ResultDB.getInt(1));
					this.ResultDB = null;
					return tmp;
				} else {
					this.ResultDB = null;
					return null;
				}
			} catch (SQLException error) {
				this.ResultDB = null;
				System.err.println("[ERROR] " + new Date() + ": " + error.getMessage());
				System.err.println("[ERROR] " + new Date() + ": Can not do query: " + this.SQLQueryString
						+ " in connection: " + this.ConnectionDBAddres);
				return null;
			}
		}
		return null;
	}

	public Images getImageById(String id) throws SQLException {
		if (this.Connected) {
			try {
				this.SQLQueryString = "SELECT ID, Image " + "FROM ONLINE_AUCTIONS.IMAGES " + "WHERE ID = " + id + ";";
				this.ResultDB = this.StatementDB.executeQuery(this.SQLQueryString);
				Images Image = new Images();
				if (this.ResultDB.next()) {
					Image.setId(this.ResultDB.getInt("ID"));
					Image.setImage(this.ResultDB.getBinaryStream("Image"));
				} else {
					this.ResultDB = null;
					return null;
				}
				this.ResultDB = null;
				return Image;
			} catch (SQLException error) {
				this.ResultDB = null;
				System.err.println("[ERROR] " + new Date() + ": " + error.getMessage());
				System.err.println("[ERROR] " + new Date() + ": Can not do query: " + this.SQLQueryString
						+ " in connection: " + this.ConnectionDBAddres);
				return null;
			}
		}
		return null;
	}

	public Images deleteImageById(String id) {
		if (this.Connected) {
			try {
				this.SQLQueryString = "DELETE " + "FROM ONLINE_AUCTIONS.IMAGES " + "WHERE ID = " + id + ";";
				this.StatementDB.executeUpdate(this.SQLQueryString);
				Images tmp = new Images();
				tmp.setId(Integer.parseInt(id));
				return tmp;
			} catch (SQLException error) {
				this.ResultDB = null;
				System.err.println("[ERROR] " + new Date() + ": " + error.getMessage());
				System.err.println("[ERROR] " + new Date() + ": Can not do query: " + this.SQLQueryString
						+ " in connection: " + this.ConnectionDBAddres);
				return null;
			}
		}
		return null;
	}

	public Images deleteImageById(int id) {
		return this.deleteImageById(Integer.toString(id));
	}

	public boolean checkExistImageById(String id) {
		if (this.Connected) {
			try {
				this.SQLQueryString = "SELECT ID " + "FROM ONLINE_AUCTIONS.IMAGES";
				this.ResultDB = this.StatementDB.executeQuery(this.SQLQueryString);
				if (this.ResultDB.next()) {
					Images Image = new Images();
					Image.setId(this.ResultDB.getInt("ID"));
					return true;
				} else {
					this.ResultDB = null;
					return false;
				}
			} catch (SQLException error) {
				this.ResultDB = null;
				System.err.println("[ERROR] " + new Date() + ": " + error.getMessage());
				System.err.println("[ERROR] " + new Date() + ": Can not do query: " + this.SQLQueryString
						+ " in connection: " + this.ConnectionDBAddres);
				return false;
			}
		}
		return false;
	}

	public boolean checkExistImageById(int id) {
		return checkExistImageById(Integer.toString(id));
	}
}