package com.proyecto.springboot.items;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	//El bean se registra en el contenedor de Spring con el nombre del metodo
	@Bean ("clienteRest")
	@LoadBalanced
	public RestTemplate registrarRestTemplate() {
		
		
		return new RestTemplate();
		
	}

}
