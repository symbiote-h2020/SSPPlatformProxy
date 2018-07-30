package eu.h2020.symbiote.fm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author RuggenthalerC
 *
 *         Main entry point to start spring boot application.
 */
@EnableDiscoveryClient    
@EnableAutoConfiguration
@SpringBootApplication
public class FederationManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FederationManagerApplication.class, args);
    }
}
