package io.qsource.webservice;

import io.qsource.webservice.util.LoggingConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan
@EntityScan({"io.qsource.webservice.entitiy"})
@EnableJpaRepositories({"io.qsource.webservice.repo"})
public class Main {

    private final Log log = LogFactory.getLog(LoggingConstants.MAIN_LOG);

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
