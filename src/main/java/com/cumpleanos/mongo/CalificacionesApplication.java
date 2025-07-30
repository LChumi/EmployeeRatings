package com.cumpleanos.mongo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(
		info = @Info(
				title = "Calificaciones", description = "Documentación de procesos con mongo "
		)
)
public class CalificacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalificacionesApplication.class, args);
	}

}
