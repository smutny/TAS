package tasslegro.rest;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import tasslegro.rest.MySQL.MySQL;
import tasslegro.rest.model.Users;

@Path("/Users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource{

    @GET
	public List<Users> getUsers() throws ClassNotFoundException, SQLException {
    	MySQL tmp = new MySQL();
    	tmp.StartConnection();
    	List<Users> UserList = tmp.getUsers();
    	tmp.finalize();
    	return UserList;
	}
    
    @POST
    public Users addUser( Users user ) throws ClassNotFoundException, SQLException{
    	MySQL tmp = new MySQL();
    	tmp.StartConnection();
    	if( tmp.checkUserByLogin( user.getLogin() ) ){
    		/*
    		 * INSERT
    		 curl -H "Content-Type: application/json" -X POST -d '{"login":"login123", "pass":"pass123", "email":"login123@abc.com", "address":"Address123", "town":"Town123", "zipCode":"ZipCode123", "street":"Street123", "surname":"Nazwisko123", "phone":123123123, "account":111222333, "name":"Imie123" }' http://localhost:8080/Users
    		 */
    		Users t = tmp.addUser( user );
    		tmp.finalize();
    		return t;
    	}
    	else{
    		tmp.finalize();
    		return null;
    	}
    }
    
    @Path("/{id}")
    @GET
    public Users getUser( @PathParam("id") final String id ) throws ClassNotFoundException, SQLException {
    	MySQL tmp = new MySQL();
    	tmp.StartConnection();
    	Users User = tmp.getUserById( id );
    	tmp.finalize();
    	if( User == null ){
    		return null;
    	}
    	return User;
	}
    
}