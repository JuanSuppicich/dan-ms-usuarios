package com.durandsuppicich.danmsusuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class DanMsUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(DanMsUsuariosApplication.class, args);
	}

}
