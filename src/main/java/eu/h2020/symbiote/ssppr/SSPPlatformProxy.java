package eu.h2020.symbiote.ssppr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Vasilis Glykantzis
 *
 *         Main entry point to start spring boot application.
 */
@EnableAutoConfiguration
@SpringBootApplication
public class SSPPlatformProxy {

	public static void main(String[] args) {
		SpringApplication.run(SSPPlatformProxy.class, args);
    }
}
