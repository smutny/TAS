package tasslegro.rest;

import java.util.List;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import tasslegro.rest.MySQL.MySQL;
import tasslegro.rest.model.Users;

@Path("/users")
@Api(value = "users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource{
	
	MySQL database = null;
	
	public UsersResource() throws ClassNotFoundException, SQLException{
		this.database = new MySQL();
	}
	
	public void finaly(){
		this.database.finalize();
	}

	@GET
    @ApiOperation(value = "Get list all users.")
	public Response getUsers() throws ClassNotFoundException, SQLException {
    	List<Users> UserList = this.database.getUsers();
    	if( UserList == null ){
    		return Response.status( Response.Status.NO_CONTENT ).entity( "No content!" ).build();
    	}
    	else{
    		return Response.status( Response.Status.OK ).entity( UserList ).build();
    	}
	}

	@POST
	@ApiOperation(value = "Add user.")
    public Response addUser( Users user ) throws ClassNotFoundException, SQLException{
	    	/*
			 * INSERT
			 curl -i -H "Content-Type: application/json" -X POST -d '{"login":"login123", "pass":"pass123", "email":"login123@abc.com", "address":"Address123", "town":"Town123", "zipCode":"ZipCode123", "surname":"Nazwisko123", "phone":123123123, "account":111222333, "name":"Imie123" }' http://localhost:8080/users
			 */
    	if( user.getEmail().isEmpty() ){
    		return Response.status( Response.Status.NOT_ACCEPTABLE ).entity( "Email is required!\n" ).build();
    	}
    	else if( user.getLogin().isEmpty() ){
    		return Response.status( Response.Status.NOT_ACCEPTABLE ).entity( "Login is required!\n" ).build();
    	}
    	else if( user.getPass().isEmpty() ){
    		return Response.status( Response.Status.NOT_ACCEPTABLE ).entity( "Password is required!\n" ).build();
    	}
    	else if( this.database.checkExistUserByLogin( user.getLogin() ) ){
    		return Response.status( Response.Status.CONFLICT ).entity( "User with login \"" + user.getLogin() +  "\" exists!\n" ).build();
    	}
    	else if( this.database.checkExistUserByEmail( user.getEmail() ) ){
    		return Response.status( Response.Status.CONFLICT ).entity( "User with email \"" + user.getEmail() +  "\" exists!\n" ).build();
    	}
    	else{
    		Users tmp = this.database.addUser( user );
    		if( tmp == null ){
    			return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( "Problem with server! Please try again later!\n" ).build();
    		}
    		else{
    			return Response.status( Response.Status.CREATED ).entity( tmp ).build();
    		}
    	}
    }
    
	@Path("/{id}")
	@GET
	@ApiOperation(value = "Get all users with {id}.")
    public Response getUser( @PathParam("id") final String id ) throws ClassNotFoundException, SQLException {
    	Users User = this.database.getUserById( id );
    	if( User == null ){
    		return Response.status( Response.Status.NOT_FOUND ).entity( "User with id: " + id +  " not found!\n" ).build();
    	}
    	else{
    		return Response.status( Response.Status.FOUND ).entity( User ).build();
    	}
    }

}
