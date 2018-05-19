package com.starter.web.config;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAutoConfiguration
@EnableDiscoveryClient
public class StarterWebApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(StarterWebApplication.class);
	
	@Autowired
	private ApplicationContext context;

	@Override
	public void run(String... args) throws Exception {
		
		Environment environment = context.getEnvironment();
		String protocol = "http";
		if(environment.getProperty("server.ssl.key-store")!=null){
			protocol = "https";
		}
		
		log.info(
				"\n------------------------------------------------------\n\t"
					+ "Application '{}' is running! Access URLs:\n\t" + "Local: \t\t{}://localhost:{}\n\t"
					+ "External: \t{}://{}:{}\n\t"
					+ "Profile(s): \t{}\n----------------------------------------------------------------",
					environment.getProperty("spring.application.name"),protocol,environment.getProperty("server.port"),
					protocol, InetAddress.getLocalHost().getHostAddress(), environment.getProperty("server.port"),
					environment.getActiveProfiles());
		
	}
	/**
	------------------------------------------------------
	Application 'proy-h2-provider' is running! Access URLs: ---> VALOR DE PROPETIES NAME SERVICE
	Local: 		http://localhost:5004 ---> PORT
	External: 	http://192.168.56.1:5004 ----> VALOR DE HOST MACHINE
	Profile(s): [dev] --> VALOR DEL PROPERTIES .YML
----------------------------------------------------------------
	**/

}
