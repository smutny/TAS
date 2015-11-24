package tasslegro.rest.resource;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
public class UsersResource {

	MySQL database = null;

	public UsersResource() throws ClassNotFoundException, SQLException {
		this.database = new MySQL();
	}

	public void finaly() {
		this.database.finalize();
	}

	@GET
	@ApiOperation(value = "Get list all users.")
	public Response getUsers() throws ClassNotFoundException, SQLException {
		if (!this.database.IsConnected()) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Problem with server! Please try again later!\n").build();
		}
		List<Users> UserList = this.database.getUsers();
		if (UserList == null) {
			return Response.status(Response.Status.NO_CONTENT).entity("No content!").build();
		} else {
			return Response.status(Response.Status.OK).entity(UserList).build();
		}
	}

	@POST
	@ApiOperation(value = "Add user.")
	public Response addUser(Users user) throws ClassNotFoundException, SQLException {
		if (!this.database.IsConnected()) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Problem with server! Please try again later!\n").build();
		}
		if (user.getEmail().isEmpty()) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Email is required!\n").build();
		} else if (user.getLogin().isEmpty()) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Login is required!\n").build();
		} else if (user.getPass().isEmpty()) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Password is required!\n").build();
		} else if (this.database.checkExistUserByLogin(user.getLogin())) {
			return Response.status(Response.Status.CONFLICT)
					.entity("User with login \"" + user.getLogin() + "\" exists!\n").build();
		} else if (this.database.checkExistUserByEmail(user.getEmail())) {
			return Response.status(Response.Status.CONFLICT)
					.entity("User with email \"" + user.getEmail() + "\" exists!\n").build();
		} else {
			Users tmp = this.database.addUser(user);
			if (tmp == null) {
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity("Problem with server! Please try again later!\n").build();
			} else {
				return Response.status(Response.Status.CREATED).entity(tmp).build();
			}
		}
	}

	@PUT
	@ApiOperation(value = "Update user.")
	public Response updateUser(Users user) {
		if (!this.database.IsConnected()) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Problem with server! Please try again later!\n").build();
		}
		return null;
	}

	@DELETE
	@ApiOperation(value = "Delete user.")
	public Response deleteUser(Users user) {
		if (!this.database.IsConnected()) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Problem with server! Please try again later!\n").build();
		}
		return null;
	}

	@Path("/{login}")
	@GET
	@ApiOperation(value = "Get all users with {login}.")
	public Response getUser(@PathParam("login") final String login) throws ClassNotFoundException, SQLException {
		if (!this.database.IsConnected()) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Problem with server! Please try again later!\n").build();
		}
		Users User = this.database.getUserByLogin(login);
		if (User == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("User with login \"" + login + "\" not found!\n")
					.build();
		} else {
			return Response.status(Response.Status.FOUND).entity(User).build();
		}
	}

}
