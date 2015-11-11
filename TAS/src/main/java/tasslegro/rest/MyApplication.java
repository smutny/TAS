package tasslegro.rest;
import org.glassfish.jersey.server.ResourceConfig;

public class MyApplication extends ResourceConfig {
   public MyApplication (){
      register(MainInfo.class);
      register(UsersResource.class);
      register(AuctionsResource.class);
   }
}
