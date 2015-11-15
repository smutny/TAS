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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.client.Entity;
import java.net.URI;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tasslegro.rest.MySQL.MySQL;
import tasslegro.rest.model.Users;

@Path("/users")
@Api(value = "users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource{

    @GET
    @ApiOperation(value = "List all users.")
	public List<Users> getUsers() throws ClassNotFoundException, SQLException {
    	MySQL tmp = new MySQL();
    	tmp.StartConnection();
    	List<Users> UserList = tmp.getUsers();
    	tmp.finalize();
    	return UserList;
	}

    @POST
    public Response addUser( Users user ) throws ClassNotFoundException, SQLException{
    	MySQL tmp = new MySQL();
    	tmp.StartConnection();
	    	/*
			 * INSERT
			 curl -i -H "Content-Type: application/json" -X POST -d '{"login":"login123", "pass":"pass123", "email":"login123@abc.com", "address":"Address123", "town":"Town123", "zipCode":"ZipCode123", "street":"Street123", "surname":"Nazwisko123", "phone":123123123, "account":111222333, "name":"Imie123" }' http://localhost:8080/users
			 */
    	if( tmp.checkExistUserByLogin( user.getLogin() ) ){
    		tmp.finalize();
    		return Response.status( Response.Status.CONFLICT ).entity( "User with login \"" + user.getLogin() +  "\" exists!\n" ).build();
    	}
    	else if( tmp.checkExistUserByEmail( user.getEmail() ) ){
    		tmp.finalize();
    		return Response.status( Response.Status.CONFLICT ).entity( "User with email \"" + user.getEmail() +  "\" exists!\n" ).build();
    	}
    	else{
    		Users t = tmp.addUser( user );
    		tmp.finalize();
    		return Response.status( Response.Status.CREATED ).entity( t ).build();
    	}
    }

    @Path("/{id}")
    @GET
    public Response getUser( @PathParam("id") final String id ) throws ClassNotFoundException, SQLException {
    	MySQL tmp = new MySQL();
    	tmp.StartConnection();
    	Users User = tmp.getUserById( id );
    	tmp.finalize();
    	if( User == null ){
    		return Response.status( Response.Status.NOT_FOUND ).entity( "User with id: " + id +  " not found!\n" ).build();
    	}
    	return Response.status( Response.Status.CREATED ).entity( User ).build();
	}

}
