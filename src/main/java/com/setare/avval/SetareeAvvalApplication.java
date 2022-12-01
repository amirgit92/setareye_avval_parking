package com.setare.avval;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Random;

@SpringBootApplication
public class SetareeAvvalApplication {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(SetareeAvvalApplication.class, args);
    }

    public String licensePlateGenerator(){
        String AlphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder s = new StringBuilder(6);
        Random r = new Random();
        int i = r.nextInt(89) + 10;
        s.append(i);
        int ch = (int) (AlphaNumericStr.length() * Math.random());
        s.append(AlphaNumericStr.charAt(ch));
        int j = r.nextInt(898) + 100;
        s.append(j);
        return s.toString();
    }

}
