package jaxrs;

import io.undertow.Undertow;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;

import dao.UserDao;
import domain.User;

import java.util.logging.Logger;

/**
 * RESTfull microservice, based on JAX-RS and JBoss Undertow
 *
 */
public class RestServer {

    private static final Logger logger = Logger.getLogger(RestServer.class.getName());

    public static void main( String[] args ) {

        UndertowJaxrsServer ut = new UndertowJaxrsServer();

        TestApplication ta = new TestApplication();

        ut.deploy(ta);

        ut.start(
                Undertow.builder()
                        .addHttpListener(8080, "localhost")

        );

        logger.info("JAX-RS based micro-service running!");
        User user = new User();
        user.setEmail("a");
        UserDao u = new UserDao();
        u.save(user);
        u.delete(user);
    }
}
