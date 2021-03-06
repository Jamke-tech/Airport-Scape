package edu.upc.eetac.dsa;

import io.swagger.jaxrs.config.BeanConfig;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://147.83.7.203:8080/gameDSA/";//entorno produccion
    //public static final String BASE_URI = "http://localhost:8080/gameDSA/";//entorno local
    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in edu.upc.dsa package
        final ResourceConfig rc = new ResourceConfig().packages("edu.upc.eetac.dsa.service");

        rc.register(io.swagger.jaxrs.listing.ApiListingResource.class);
        rc.register(io.swagger.jaxrs.listing.SwaggerSerializers.class);

        BeanConfig beanConfig = new BeanConfig();


        beanConfig.setHost("eetacdsa0.upc.es:8080");//entorno produccion
        //beanConfig.setHost("localhost:8080");//entorno local
        beanConfig.setBasePath("/gameDSA");
        beanConfig.setContact("support@example.com");
        beanConfig.setDescription("REST API for GAMESERVICE DSA");
        beanConfig.setLicenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html");
        beanConfig.setResourcePackage("edu.upc.eetac.dsa.service");
        beanConfig.setTermsOfServiceUrl("http://www.example.com/resources/eula");
        beanConfig.setTitle("REST API");
        beanConfig.setVersion("1.0.0");
        beanConfig.setScan(true);

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();

        StaticHttpHandler staticHttpHandler = new StaticHttpHandler("./public/");
        server.getServerConfiguration().addHttpHandler(staticHttpHandler, "/");


        /*System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));*/

        String swagger_uri = BASE_URI;
        String target = "gameDSA";
        String replacement = "swagger";
        swagger_uri = swagger_uri.replace(target, replacement);
        System.out.println(String.format("RestApi Started at " + "%s\nHit enter to stop it...", swagger_uri));
        System.in.read();
        //server.shutdownNow();
        server.stop();
    }
}
