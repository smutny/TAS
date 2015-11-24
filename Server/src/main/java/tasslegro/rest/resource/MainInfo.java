package tasslegro.rest.resource;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import tasslegro.rest.MySQL.MySQL;

@Path("/")
@Produces("text/plain")
public class MainInfo {

	MySQL database = null;

	public MainInfo() throws ClassNotFoundException, SQLException {
		this.database = new MySQL();
	}

	public void finaly() {
		this.database.finalize();
	}

	@GET
	public Response getMsg() {
		String msg = "Hello :)\nAll path:\n\t/users - list all users\n\t/auctions - list all auctions\n\t/images - list all images\n";
		if (!this.database.IsConnected()) {
			msg += "\n\nProblem with server! Please try again later!\n";
		}
		return Response.status(Response.Status.OK).entity(msg).build();
	}
}
