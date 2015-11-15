package tasslegro.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/")
public class MainInfo {
	@GET
	@Produces("text/plain")
	public String getMsg() {
		return "Hello :)\n"
				+ "All path:\n" 
				+ "\t/users - list all users\n" 
				+ "\t/auctions - list all auctions\n"
				+ "";
	}
}
