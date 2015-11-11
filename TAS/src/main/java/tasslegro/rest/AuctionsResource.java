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
import tasslegro.rest.model.Auctions;

@Path("/Auctions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuctionsResource {
	
	@GET
	public List<Auctions> getMsg() throws ClassNotFoundException, SQLException {
    	MySQL tmp = new MySQL();
    	tmp.StartConnection();
    	List<Auctions> AuctionsList = tmp.getAuctions();
    	tmp.finalize();
    	if( AuctionsList == null ){
    		return null;
    	}
    	return AuctionsList;
	}
}
