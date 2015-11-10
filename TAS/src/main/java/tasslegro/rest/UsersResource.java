package tasslegro.rest;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import tasslegro.rest.MySQL.*;
import tasslegro.rest.model.Users;

@Path("/Users")
public class UsersResource{

    @GET
    @Produces("application/json")
	public List<Users> getMsg() throws ClassNotFoundException, SQLException {
    	MySQL tmp = new MySQL();
    	tmp.StartConnection();
//    	String all = "";//= tmp.Select( "SELECT Name_u, Surname FROM USERS" );
    	List<Users> UserList = tmp.SelectFromUsers();
    	tmp.finalize();
//		return "Users:\n" + all;
    	return UserList;
	}
}