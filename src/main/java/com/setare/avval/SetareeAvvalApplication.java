package com.setare.avval;

import com.setare.avval.facilities.DataBaseSeed;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.xml.crypto.Data;
import java.util.Random;

@SpringBootApplication
public class SetareeAvvalApplication {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(SetareeAvvalApplication.class, args);
        DataBaseSeed seed = new DataBaseSeed("seeding thread");
        seed.start();
    }
}
