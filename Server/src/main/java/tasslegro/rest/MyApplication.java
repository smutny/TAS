package tasslegro.rest;

import org.glassfish.jersey.server.ResourceConfig;
import io.swagger.jaxrs.config.BeanConfig;

public class MyApplication extends ResourceConfig {
   public MyApplication (){
      register(MainInfo.class);
      register(UsersResource.class);
      register(AuctionsResource.class);
      packages("io.swagger.jaxrs.listing");

      BeanConfig beanConfig = new BeanConfig();
      beanConfig.setVersion("1.0.2");
      beanConfig.setSchemes(new String[]{"http"});
      beanConfig.setHost("localhost:8080");
      beanConfig.setBasePath("");
      beanConfig.setResourcePackage("tasslegro.rest");
      beanConfig.setScan(true);
   }
}
