package br.com.ntconsult.hotelaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
@EntityScan("br.com.ntconsult.hotelaria.persistence.entities")
public class NTConsultApplication {
    public static void main(String[] args) {
        SpringApplication.run(NTConsultApplication.class, args);
    }
}
