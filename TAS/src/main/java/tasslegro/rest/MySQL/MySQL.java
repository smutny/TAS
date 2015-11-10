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
	
	public List<Users> SelectFromUsers() throws SQLException{
		if( this.Connected ){
			try{
				this.StatementDB.executeUpdate("USE ONLINE_AUCTIONS;");
				this.SQLQueryString = "SELECT User_ID, Name, Surname, Email, Phone, Login, Account, Address, Town, ZipCode, Street FROM USERS";
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
}