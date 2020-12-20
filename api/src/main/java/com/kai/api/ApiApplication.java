package com.kai.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Locale;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);

        Locale[] availableLocales = Locale.getAvailableLocales();
        Arrays.stream(availableLocales).forEach(item->{
           System.out.println(" 国家 " +item.getCountry()+ " "+item.getDisplayCountry() +" " +item.getScript()+" 语言"+ item.getLanguage()+" "+item.getDisplayLanguage());
        });

    }

}
