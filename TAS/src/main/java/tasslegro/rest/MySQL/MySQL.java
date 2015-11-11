package tasslegro.rest.MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tasslegro.rest.model.*;

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
	
	public MySQL() throws ClassNotFoundException, SQLException{
		this.Driver = "com.mysql.jdbc.Driver";
		this.ConnectionDBAddres = "jdbc:mysql://localhost:3306/";
		this.UserName = "root";
		this.UserPassword = "root";
		this.StartConnection();
	}
	
	public MySQL( String driver, String connection_addres ) throws ClassNotFoundException, SQLException{
		this.Driver = driver;
		this.ConnectionDBAddres = connection_addres;
		this.UserName = "root";
		this.UserPassword = "root";
		this.StartConnection();
	}
	
	public void finalize(){
		this.Connected = false;
		try{
			if( this.StatementDB != null ){
				this.StatementDB.close();
			}
		}
		catch( SQLException error ){
			error.printStackTrace();
		}
		try{
			if( this.ConnectionDB != null ){
				this.ConnectionDB.close();
			}
		}
		catch( SQLException error ){
			error.printStackTrace();
		}
	}
	
	public void StartConnection() throws ClassNotFoundException, SQLException{
		Class.forName( this.Driver );
		try{
			this.ConnectionDB = DriverManager.getConnection( this.ConnectionDBAddres, this.UserName, this.UserPassword );
			this.StatementDB = this.ConnectionDB.createStatement();
		}
		catch( SQLException error ){
			error.printStackTrace();
			System.out.println( "Can not connect: " + this.ConnectionDBAddres );
			this.Connected = false;
			return;
		}
		catch( Exception error ){
			error.printStackTrace();
			System.out.println( "Can not connect: " + this.ConnectionDBAddres );
			this.Connected = false;
			return;
		}
		this.Connected = true;
	}
	
	/*
	 * USERS * 
	 */
	
	public List<Users> getUsers() throws SQLException{
		if( this.Connected ){
			try{
				this.SQLQueryString = "SELECT User_ID, Name, Surname, Email, Phone, Login, Account, Address, Town, ZipCode, Street "
						+ "FROM ONLINE_AUCTIONS.USERS";
				this.ResultDB = this.StatementDB.executeQuery( this.SQLQueryString );
				List<Users> UserList = new ArrayList<>();
				while( this.ResultDB.next() ){
					Users User = new Users();
					User.setID( this.ResultDB.getInt( "User_ID" ) );
					User.setName( this.ResultDB.getString( "Name" ) );
					User.setSurname( this.ResultDB.getString( "Surname" ) );
					User.setEmail( this.ResultDB.getString( "Email" ) );
					User.setPhone( this.ResultDB.getInt( "Phone" ) );
					User.setLogin( this.ResultDB.getString( "Login" ) );
					User.setAccount( this.ResultDB.getInt( "Account" ) );
					User.setAddress( this.ResultDB.getString( "Address" ) );
					User.setTown( this.ResultDB.getString( "Town" ) );
					User.setZipCode( this.ResultDB.getString( "ZipCode" ) );
					User.setStreet( this.ResultDB.getString( "Street" ) );
					UserList.add( User );
				}
				this.ResultDB = null;
				return UserList;
			}
			catch( SQLException error ){
				error.printStackTrace();
				System.out.println( "Can not do query: " + this.SQLQueryString + " in connection: "+ this.ConnectionDBAddres );
				return null;
			}
		}
		return null;
	}
	
	public Users getUserById( String id ) throws SQLException{
		if( this.Connected ){
			try{
				this.SQLQueryString = "SELECT User_ID, Name, Surname, Email, Phone, Login, Account, Address, Town, ZipCode, Street "
						+ "FROM ONLINE_AUCTIONS.USERS "
						+ "WHERE User_ID = " + id;
				this.ResultDB = this.StatementDB.executeQuery( this.SQLQueryString );
				Users User = new Users();
				if( this.ResultDB.next() ){
					User.setID( this.ResultDB.getInt( "User_ID" ) );
					User.setName( this.ResultDB.getString( "Name" ) );
					User.setSurname( this.ResultDB.getString( "Surname" ) );
					User.setEmail( this.ResultDB.getString( "Email" ) );
					User.setPhone( this.ResultDB.getInt( "Phone" ) );
					User.setLogin( this.ResultDB.getString( "Login" ) );
					User.setAccount( this.ResultDB.getInt( "Account" ) );
					User.setAddress( this.ResultDB.getString( "Address" ) );
					User.setTown( this.ResultDB.getString( "Town" ) );
					User.setZipCode( this.ResultDB.getString( "ZipCode" ) );
					User.setStreet( this.ResultDB.getString( "Street" ) );
				}
				else{
					System.out.println("User " + id +  " not found!");
					this.ResultDB = null;
					return null;
				}
				this.ResultDB = null;
				return User;
			}
			catch( SQLException error ){
				error.printStackTrace();
				System.out.println( "Can not do query: " + this.SQLQueryString + " in connection: "+ this.ConnectionDBAddres );
				return null;
			}
		}
		return null;
	}
	
	public Users getUserById( int id ) throws SQLException{
		return getUserById( Integer.toString( id ) );
	}
	
	public Users getUserByLogin( String login ){
		if( this.Connected ){
			try{
				this.SQLQueryString = "SELECT User_ID, Name, Surname, Email, Phone, Login, Account, Address, Town, ZipCode, Street "
						+ "FROM ONLINE_AUCTIONS.USERS "
						+ "WHERE Login = \"" + login + "\"";
				this.ResultDB = this.StatementDB.executeQuery( this.SQLQueryString );
				Users User = new Users();
				if( this.ResultDB.next() ){
					User.setID( this.ResultDB.getInt( "User_ID" ) );
					User.setName( this.ResultDB.getString( "Name" ) );
					User.setSurname( this.ResultDB.getString( "Surname" ) );
					User.setEmail( this.ResultDB.getString( "Email" ) );
					User.setPhone( this.ResultDB.getInt( "Phone" ) );
					User.setLogin( this.ResultDB.getString( "Login" ) );
					User.setAccount( this.ResultDB.getInt( "Account" ) );
					User.setAddress( this.ResultDB.getString( "Address" ) );
					User.setTown( this.ResultDB.getString( "Town" ) );
					User.setZipCode( this.ResultDB.getString( "ZipCode" ) );
					User.setStreet( this.ResultDB.getString( "Street" ) );
				}
				else{
					System.out.println("User " + login +  " not found!");
					this.ResultDB = null;
					return null;
				}
				this.ResultDB = null;
				return User;
			}
			catch( SQLException error ){
				error.printStackTrace();
				System.out.println( "Can not do query: " + this.SQLQueryString + " in connection: "+ this.ConnectionDBAddres );
				return null;
			}
		}
		return null;
	}
	
	public boolean checkUserByLogin( String login ){
		if( this.Connected ){
			try{
				this.SQLQueryString = "SELECT User_ID, Name, Surname, Email, Phone, Login, Account, Address, Town, ZipCode, Street "
						+ "FROM ONLINE_AUCTIONS.USERS "
						+ "WHERE Login = \"" + login + "\"";
				this.ResultDB = this.StatementDB.executeQuery( this.SQLQueryString );
				if( this.ResultDB.next() ){
					this.ResultDB = null;
					return false;
				}
				else{
					this.ResultDB = null;
					return true;
				}
			}
			catch( SQLException error ){
				error.printStackTrace();
				System.out.println( "Can not do query: " + this.SQLQueryString + " in connection: "+ this.ConnectionDBAddres );
				return false;
			}
		}
		return false;
	}
	
	public Users addUser( Users user ){
		if( this.Connected ){
			try{
				this.SQLQueryString = "INSERT INTO ONLINE_AUCTIONS.USERS(Name, Surname, Email, Phone, Login, Pass, Account, Address, Town, ZipCode, Street)"
						+ "VALUES (\""
					+ user.getName() + "\", \""
					+ user.getSurname() + "\", \""
					+ user.getEmail() + "\", "
					+ user.getPhone() + ", \""
					+ user.getLogin() + "\",\""
					+ user.getPass() + "\", "
					+ user.getAccount() + ", \""
					+ user.getAddress() + "\", \""
					+ user.getTown() + "\", \""
					+ user.getZipCode() + "\", \""
					+ user.getStreet() + "\");";
				this.StatementDB.executeUpdate( this.SQLQueryString );
				return getUserByLogin( user.getLogin() );
			}
			catch( SQLException error ){
				error.printStackTrace();
				System.out.println( "Can not do query: " + this.SQLQueryString + " in connection: "+ this.ConnectionDBAddres );
				return null;
			}
		}
		return null;
	}
	
	/*
	 * AUCTIONS * 
	 */
	
	public List<Auctions> getAuctions() throws SQLException{
		if( this.Connected ){
			try{
				this.SQLQueryString = "SELECT Auciton_ID, User_ID, Description, Start_Date, End_Date, Price "
						+ "FROM ONLINE_AUCTIONS.AUCTIONS";
				this.ResultDB = this.StatementDB.executeQuery( this.SQLQueryString );
				List<Auctions> AuctionList = new ArrayList<>();
				while( this.ResultDB.next() ){
					Auctions Auction = new Auctions();
					Auction.setAuciton_ID( this.ResultDB.getInt( "Auciton_ID" ) );
					Auction.setUser_ID( this.ResultDB.getInt( "User_ID" ) );
					Auction.setDescription( this.ResultDB.getString( "Description" ) );
					Auction.setStart_Date( this.ResultDB.getString( "Start_Date" ) );
					Auction.setEnd_Date( this.ResultDB.getString( "End_Date" ) );
					Auction.setPrice( this.ResultDB.getFloat( "Price" ) );
					AuctionList.add( Auction );
				}
				this.ResultDB = null;
				return AuctionList;
			}
			catch( SQLException error ){
				error.printStackTrace();
				System.out.println( "Can not do query: " + this.SQLQueryString + " in connection: "+ this.ConnectionDBAddres );
				return null;
			}
		}
		return null;
	}
}