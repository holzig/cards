package cma.cards;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.Bootstrap;

@SpringBootApplication
public class CardsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CardsApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner cmd() {
		return new CommandLineRunner() {
			@Override
			public void run(String... arg0) throws Exception {
				new Bootstrap(arg0).run();
			}
		};
	}
}
